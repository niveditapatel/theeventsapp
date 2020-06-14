package com.eventmanagement.eventmanagement.controller;
import com.eventmanagement.eventmanagement.entity.User;

import com.eventmanagement.eventmanagement.service.NotificationService;
import com.eventmanagement.eventmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
   @Autowired private UserService userService;

    @Autowired private NotificationService notificationService;

    @RequestMapping("/signup")
    public String signup ()
    {
        return "signup";
    }

    @RequestMapping("/notification/{email}/{subject}/{text}")
    public String signupsuccess (@PathVariable String email, @PathVariable String subject, @PathVariable String text) {


        try {
            notificationService.sendNotification(email,subject,text);
        } catch (MailException e) {
        }

        return "signupsuccess";
    }
}
