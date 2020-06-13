package com.eventmanagement.eventmanagement.controller;
import com.eventmanagement.eventmanagement.entity.User;

import com.eventmanagement.eventmanagement.service.NotificationService;
import com.eventmanagement.eventmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @RequestMapping("/signup")
    public String signup ()
    {
        return "signup";
    }

    @RequestMapping("/notification")
    public String signupsuccess () {


        User user= userService.getUserByEmail("17bce084@nirmauni.ac.in");

        try {
            notificationService.sendNotification(user);
        } catch (MailException e) {
        }

        return "signupsuccess";
    }
}

