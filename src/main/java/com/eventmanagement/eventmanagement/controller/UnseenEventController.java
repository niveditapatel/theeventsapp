package com.eventmanagement.eventmanagement.controller;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.repository.UnseenEventRepository;
import com.eventmanagement.eventmanagement.service.UnseenEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UnseenEventController {

    @Autowired
    UnseenEventService unseenEventService;

    @GetMapping("/alertsCountNewEvent/{user_id}")
    private int alertsCountNewEvent(@PathVariable int user_id) {
        return unseenEventService.alertsCountNewEvent(user_id);
    }

    @GetMapping("/alertsNewEvent/{user_id}")
    private List<Event> alertsNewEvent(@PathVariable int user_id) {
        return unseenEventService.alertsNewEvent(user_id);
    }

    @GetMapping("/alertsCountEventIn15Min/{user_id}")
    private int alertsCountEventIn15Min(@PathVariable int user_id) {
        return unseenEventService.alertsCountEventIn15Min(user_id);
    }

    @GetMapping("/alertsEventIn15Min/{user_id}")
    private List<Event> alertsEventIn15Min(@PathVariable int user_id) {
        return unseenEventService.alertsEventIn15Min(user_id);
    }

}
