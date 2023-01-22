package com.ashu.octopus.service.user;

import com.ashu.octopus.entity.User;

import java.util.List;

public interface UserService {

    public User saveUser(User user);

    public List<User> findUserByEmail(String email);

}
