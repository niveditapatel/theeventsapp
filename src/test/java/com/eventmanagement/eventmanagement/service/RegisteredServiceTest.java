package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.*;
import com.eventmanagement.eventmanagement.repository.*;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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
        User user = new User(10,"nayamufaddal@gmail.com", "sd3edwfsdfdsfsdf", "Mufaddal", "Naya", "active", new Role(2, "CREATOR"));

        when(userRepository.findByEmail("nayamufaddal@gmail.com")).thenReturn(Optional.of(user));
        when(eventRepository.save(any())).thenReturn(event);
        when(registeredRepository.save(any())).thenReturn(new Registered());
        when(unseenEventRepository.save(any())).thenReturn(new UnseenEvent());
        Mockito.doNothing().when(notificationService).sendNotification(anyString(), anyString(), anyString());
        Mockito.doNothing().when(eventRepository).deleteById(anyInt());

        String result = registeredService.addEvent(eventReceiver);
        assertEquals("success", result);
    }

    @Test
    void addPublicEvent() {
        List<User> users = new ArrayList<>();
        users.add(new User(1,"nayamufaddal@gmail.com", "sd3edwfsdfdsfsdf", "Mufaddal", "Naya", "active", new Role(1, "ADMIN")));
        when(registeredRepository.save(any())).thenReturn(new Registered());
        when(unseenEventRepository.save(any())).thenReturn(new UnseenEvent());
        Mockito.doNothing().when(notificationService).sendNotification(anyString(), anyString(), anyString());

        when(userRepository.findAll()).thenReturn(users);
        String result = registeredService.addPublicEvent(new Event());

        assertEquals("success", result);
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