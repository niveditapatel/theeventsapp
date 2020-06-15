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
@CrossOrigin(origins = "*")
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
    @CrossOrigin(origins = "http://localhost:3000")
    public List<EventDashboard> findEventDashboard (@PathVariable int user_id) throws ParseException {
        return eventService.findEventDashboard(user_id);
    }

    @GetMapping("/findEventByHost/{email}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Event> findEventByHost(@PathVariable String email)
    {
        return eventService.findEventByHost(email);
    }


    @GetMapping("/getEventsToday")
    @CrossOrigin(origins = "http://localhost:3000")

    public Integer findEventsToday () {
        return eventService.findEventsToday();
    }

    @GetMapping("/getPendingEvents/{user_id}")
    public List<Event> getPendingEvents(@PathVariable int user_id) {
        return eventService.getPendingEvents(user_id);
    }

    @PostMapping("/accept/{user_id}/{event_id}")
    public void accept(@PathVariable int user_id, @PathVariable int event_id) {
         registeredService.accept(user_id, event_id);
    }

    @PostMapping("reject/{user_id}/{event_id}")
    public void reject(@PathVariable int user_id, @PathVariable int event_id) {
         registeredService.reject(user_id, event_id);
    }

    @PostMapping("updateEvent")
    public void updateEvent(@RequestBody UpdatedEvent updatedEvent){

        String title= updatedEvent.getTitle();
        String description= updatedEvent.getDescription();
        String place= updatedEvent.getPlace();
        Date startDateTime= updatedEvent.getStartDateTime();
        Date endDateTime= updatedEvent.getStartDateTime();
        String type=updatedEvent.getType();
        int event_id= updatedEvent.getEvent_id();
        eventService.updateEvent(title,description,place,type,startDateTime,endDateTime,event_id);
    }

}

