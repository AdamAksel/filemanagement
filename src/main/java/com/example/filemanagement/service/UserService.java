package com.example.filemanagement.service;

import com.example.filemanagement.model.User;
import com.example.filemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalStateException("Username already exists");
        }
        return userRepository.save(user);
    }

    public boolean authenticateUser(String username, String password) {
        Optional<User> foundUser = userRepository.findByUsername(username);
        return foundUser.isPresent() && foundUser.get().getPassword().equals(password);
    }

}


