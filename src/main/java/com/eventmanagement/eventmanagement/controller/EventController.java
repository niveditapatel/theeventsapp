package com.eventmanagement.eventmanagement.controller;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "*")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/addEvent")
    public Event addEvent(@RequestBody Event event) {
        return eventService.saveEvent(event);
    }

    @PostMapping("/addEvents")
    public List<Event> addEvents(@RequestBody List<Event> events) {
        return eventService.saveEvents(events);
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

    @GetMapping("/eventByTitle/{title}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Event findEventByTitle(@PathVariable String title) {
        return eventService.getEventByTitle(title);
    }

  /*  @PutMapping("/updateEvent")
    public Event updateEvent(@RequestBody Event event) {
        return eventService.updateEvent(event);
    }*/

    @DeleteMapping("/deleteEvent/{id}")
    public String deleteEvent(@PathVariable int id) {
        return eventService.deleteEvent(id);
    }

    @GetMapping("/getEventDashboard")
    public String findEventDashboard () {
        return eventService.findEventDashboard();
    }

    @GetMapping("/getEventsToday")
    public Integer findEventsToday () {
        return eventService.findEventstoday();
    }

}
