package com.ashu.octopus.service.user;

import com.ashu.octopus.entity.User;
import com.ashu.octopus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public List<User> findUserByEmail(String email) {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "email"));
    }
}
