package com.example.accountsystemportal.services;

import com.example.accountsystemportal.entities.Transaction;
import com.example.accountsystemportal.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    User createUsers(User user);
    List<User> viewUsers();
    User findUserById(Long userId);

    User updateUserById(User user, Long userId) throws Exception;
}
