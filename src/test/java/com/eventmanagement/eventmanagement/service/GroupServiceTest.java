package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.Group;
import com.eventmanagement.eventmanagement.entity.Role;
import com.eventmanagement.eventmanagement.entity.User;
import com.eventmanagement.eventmanagement.repository.GroupRepository;
import com.eventmanagement.eventmanagement.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class GroupServiceTest {

    @InjectMocks
    GroupService groupService;

    @Mock
    GroupRepository groupRepository;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveGroup() {
    }


    @Test
    void getGroups() {
        List<User> users = new ArrayList<>();
        users.add(new User(1,"patelnivedita@icloud.com", "sd3edwfsdfdsfsdf", "Niv", "Patel", "active", new Role(1, "ADMIN")));
        users.add(new User(2,"17bce084@nirmauni.ac.in", "dsfv", "Niv", "Patel", "active", new Role(3, "USER")));
        List<Group> groups =new ArrayList<>();
       groups.add(new Group(1,"NewGroup1", "patelnivedita@icloud.com",users));
        groups.add (new Group(2,"NewGroup2", "patelnivedita@icloud.com",users));
        when(groupRepository.findAll()).thenReturn(groups);
        List<Group> groupDB= groupService.getGroups();
        assertEquals(2,groupDB.size());


    }

    @Test
    void getUsers() {
        String groupusers="patelnivedita@icloud.com,17bce084@nirmauni.ac.in";


       User user1= (new User(1,"patelnivedita@icloud.com", "sd3edwfsdfdsfsdf", "Niv", "Patel", "active", new Role(1, "ADMIN")));
       User user2= (new User(2,"17bce084@nirmauni.ac.in", "dsfv", "Niv", "Patel", "active", new Role(3, "USER")));
       when(userRepository.findByEmail("patelnivedita@icloud.com")).thenReturn(Optional.of(user1));
        when(userRepository.findByEmail("17bce084@nirmauni.ac.in")).thenReturn(Optional.of(user2));
        List<User> users= groupService.getUsers(groupusers);
       Assertions.assertEquals(2,users.size());

    }

    @Test
    void findById() {
        List<User> users = new ArrayList<>();
        users.add(new User(1,"patelnivedita@icloud.com", "sd3edwfsdfdsfsdf", "Niv", "Patel", "active", new Role(1, "ADMIN")));
        users.add(new User(2,"17bce084@nirmauni.ac.in", "dsfv", "Niv", "Patel", "active", new Role(3, "USER")));
        Group group = new Group(1,"NewGroup", "patelnivedita@icloud.com",users);
        when(groupRepository.findById(anyInt())).thenReturn(Optional.of(group));
        Group groupDB = groupService.findById(1);
        assertNotNull(groupDB);
        Assertions.assertEquals("NewGroup", groupDB.getGroupName());
    }

    @Test
    void groupNames() {
        List<String> groupnames = new ArrayList<>();
        groupnames.add("interns");
        groupnames.add("buddies");
        groupnames.add("members");
        when(groupRepository.getGroupName()).thenReturn(groupnames);
        List<String> groupnamesDB= groupService.groupNames();
        assertEquals(3,groupnamesDB.size());

    }

    @Test
    void findGroupByUser() {
        List<User> users = new ArrayList<>();
        users.add(new User(1,"patelnivedita@icloud.com", "sd3edwfsdfdsfsdf", "Niv", "Patel", "active", new Role(1, "ADMIN")));
        users.add(new User(2,"17bce084@nirmauni.ac.in", "dsfv", "Niv", "Patel", "active", new Role(3, "USER")));
        List<Group> groups = new ArrayList<>();
        groups.add(new Group(1,"NewGroup1", "patelnivedita@icloud.com",users));
        groups.add(new Group(2,"NewGroup2", "patelnivedita@icloud.com",users));

        when(groupRepository.findGroupByUser(anyInt())).thenReturn(groups);
        List<Group> groupDB = groupService.findGroupByUser(1);
        assertNotNull(groupDB);
        Assertions.assertEquals(2, groupDB.size());

    }
}