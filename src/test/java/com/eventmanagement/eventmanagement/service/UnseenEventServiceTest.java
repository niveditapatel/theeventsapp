package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.repository.EventRepository;
import com.eventmanagement.eventmanagement.repository.UnseenEventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class UnseenEventServiceTest {

    @InjectMocks
    UnseenEventService unseenEventService;

    @Mock
    UnseenEventRepository unseenEventRepository;

    @Mock
    EventRepository eventRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void alertsCountNewEvent() {
        when(unseenEventRepository.alertsCount(anyInt())).thenReturn(2);
        assertEquals(2,unseenEventService.alertsCountNewEvent(3));
    }

    @Test
    void alertsNewEvent() {
        Mockito.doNothing().when(unseenEventRepository).deleteById(anyInt());
        when(eventRepository.alerts(anyInt())).thenReturn(new ArrayList<>());
        when(unseenEventRepository.findByUserId(anyInt())).thenReturn(new ArrayList<>());

        assertEquals(0, unseenEventService.alertsNewEvent(1).size());
    }

    @Test
    void alertsCountEventIn15Min() {
        when(unseenEventRepository.alertsCountEventIn15Min(anyInt())).thenReturn(2);
        assertEquals(2,unseenEventService.alertsCountEventIn15Min(3));
    }

    @Test
    void alertsEventIn15Min() {
        when(eventRepository.alertsEventIn15Min(anyInt())).thenReturn(new ArrayList<>());
        assertEquals(0, unseenEventService.alertsEventIn15Min(1).size());
    }
}