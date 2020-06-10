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
    private EventService eventService;

    @PostMapping("/addEvent")
    @CrossOrigin(origins = "http://localhost:3000")
    public Event addEvent(@RequestBody Event event) {
        return eventService.saveEvent(event);
    }

    @PostMapping("/addEvents")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Event> addEvents(@RequestBody List<Event> events) {
        return eventService.saveEvents(events);
    }

    @GetMapping("/events")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Event> findAllEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/eventById/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Event findEventById(@PathVariable int id) {
        return eventService.getEventById(id);
    }

    @GetMapping("/eventByTitle/{title}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Event findEventByTitle(@PathVariable String title) {
        return eventService.getEventByTitle(title);
    }

  /*  @PutMapping("/updateEvent")
    @CrossOrigin(origins = "http://localhost:3000")
    public Event updateEvent(@RequestBody Event event) {
        return eventService.updateEvent(event);
    }*/

    @DeleteMapping("/deleteEvent/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public String deleteEvent(@PathVariable int id) {
        return eventService.deleteEvent(id);
    }


    @GetMapping("/geteventdashboard")
    @CrossOrigin(origins = "http://localhost:3000")
    public String findEventDashboard () {
        return eventService.findEventDashboard();
    }

    @GetMapping("/geteventstoday")
    @CrossOrigin(origins = "http://localhost:3000")
    public Integer findEventsToday () {
        return eventService.findEventstoday();
    }

}
