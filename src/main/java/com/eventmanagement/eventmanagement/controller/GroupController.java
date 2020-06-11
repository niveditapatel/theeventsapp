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
    public Group saveGroup(@RequestBody GroupReceiver groupReceiver) {
        return groupService.saveGroup(groupReceiver);
    }

    @GetMapping("/getGroupById/{id}")
    public Group findGroupById(@PathVariable int id) {
        return groupService.findById(id);
    }

    @GetMapping("/getGroups")
    public List<Group> groups() {
        return groupService.getGroups();
    }


//    @PostMapping("/enterIntoGroupUser/{gid}/{uid}")
//  @CrossOrigin(origins = "http://localhost:3000")
//    public void enterIntoGroupUser(@PathVariable Integer gid, @PathVariable Integer uid) {
//         groupService.enterintogroupuser(gid,uid);
//    }

    @GetMapping("/userByEmail/{email}")
    public User findByEmail(@PathVariable String email) {
        return groupService.findByEmail(email);
    }



  /*  @GetMapping("/getusersfromstring/{groupusers}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ArrayList<String> getEmails(String groupusers) {
        return groupService.getEmails(groupusers);
    } */
}







