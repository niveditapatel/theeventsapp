package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.Group;
import com.eventmanagement.eventmanagement.entity.User;
import com.eventmanagement.eventmanagement.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    public List<Group> saveGroups(List<Group> groups) {
        return groupRepository.saveAll(groups);
    }


    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    List<Group> getUsersinGroup(String groupname) {
        return groupRepository.findByGroupname(groupname);
    }

}
