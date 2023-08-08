package com.example.accountsystemportal.controller;

import com.example.accountsystemportal.entities.User;
import com.example.accountsystemportal.entities.dtos.UserDTO;
import com.example.accountsystemportal.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createNewUser (@RequestBody UserDTO userDTO){
        User userRequest = modelMapper.map(userDTO, User.class);
        User user = userService.createUsers(userRequest);
        UserDTO userResponse = modelMapper.map(user, UserDTO.class);

        return  new ResponseEntity<UserDTO>(userResponse, HttpStatus.CREATED);
    }
}
