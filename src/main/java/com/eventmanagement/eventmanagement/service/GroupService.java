package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.Group;
import com.eventmanagement.eventmanagement.entity.GroupReceiver;
import com.eventmanagement.eventmanagement.entity.User;
import com.eventmanagement.eventmanagement.repository.GroupRepository;
import com.eventmanagement.eventmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public Group saveGroup(GroupReceiver groupReceiver) {
        Group group = new Group();
        group.setGroupName(groupReceiver.getName());
        group.setUsers(getUsers(groupReceiver.getEmail()));
        group.setCreatorEmail(groupReceiver.getCreatorEmail());

        return groupRepository.save(group);
    }

    public List<Group> saveGroups(List<Group> groups) {
        return groupRepository.saveAll(groups);
    }

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    public List<User> getUsers(String groupUsers) {
        ArrayList<User> users = new ArrayList<>();

        for(String email: groupUsers.split(",")) {
            Optional<User> user = userRepository.findByEmail(email);
            user.orElseThrow(() ->  new UsernameNotFoundException("User is invalid"));

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
}
