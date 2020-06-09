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
    public Group saveGroup(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }

   @GetMapping("/getuser/{email}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<User> findByEmail(@PathVariable String email) {
      return groupService.findByEmail(email);
   }
    }







