package com.eventmanagement.eventmanagement.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @CrossOrigin("*")
    @RequestMapping("/")
    public String index() {
        return "Hello From Event Management API Spring Boot Application";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/login")
    public String login() {
        return "success";
    }

}
