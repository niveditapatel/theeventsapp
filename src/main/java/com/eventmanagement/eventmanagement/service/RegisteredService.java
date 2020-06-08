package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.Registered;
import com.eventmanagement.eventmanagement.repository.RegisteredRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RegisteredService {

    @Autowired
    RegisteredRepository registeredRepository;

    List<Registered> getRegisteredEvents(int userId) {
        return registeredRepository.findByUserId(userId);
    }

}
