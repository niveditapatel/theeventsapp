package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.Role;
import com.eventmanagement.eventmanagement.entity.User;
import com.eventmanagement.eventmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User saveUser(User user) {
        Role role = new Role();
        role.setId(3);
        user.setRole(role);
        user.setStatus("active");
        String encryptedPassword = passwordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
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
}
