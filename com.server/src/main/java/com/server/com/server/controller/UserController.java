package com.server.com.server.controller;


import com.server.com.server.dao.UserDao;
import com.server.com.server.entity.User;
import com.server.com.server.record.UserUpdateRequest;
import com.server.com.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRolesAndUsers() {
        userService.initRolesAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @GetMapping({"/forAdmin"})
    public String forAdmin() {
        return "This URL is only accessible to the admin";
    }


    @GetMapping({"/users"})
    public List<User> getAllUsers() {
        List<User> users = (List<User>) userDao.findAll();
        return users;
    }


    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser() {
        return "This URL is only accessible to the user";
    }

    @GetMapping({"/user/{userName}"})
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName) {
        User user = userService.getUserByUserName(userName);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping({"/user/{userName}"})
    public ResponseEntity<String> deleteUserByUserName(@PathVariable String userName) {
        try {
            userService.deleteUserByUserName(userName);
            return ResponseEntity.ok("User deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping({"/user/{userName}"})
    public ResponseEntity<User> updateUserByUserName(@PathVariable String userName, @RequestBody UserUpdateRequest userUpdateRequest) {
        User user = userService.getUserByUserName(userName);
        if (user != null) {
            User updatedUser = userService.updateUserByUserName(userName, userUpdateRequest, user);
            return ResponseEntity.ok(updatedUser);
        } else {
            throw new RuntimeException("User not found");
        }
    }


}



