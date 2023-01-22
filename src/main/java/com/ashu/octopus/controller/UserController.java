package com.ashu.octopus.controller;

import com.ashu.octopus.entity.User;
import com.ashu.octopus.models.user.RegisterUserRequest;
import com.ashu.octopus.models.user.RegistrationResponse;
import com.ashu.octopus.service.user.UserService;
import com.ashu.octopus.utility.GoogleUserToken;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/save")
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {

        String token = registerUserRequest.getToken();

        // Deserialize token
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        Gson gson = new Gson();
        GoogleUserToken userToken = gson.fromJson(payload, GoogleUserToken.class);

        System.out.println("\n\nemail:" + userToken.getEmail());

        User user = new User();
        user.setEmail(userToken.getEmail());
        user.setName(userToken.getName());
        user.setImageUrl(userToken.getPicture());

        if (userToken.getAud().contains("google")) {
            user.setModeOfRegister("google_sign_in");
        }

        UUID uuid = UUID.randomUUID();
        user.setUserId(uuid.toString());
        HttpStatus status = HttpStatus.OK;

        if (checkForUniqueEmail(user.getEmail())) {
            userService.saveUser(user);
        } else {
            status = HttpStatus.ALREADY_REPORTED;
        }

        RegistrationResponse response = new RegistrationResponse();
        response.setUserUid(user.getUserId());
        response.setEmail(user.getEmail());
        response.setName(user.getName());
        response.setProfilePhoto(user.getImageUrl());
        response.setPhoneNumber(user.getUser_phone());

        return new ResponseEntity<>(response, status);
    }

    boolean checkForUniqueEmail(String email) {
        if (email == null) {
            return true;
        }

        List<User> users = userService.findUserByEmail(email);

        System.out.println("Users found with email:" + email + users.size());

        return users.size() == 0;
    }



}
