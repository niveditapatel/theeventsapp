package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.*;
import com.eventmanagement.eventmanagement.repository.GroupRepository;
import com.eventmanagement.eventmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserRepository userRepository;

    public String saveGroup(GroupReceiver groupReceiver) {
        Group group = new Group();
        group.setGroupName(groupReceiver.getName());
        List<User> users = getUsers(groupReceiver.getEmail());
        if(users==null)
        {
            return "failed";
        }
        getString(group, users);
        group.setUsers(users);
        group.setCreatorEmail(groupReceiver.getCreatorEmail());

        groupRepository.save(group);
        return "success";

    }

   // public List<Group> saveGroups(List<Group> groups) {
     //   return groupRepository.saveAll(groups);
    //}

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }


    public List<User> getUsers(String groupUsers) {
        ArrayList<User> users = new ArrayList<>();

        for (String email : groupUsers.split(",")) {

            Optional<User> user = userRepository.findByEmail(email);
            if (!user.isPresent())
            {
                return null;
            }
            if(!user.get().getStatus().equals("active"))
            {
                return null;
            }
            users.add(user.get());
        }

        return users;
    }

    public Group findById(int id) {
        return groupRepository.findById(id).orElse(null);
    }

    public List<String> groupNames() {
        return groupRepository.getGroupName();
    }


    private void getString(Group group, List<User> users) {
        for (User user : users) {

            String email = user.getEmail();
            String subject = "New Group: " + group.getGroupName();

            String text = "Hi " + user.getFirstName() + ",\n" +
                    "You have been added to the following group:\n" +
                    group.getGroupName() + "\n" +
                    "Created By:" + group.getCreatorEmail();

            try {
                notificationService.sendNotification(email, subject, text);
            } catch (MailException e) {
                System.out.println("mail not sent " + e);
            }
        }

    }

    public Group findGroupByUser(int user_id) {
        return groupRepository.findGroupByUser(user_id);
    }
}
