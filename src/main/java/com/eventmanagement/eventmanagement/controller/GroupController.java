package com.eventmanagement.eventmanagement.controller;

import com.eventmanagement.eventmanagement.entity.Group;

import com.eventmanagement.eventmanagement.entity.GroupReceiver;
import com.eventmanagement.eventmanagement.entity.User;
import com.eventmanagement.eventmanagement.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/addGroup")
    public String saveGroup(@RequestBody GroupReceiver groupReceiver) {
        return groupService.saveGroup(groupReceiver);
    }

    @GetMapping("/getGroupById/{id}")
    public Group findGroupById(@PathVariable int id) {
        return groupService.findById(id);
    }

    @GetMapping("/getGroupByUser/{user_id}")
    public Group findGroupByUser(@PathVariable int user_id) {
       return groupService.findGroupByUser(user_id);
    }

    @GetMapping("/getGroups")
    public List<Group> groups() {
        return groupService.getGroups();
    }

    @GetMapping("/getGroupNames")
    public List<String> groupNames() {
        return groupService.groupNames();
    }

}







