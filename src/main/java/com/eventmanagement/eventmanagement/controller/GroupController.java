package com.eventmanagement.eventmanagement.controller;

import com.eventmanagement.eventmanagement.entity.Group;

import com.eventmanagement.eventmanagement.entity.User;
import com.eventmanagement.eventmanagement.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/addGroup")
    @CrossOrigin(origins = "http://localhost:3000")
    public Group addGroup(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }

    //@PostMapping("/viewusers")
    //@CrossOrigin(origins = "http://localhost:3000")
    //public List<User> addEvents(@RequestBody List<User> events) {
       // return eventService.saveEvents(events);
    }







