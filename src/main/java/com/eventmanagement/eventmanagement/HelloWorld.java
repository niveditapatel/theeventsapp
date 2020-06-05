package com.eventmanagement.eventmanagement;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @RequestMapping("/")
    @CrossOrigin("*")
    public String index() {
        return "Hello World";
    }
}
