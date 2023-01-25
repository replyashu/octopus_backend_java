package com.ashu.octopus.service.user;

import com.ashu.octopus.entity.User;
import com.ashu.octopus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findByUserId(String uuid) {
        System.out.println("User" + uuid + userRepository.findUserById(uuid));
        User user = userRepository.findUserById(uuid);
        if (user == null) {
            user = new User();
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
