package com.example.accountsystemportal.services.impl;

import com.example.accountsystemportal.entities.Transaction;
import com.example.accountsystemportal.entities.User;
import com.example.accountsystemportal.repositories.UserRepository;
import com.example.accountsystemportal.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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

    @Override
    public User updateUserById(User user, Long userId) throws Exception {
        User existingUser = userRepository.findById(userId).orElseThrow(()-> new Exception("Transaction with ID "+userId+" not found"));
        existingUser.setFname(user.getFname());
        existingUser.setDob(user.getDob());
        existingUser.setAccountType(user.getAccountType());
        existingUser.setBalance(user.getBalance());
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(Long userId) throws Exception {
        userRepository.findById(userId).orElseThrow(()-> new Exception("user with id "+userId+ " not found"));
        log.info("Successfully deleted user");
        userRepository.deleteById(userId);
    }
}
