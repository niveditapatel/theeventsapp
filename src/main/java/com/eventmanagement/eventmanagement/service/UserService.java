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
        user.setEmail(user.getEmail().toLowerCase());
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
                +"Verification Link:\n"+"http://backendproject-emb.apps.123.252.203.195.nip.io/api/verify/"+unique_id;
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
        email = email.toLowerCase();
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
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\n" +
                "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n" +
                "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div class=\"container\">\n" +
                "  <div class=\"jumbotron\">\n" +
                "    <h1>Email Verified!</h1>\n" +
                "    <p><a href='http://eventmanagementapp-emf.apps.123.252.203.195.nip.io/'>Login Here</a></p>\n" +
                "  </div>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";
    }
}
