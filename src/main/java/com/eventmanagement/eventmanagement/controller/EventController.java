package com.eventmanagement.eventmanagement.controller;

import com.eventmanagement.eventmanagement.entity.Event;
<<<<<<< HEAD
import com.eventmanagement.eventmanagement.entity.EventDashboard;
=======
import com.eventmanagement.eventmanagement.entity.EventSender;
>>>>>>> a986aa44cfa07b291dd02d9fa7bc0cf6900ab7d2
import com.eventmanagement.eventmanagement.service.EventService;
import com.eventmanagement.eventmanagement.service.RegisteredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.text.ParseException;
=======
import java.security.Principal;
>>>>>>> a986aa44cfa07b291dd02d9fa7bc0cf6900ab7d2
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

    @GetMapping("/eventsByUserId/{user_id}")
    public List<EventSender> eventsByUserId(@PathVariable int user_id) {
        return registeredService.eventsByUserId(user_id);
    }

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

<<<<<<< HEAD

    @GetMapping("/geteventdashboard")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<EventDashboard> findEventDashboard () throws ParseException {
        return eventService.findEventDashboard();
    }

    @GetMapping("/findEventByHost/{email}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Event> findEventByHost(@PathVariable String email)
    {
        return eventService.findEventByHost(email);
    }

    @GetMapping("/findEventByUser/{user_id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Event> findEventByUser(@PathVariable String user_id)
    {
        return eventService.findEventByUser(user_id);
    }

    @GetMapping("/geteventstoday")
    @CrossOrigin(origins = "http://localhost:3000")
=======
    @GetMapping("/getEventDashboard")
    public String findEventDashboard () {
        return eventService.findEventDashboard();
    }

    @GetMapping("/getEventsToday")
>>>>>>> a986aa44cfa07b291dd02d9fa7bc0cf6900ab7d2
    public Integer findEventsToday () {
        return eventService.findEventstoday();
    }



}

/*    @GetMapping("/findEventByHost/{email}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Event> findEventByHost(@PathVariable String email)
    {
        return eventService.findEventByHost(email);
    */

