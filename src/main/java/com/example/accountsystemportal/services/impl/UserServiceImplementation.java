package com.example.accountsystemportal.services.impl;

import com.example.accountsystemportal.entities.Transaction;
import com.example.accountsystemportal.entities.User;
import com.example.accountsystemportal.exceptions.UserCreationException;
import com.example.accountsystemportal.exceptions.UserNotFoundException;
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
        try {
            user.setFname(user.getFname());
            user.setDob(user.getDob());
            user.setBalance(user.getBalance());
            user.setAccount_type(user.getAccount_type());
            user.setNational_id(user.getNational_id());
            User newUser = userRepository.save(user);
            return newUser;
        } catch (Exception e) {
            throw new UserCreationException("Failed to create the user");
        }
    }


    @Override
    public List<User> viewUsers()throws UserNotFoundException {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            return users;
        } else {
            throw new UserNotFoundException("No users found");
        }
    }


    @Override
    public User findUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }


    @Override
    public User updateUserById(User user, Long userId) throws UserNotFoundException {
        try {
            User existingUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
            existingUser.setFname(user.getFname());
            existingUser.setDob(user.getDob());
            existingUser.setAccount_type(user.getAccount_type());
            existingUser.setBalance(user.getBalance());
            existingUser.setNational_id(user.getNational_id());
            User updatedUser = userRepository.save(existingUser);
            return updatedUser;
        } catch (Exception e) {
            throw new UserNotFoundException("Failed to update user: " + e.getMessage());
        }
    }


    @Override
    public void deleteUser(Long userId) throws UserNotFoundException {
        userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("user with id "+userId+ " not found"));
        log.info("Successfully deleted user");
        userRepository.deleteById(userId);
    }
}
