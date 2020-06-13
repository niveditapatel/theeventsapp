package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



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
    public void sendNotification(User user) throws MailException
    {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("eventappinterns@gmail.com");
        mail.setSubject("hi");
        mail.setText("hi");
        javaMailSender.send(mail);

    }

}

