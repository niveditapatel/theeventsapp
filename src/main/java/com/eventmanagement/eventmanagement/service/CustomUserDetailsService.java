package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.CustomUserDetails;
import com.eventmanagement.eventmanagement.entity.User;
import com.eventmanagement.eventmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

       Optional<User> optionalUser = userRepository.findByEmail(email);

       optionalUser.orElseThrow(() ->  new UsernameNotFoundException("User is invalid"));

        // For debugging purpose
         System.out.println(optionalUser.toString());

      return optionalUser.map(CustomUserDetails::new).get();
    }
}
