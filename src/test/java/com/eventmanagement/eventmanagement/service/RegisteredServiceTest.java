package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.entity.EventReceiver;
import com.eventmanagement.eventmanagement.entity.Registered;
import com.eventmanagement.eventmanagement.entity.VerificationCode;
import com.eventmanagement.eventmanagement.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class RegisteredServiceTest {

    @InjectMocks
    RegisteredService registeredService;
    @Mock
    UnseenEventRepository unseenEventRepository;
    @Mock
    RegisteredRepository registeredRepository;
    @Mock
    EventRepository eventRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    GroupRepository groupRepository;
    @Mock
    NotificationService notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addEvent() {
        Event event = new Event(1, "New Event", "mufaddal.naya@gmail.com", "description", "Skype for Business", new Date(), new Date(), "public");
        EventReceiver eventReceiver = new EventReceiver( "New Event", "mufaddal.naya@gmail.com", "description", "Skype for Business", new Date(), new Date(), "public", "emails", "nayamufaddal@gmail.com");

        when(eventRepository.save(any())).thenReturn(event);
        Mockito.doNothing().when(eventRepository).deleteById(anyInt());

        String result = registeredService.addEvent(eventReceiver);
        assertEquals("failed", result);
    }

    @Test
    void accept() {
        Registered registered = new Registered();
        when(registeredRepository.get(anyInt(), anyInt())).thenReturn(Optional.of(registered));
        when(registeredRepository.save(any())).thenReturn(new Registered());

        registeredService.accept(anyInt(), anyInt());
        assertEquals("accept", registered.getResponse());
    }

    @Test
    void reject() {
        Registered registered = new Registered();
        when(registeredRepository.get(anyInt(), anyInt())).thenReturn(Optional.of(registered));
        when(registeredRepository.save(any())).thenReturn(new Registered());

        registeredService.reject(anyInt(), anyInt());
        assertEquals("reject", registered.getResponse());
    }
}