package com.ashu.octopus.service.user;

import com.ashu.octopus.entity.Dish;
import com.ashu.octopus.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    public User saveUser(User user);

    public User findUserByEmail(String email);

    public User findByUserId(String uuid);

}
