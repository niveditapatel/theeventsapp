package com.eventmanagement.eventmanagement.service;

import ch.qos.logback.core.joran.action.IADataForComplexProperty;
import com.eventmanagement.eventmanagement.entity.Role;
import com.eventmanagement.eventmanagement.entity.User;
import com.eventmanagement.eventmanagement.entity.VerificationCode;
import com.eventmanagement.eventmanagement.repository.UserRepository;
import com.eventmanagement.eventmanagement.repository.VerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String saveUser(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
        return "Email already exists";
        }
        Role role = new Role();
        role.setId(3);
        user.setRole(role);
        user.setStatus("unverified");
        String encryptedPassword = passwordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user= userRepository.save(user);
        String unique_id=UUID.randomUUID().toString();
        VerificationCode vc = new VerificationCode();
        vc.setUnique_id(unique_id);
        vc.setUser_id(user.getId());
        String email = user.getEmail();
        String subject = "Verify Your Account" +user.getEmail();

        String text = "Hi " + user.getFirstName() + ",\n" +
                "You need to verify your Account for Events Application:\n"
                +"Verification Link:\n"+"http://localhost:8080/api/verify/"+unique_id;
        try {
            notificationService.sendNotification(email, subject, text);
        } catch (MailException e) {
            System.out.println("mail not sent " + e);
        }
        verificationCodeRepository.save(vc);
        return "success";

    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<String> getEmails() {
        return userRepository.getEmail();
    }

    public User getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElse(null);
    }
    public String verify(String unique_code)
    {
       Optional<VerificationCode> vc = verificationCodeRepository.findById(unique_code);
       if(!vc.isPresent())
           return "invalid URL";
       int user_id = vc.get().getUser_id();
       User user= userRepository.findById(user_id).get();
        user.setStatus("active");
        userRepository.save(user);
        verificationCodeRepository.delete(vc.get());
        return "<h1>success<h1><br><a href='http://localhost:3000/'>Login</a>";
    }
}
