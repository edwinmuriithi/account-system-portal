package com.example.accountsystemportal.services.impl;

import com.example.accountsystemportal.entities.User;
import com.example.accountsystemportal.repositories.UserRepository;
import com.example.accountsystemportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
