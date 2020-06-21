package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.entity.EventDashboard;
import com.eventmanagement.eventmanagement.entity.EventSender;
import com.eventmanagement.eventmanagement.repository.EventRepository;
//import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class EventServiceTest {
    @InjectMocks
    EventService eventService;
    @Mock
    EventRepository eventRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getEvents() {
        List<Event> events = new ArrayList<>();
        // Date datestart= new Date(2020,6,19);
        //  LocalDate l=new LocalDate(2020,6,19);
        Date startdate = new Date();
        Date enddate = new Date();

        events.add(new Event(1, "Meeting", "patelnivedita@icloud.com", "descriptionofmeeting", "place", startdate, enddate, "conference"));
        events.add(new Event(2, "Meeting2", "patelnivedita@icloud.com", "descriptionofmeeting", "place", startdate, enddate, "conference"));

        when(eventRepository.findAll()).thenReturn(events);
        List<Event> eventDB = eventService.getEvents();
        assertEquals(2, eventDB.size());
    }

    @Test
    void findEventByHost() {
        List<Event> events = new ArrayList<>();
        Date startdate = new Date();
        Date enddate = new Date();

        events.add(new Event(1, "Meeting", "patelnivedita@icloud.com", "descriptionofmeeting", "place", startdate, enddate, "conference"));
        events.add(new Event(2, "Meeting2", "patelnivedita@icloud.com", "descriptionofmeeting", "place", startdate, enddate, "conference"));
        when(eventRepository.findByEmail("patelnivedita@icloud.com")).thenReturn(events);
        List<Event> result = eventService.findEventByHost("patelnivedita@icloud.com");
        assertEquals(2, result.size());

    }

    @Test
    void getEventById() {
        Date startdate = new Date();
        Date enddate = new Date();
        Event event = new Event(1, "Meeting", "patelnivedita@icloud.com", "descriptionofmeeting", "place", startdate, enddate, "conference");
        when(eventRepository.findById(anyInt())).thenReturn(java.util.Optional.of(event));
        Event result = eventService.getEventById(1);
        assertEquals("Meeting", result.getTitle());

    }


    @Test
    void deleteEvent() {
        int id = 1;
        Date startdate = new Date();
        Date enddate = new Date();
        Event event = new Event(1, "Meeting", "patelnivedita@icloud.com", "descriptionofmeeting", "place", startdate, enddate, "conference");
        String s = eventService.deleteEvent(1);
        assertNull(eventService.getEventById(1));
        Assertions.assertEquals(s, "Event Removed");

        //test for exception handling when you try to remove a non-existent event;
        eventService.deleteEvent(1);

        //test for exception handling when you try to remove null
        eventService.deleteEvent(0);

    }

    //test again
    @Test
    void findEventDashboard() throws ParseException {
        List<Event> events = new ArrayList<>();
        Date startdate = new Date();
        Date enddate = new Date();
        events.add(new Event(1, "Meeting", "patelnivedita@icloud.com", "descriptionofmeeting", "place", startdate, enddate, "conference"));
        events.add(new Event(2, "Meeting2", "patelnivedita@icloud.com", "descriptionofmeeting", "place", startdate, enddate, "conference"));
        //  List <EventDashboard> eventDashboardList = null;
        // eventDashboardList.add(new EventDashboard("Meeting","patelnivedita@icloud.com","place", "2020-06-19 00:00:00"));
        // eventDashboardList.add(new EventDashboard("Meeting2","patelnivedita@icloud.com","place", "2020-06-19 00:00:00"));
        when(eventRepository.findEventByUser(anyInt())).thenReturn(events);
        List<EventDashboard> result = eventService.findEventDashboard(1);
        Assertions.assertEquals(2, result.size());

    }


    @Test
    void findEventsToday() {
        when(eventRepository.findEventsToday()).thenReturn(1);
        int no = eventService.findEventsToday();
        assertNotNull(no);
        assertEquals(no, 1);
    }

    @Test
    void findEventByUser() {

        List<Event> events = new ArrayList<>();
        Date startdate = new Date();
        Date enddate = new Date();
        events.add(new Event(1, "Meeting", "patelnivedita@icloud.com", "descriptionofmeeting", "place", startdate, enddate, "conference"));
        events.add(new Event(2, "Meeting2", "patelnivedita@icloud.com", "descriptionofmeeting", "place", startdate, enddate, "conference"));
        when(eventRepository.findEventByUser(anyInt())).thenReturn(events);
        List<EventSender> result = eventService.findEventByUser(1);
        Assertions.assertEquals(2, result.size());

    }


    @Test
    void getPendingEvents() {

        List<Event> events = new ArrayList<>();
        Date startdate = new Date();
        Date enddate = new Date();
        events.add(new Event(1, "Meeting", "patelnivedita@icloud.com", "descriptionofmeeting", "place", startdate, enddate, "conference"));
        events.add(new Event(2, "Meeting2", "patelnivedita@icloud.com", "descriptionofmeeting", "place", startdate, enddate, "conference"));
        when(eventRepository.findPendingResponse(1)).thenReturn(events);
        List<Event> result = eventService.getPendingEvents(1);
        assertEquals(2, result.size());
    }

    @Test
    void updateEvent() {
    }
}