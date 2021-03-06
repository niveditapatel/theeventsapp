package com.eventmanagement.eventmanagement.controller;

import com.eventmanagement.eventmanagement.entity.*;


import com.eventmanagement.eventmanagement.service.EventService;
import com.eventmanagement.eventmanagement.service.RegisteredService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "*")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private RegisteredService registeredService;

    @PostMapping("/addEvent")
    public String addEvent(@RequestBody EventReceiver eventReceiver) {
        return registeredService.addEvent(eventReceiver);
    }

    @GetMapping("/eventsByUserId/{user_id}")
    public List<EventSender> eventsByUserId(@PathVariable int user_id) {
        return eventService.findEventByUser(user_id);
    }

    @GetMapping("/eventsForUser/{user_id}")
    public List<Event> eventsForUser(@PathVariable int user_id) {
        return eventService.findEventForUser(user_id);
    }

    @GetMapping("/events")
    public List<Event> findAllEvents(Principal principal) {
        //Currently Logged-in User
        System.out.println(principal.toString());
        return eventService.getEvents();
    }

    @GetMapping("/eventById/{id}")
    public Event findEventById(@PathVariable int id) {
        return eventService.getEventById(id);
    }

    @DeleteMapping("/deleteEvent/{id}")
    public String deleteEvent(@PathVariable int id) {
        return eventService.deleteEvent(id);
    }

    @GetMapping("/getEventDashboard/{user_id}")
    public List<EventDashboard> findEventDashboard (@PathVariable int user_id) throws ParseException {
        return eventService.findEventDashboard(user_id);
    }

    @GetMapping("/findEventByHost/{email}")
    public List<Event> findEventByHost(@PathVariable String email)
    {
        return eventService.findEventByHost(email);
    }

    @GetMapping("/getEventsToday/{user_id}")
    public Integer findEventsToday (@PathVariable int user_id) {
        return eventService.findEventsToday(user_id);
    }

    @GetMapping("/getPendingEvents/{user_id}")
    public List<Event> getPendingEvents(@PathVariable int user_id) {
        return eventService.getPendingEvents(user_id);
    }

    @PostMapping("/accept/{user_id}/{event_id}")
    public void accept(@PathVariable int user_id, @PathVariable int event_id) {
         registeredService.accept(user_id, event_id);
    }

    @PostMapping("/reject/{user_id}/{event_id}")
    public void reject(@PathVariable int user_id, @PathVariable int event_id) {
         registeredService.reject(user_id, event_id);
    }

    @PostMapping("/updateEvent")
    public String updateEvent(@RequestBody UpdatedEvent updatedEvent){
        return eventService.updateEvent(updatedEvent);
    }

}

