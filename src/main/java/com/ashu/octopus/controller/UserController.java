package com.ashu.octopus.controller;

import com.ashu.octopus.data.Note;
import com.ashu.octopus.data.NoteWithToken;
import com.ashu.octopus.entity.User;
import com.ashu.octopus.models.user.RegisterUserRequest;
import com.ashu.octopus.models.user.RegistrationResponse;
import com.ashu.octopus.models.user.TokenRequest;
import com.ashu.octopus.service.user.UserService;
import com.ashu.octopus.utility.Constants;
import com.ashu.octopus.utility.GoogleUserToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationController notificationController;

    @PostMapping("/user/save")
    public ResponseEntity<RegistrationResponse> registerUser(@Autowired NetHttpTransport transport, @Autowired GsonFactory factory, @RequestBody RegisterUserRequest registerUserRequest) {

        String token = registerUserRequest.getToken();

        // Deserialize token
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        // validate token
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, factory)
                .setAudience(Collections.singletonList(Constants.API_CLIENT_KEY))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

        JsonFactory jsonFactory = new JacksonFactory();
//        try {
//            GoogleIdToken googleIdToken = GoogleIdToken.parse(jsonFactory, token);
//
//            if (!verifier.verify(googleIdToken)) {
//                return new ResponseEntity<>(new RegistrationResponse(), HttpStatus.UNAUTHORIZED);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(new RegistrationResponse(), HttpStatus.BAD_REQUEST);
//        }

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
            user.setMediumOfRegistration("google_android");
        }

        UUID uuid = UUID.randomUUID();
        user.setUserId(uuid.toString());
        HttpStatus status = HttpStatus.OK;

        if (checkForUniqueEmail(user.getEmail())) {
            userService.saveUser(user);
        } else {
            user = userService.findUserByEmail(user.getEmail());
            status = HttpStatus.ALREADY_REPORTED;
        }

        System.out.println(user);
        RegistrationResponse response = new RegistrationResponse();
        response.setUserUid(user.getUserId());
        response.setEmail(user.getEmail());
        response.setName(user.getName());
        response.setProfilePhoto(user.getImageUrl());
        response.setPhoneNumber(user.getUserPhone());

        return new ResponseEntity<>(response, status);
    }

    boolean checkForUniqueEmail(String email) {
        if (email == null) {
            return true;
        }

        User user = userService.findUserByEmail(email);

        return user == null;
    }

    @PostMapping("/user/enable-push")
    public ResponseEntity<Boolean> saveNotificationToken(@RequestBody TokenRequest tokenRequest) {
        String userId = tokenRequest.getUserId();

        User user = userService.findByUserId(userId);

        System.out.println(tokenRequest.getUserId());

        user.setNotificationToken(tokenRequest.getToken());

        try {
            userService.saveUser(user);
            System.out.println(user);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/user/notify")
    @ResponseBody
    public Boolean sendNotificationToAll() throws FirebaseMessagingException {
        List<User> users = userService.findAllUsers();

        for (User user: users) {
            if (user.getNotificationToken() != null) {
                System.out.println("notewa" + user.getNotificationToken());
                sendNotificationToUser(user.getNotificationToken());
            }
        }

        return true;
    }

    public void sendNotification(List<String> tokens) throws FirebaseMessagingException {
       for (String token : tokens) {
           this.sendNotificationToUser(token);
       }
    }

    @PostMapping("/send-multi-notification")
    @ResponseBody
    public String sendNotificationToUser(String token) throws FirebaseMessagingException {
        Note note = new Note();
        note.setSubject("First Push Notification");
        note.setContent("Random Content");
        Map<String, String> data = new LinkedHashMap<>();
        data.put("key1", "Value a");
        data.put("key2", "Value b");
        data.put("key3", "Value c");
        note.setData(data);
        note.setImage("https://somedomain.com/example.jpg");
        NoteWithToken noteWithToken = new NoteWithToken();
        noteWithToken.setToken(token);
        noteWithToken.setNote(note);

        System.out.println("notewa" + noteWithToken.toString());
        return notificationController.sendNotification(noteWithToken);
    }

}
