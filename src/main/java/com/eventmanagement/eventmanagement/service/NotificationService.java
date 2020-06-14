package com.eventmanagement.eventmanagement.service;



import com.eventmanagement.eventmanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private JavaMailSender javaMailSender;


    @Autowired
    public NotificationService(JavaMailSender javaMailSender)
    {
        this.javaMailSender=javaMailSender;
    }
    public void sendNotification(String email,String subject, String text) throws MailException
    {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom("eventinternsapp@gmail.com");
        mail.setSubject(subject);
        mail.setText(text);
        javaMailSender.send(mail);

    }

}

