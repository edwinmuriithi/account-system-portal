package com.example.accountsystemportal.services;

import com.example.accountsystemportal.entities.Transaction;
import com.example.accountsystemportal.entities.User;
import com.example.accountsystemportal.exceptions.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    User createUsers(User user);
    List<User> viewUsers() throws UserNotFoundException;
    User findUserById(Long userId) throws UserNotFoundException;

    User updateUserById(User user, Long userId) throws UserNotFoundException;

    void deleteUser(Long userId) throws UserNotFoundException;
}
