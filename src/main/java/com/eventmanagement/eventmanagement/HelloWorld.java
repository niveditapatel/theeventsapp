package com.eventmanagement.eventmanagement;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @CrossOrigin("*")
    @RequestMapping("/")
    public String index() {
        return "Hello From Event Management API Spring Boot Application";
    }
}
