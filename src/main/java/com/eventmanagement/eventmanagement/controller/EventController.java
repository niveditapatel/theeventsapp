package com.eventmanagement.eventmanagement.controller;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    private EventService service;

    @PostMapping("/addEvent")
    @CrossOrigin(origins = "http://localhost:3000")
    public Event addEvent(@RequestBody Event event) {
        return service.saveEvent(event);
    }

    @PostMapping("/addEvents")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Event> addEvents(@RequestBody List<Event> events) {
        return service.saveEvents(events);
    }

    @GetMapping("/events")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Event> findAllEvents() {
        return service.getEvents();
    }

    @GetMapping("/eventsById/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Event findEventById(@PathVariable int id) {
        return service.getEventById(id);
    }

    @GetMapping("/eventsByTitle/{title}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Event findEventByTitle(@PathVariable String title) {
        return service.getEventByTitle(title);
    }

    @PutMapping("/updateEvent")
    @CrossOrigin(origins = "http://localhost:3000")
    public Event updateEvent(@RequestBody Event event) {
        return service.updateEvent(event);
    }

    @DeleteMapping("/deleteEvent/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public String deleteEvent(@PathVariable int id) {
        return service.deleteEvent(id);
    }

}
