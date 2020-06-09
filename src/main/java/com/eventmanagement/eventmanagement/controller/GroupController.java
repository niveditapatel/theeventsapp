package com.eventmanagement.eventmanagement.controller;

import com.eventmanagement.eventmanagement.entity.Group;

import com.eventmanagement.eventmanagement.entity.User;
import com.eventmanagement.eventmanagement.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/enterintogroupuser/{gid}/{uid}")
  @CrossOrigin(origins = "http://localhost:3000")
    public void enterintogroupuser(@PathVariable Integer gid, @PathVariable Integer uid) {
         groupService.enterintogroupuser(gid,uid);
    }

    @GetMapping("/getuser/{email}")
    @CrossOrigin(origins = "http://localhost:3000")
    public User findByEmail(@PathVariable String email) {
        return groupService.findByEmail(email);
    }



  /*  @GetMapping("/getusersfromstring/{groupusers}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ArrayList<String> getEmails(String groupusers) {
        return groupService.getEmails(groupusers);
    } */
}







