package com.eventmanagement.eventmanagement.controller;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.entity.User;
import com.eventmanagement.eventmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/getEmails")
    public List<String> getEmails() {
        return userService.getEmails();
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/userByEmail/{email}")
    public User findByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/verify/{unique_code}")
    public String verify(@PathVariable String unique_code) {
        return userService.verify(unique_code);
    }

}
