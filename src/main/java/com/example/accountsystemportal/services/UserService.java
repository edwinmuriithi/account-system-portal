package com.example.accountsystemportal.services;

import com.example.accountsystemportal.entities.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    User createUsers(User user);
}
