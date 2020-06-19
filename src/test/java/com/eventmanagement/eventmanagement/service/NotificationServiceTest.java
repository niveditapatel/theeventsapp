package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.repository.RegisteredRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.Message;
import javax.mail.MessagingException;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest {
    @InjectMocks
    NotificationService notificationService;


    @Mock
    private JavaMailSender javaMailSender;


 //   private RegisteredRepository Mailbox;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void sendNotification() throws MessagingException, IOException {
        String test= notificationService.sendNotification("patelnivedita@icloud.com","hi","hi");
      //  Object recipient="patelnivedita@icloud.com";
       // List<Message> inbox = Mailbox.get(recipient);
       // assertTrue(inbox.size() == 1);
       // assertEquals("hi", inbox.get(0).getSubject());
       // assertEquals("hi", inbox.get(0).getContent());
        assertEquals("Email Sent",test);
    }
}