package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.Role;
import com.eventmanagement.eventmanagement.entity.User;
import com.eventmanagement.eventmanagement.entity.VerificationCode;
import com.eventmanagement.eventmanagement.repository.UserRepository;
import com.eventmanagement.eventmanagement.repository.VerificationCodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    VerificationCodeRepository verificationCodeRepository;

    @Mock
    NotificationService notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void passwordEncoder() {
        assertNotNull(userService.passwordEncoder());
    }

    @Test
    void saveUser() {

        User user = new User(1,"mufaddal.naya@gmail.com", "sd3edwfsdfdsfsdf", "Mufaddal", "Naya", "active", new Role(1, "ADMIN"));
        when(userRepository.findByEmail("mufaddal.naya@gmail.com")).thenReturn(Optional.of(user));
        String result = userService.saveUser(user);
        assertNotNull(result);
        assertEquals("Email already exists", result);
        user = new User(1,"nayamufaddal@gmail.com", "sd3edwfsdfdsfsdf", "Mufaddal", "Naya", "active", new Role(1, "ADMIN"));
        when(userRepository.findByEmail("nayamufaddal@gmail.com")).thenReturn(Optional.empty());
        Mockito.doNothing().when(notificationService).sendNotification(any(),any(),any());
        user = new User(1,"nayamufaddal@gmail.com", "sd3edwfsdfdsfsdf", "Mufaddal", "Naya", "unverified", new Role(3, "USER"));
        when(userRepository.save(any())).thenReturn(user);
        when(verificationCodeRepository.save(any())).thenReturn(new VerificationCode());
        String result1 = userService.saveUser(user);

        assertNotNull(result);
        assertEquals("success", result1);
        assertEquals("unverified", user.getStatus());
        assertEquals(3, user.getRole().getId());

    }

    @Test
    void getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1,"mufaddal.naya@gmail.com", "sd3edwfsdfdsfsdf", "Mufaddal", "Naya", "active", new Role(1, "ADMIN")));
        users.add(new User(2,"17bit055@nirmauni.ac.in", "dsfv", "NN", "Uni", "active", new Role(3, "USER")));
        when(userRepository.findAll()).thenReturn(users);
        List<User> usersDB = userService.getUsers();
        assertNotNull(usersDB);
        assertEquals(2, usersDB.size());

    }

    @Test
    void getEmails() {

        List<String> emails = new ArrayList<>();
        emails.add("mufaddal.naya@gmail.com");
        emails.add("17bit055@nirmauni.ac.in");
        emails.add("nayamufaddal@gmail.com");
        when(userRepository.getEmail()).thenReturn(emails);
        List<String> emailsDB = userService.getEmails();
        assertNotNull(emailsDB);
        assertEquals(3, emailsDB.size());

    }

    @Test
    void getUserByEmail() {
        User user = new User(1,"mufaddal.naya@gmail.com", "sd3edwfsdfdsfsdf", "Mufaddal", "Naya", "active", new Role(1, "ADMIN"));
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        User userDB = userService.getUserByEmail("mufadDAL.naya@gmail.com");

        assertNotNull(userDB);
        assertEquals("Mufaddal", userDB.getFirstName());
    }

    @Test
    void verify() {
        when(verificationCodeRepository.findById("213asd")).thenReturn(Optional.empty());
        assertEquals("invalid URL", userService.verify("213asd"));

        VerificationCode verificationCode = new VerificationCode("asfdshjfg-dsfbjv-sdf", 2);
        when(verificationCodeRepository.findById("asfdshjfg-dsfbjv-sdf")).thenReturn(Optional.of(verificationCode));
        User user = new User(2,"mufaddal.naya@gmail.com", "sd3edwfsdfdsfsdf", "Mufaddal", "Naya", "unverified", new Role(1, "ADMIN"));
        when(userRepository.findById(2)).thenReturn(Optional.of(user));

        when(userRepository.save(any())).thenReturn(user);
        Mockito.doNothing().when(verificationCodeRepository).deleteById(any());

        String result = userService.verify("asfdshjfg-dsfbjv-sdf");
        assertNotNull(result);
        assertEquals("active", user.getStatus());
    }
}