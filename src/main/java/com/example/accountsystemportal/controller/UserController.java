package com.example.accountsystemportal.controller;

import com.example.accountsystemportal.entities.Transaction;
import com.example.accountsystemportal.entities.User;
import com.example.accountsystemportal.entities.dtos.TransactionDTO;
import com.example.accountsystemportal.entities.dtos.UserDTO;
import com.example.accountsystemportal.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@Slf4j
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

    @GetMapping
    public ResponseEntity<List<UserDTO>> viewAll(){
        return ResponseEntity.ok(userService.viewUsers().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO>viewCampaignBYId(@PathVariable Long userId)  {
        User user = userService.findUserById(userId);
        UserDTO userResponse = modelMapper.map(user, UserDTO.class);

        return ResponseEntity.ok().body(userResponse);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUserById(@RequestBody UserDTO userDTO, @PathVariable Long userId)
            throws Exception {
        User userRequest = modelMapper.map(userDTO, User.class);
        User user = userService.updateUserById(userRequest, userId);
        UserDTO userResponse = modelMapper.map(user,UserDTO.class);
        return ResponseEntity.ok().body(userResponse);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            log.info("User with ID {} has been deleted successfully", userId);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            log.error("Failed to delete user with ID {}", userId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user");
        }
    }



}


