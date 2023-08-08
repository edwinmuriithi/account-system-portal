package com.example.accountsystemportal.services.impl;

import com.example.accountsystemportal.entities.User;
import com.example.accountsystemportal.repositories.UserRepository;
import com.example.accountsystemportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUsers(User user) {
        user.setFname(user.getFname());
        user.setDob(user.getDob());
        user.setBalance(user.getBalance());
        user.setAccountType(user.getAccountType());
        User newUser = userRepository.save(user);
        return newUser;
    }

    @Override
    public List<User> viewUsers() {
        List<User> user = userRepository.findAll();
        return user;
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> users = userRepository.findById(userId);
        return users.get();
    }
}
