package com.eventmanagement.eventmanagement.controller;

import com.eventmanagement.eventmanagement.entity.Group;
import com.eventmanagement.eventmanagement.entity.Role;
import com.eventmanagement.eventmanagement.entity.User;
import com.eventmanagement.eventmanagement.service.GroupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "patelnivedita@icloud.com", password = "niv", roles = "CREATOR")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GroupControllerTest {
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    GroupService groupService;

    @Autowired
    private WebApplicationContext context;


        public static String asJsonString(final Object obj) {
            try {
                final ObjectMapper mapper = new ObjectMapper();
                final String jsonContent = mapper.writeValueAsString(obj);
                return jsonContent;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }

        @BeforeEach
        void setUp() {

            mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        }

    @Test
    void saveGroup() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User(1,"patelnivedita@icloud.com", "sd3edwfsdfdsfsdf", "Niv", "Patel", "active", new Role(1, "ADMIN")));
        users.add(new User(2,"17bce084@nirmauni.ac.in", "dsfv", "Niv", "Patel", "active", new Role(3, "USER")));
        Group group = new Group(1,"NewGroup", "patelnivedita@icloud.com",users);

        when(groupService.saveGroup(any())).thenReturn("success");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/addGroup")
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(group))
                .contentType(MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals("success",content);
    }

    @Test
    void findGroupById() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User(1,"patelnivedita@icloud.com", "sd3edwfsdfdsfsdf", "Niv", "Patel", "active", new Role(1, "ADMIN")));
        users.add(new User(2,"17bce084@nirmauni.ac.in", "dsfv", "Niv", "Patel", "active", new Role(3, "USER")));
        Group group = new Group(1,"NewGroup", "patelnivedita@icloud.com",users);
        when(groupService.findById(anyInt())).thenReturn(group);
        MvcResult result = this.mockMvc
                .perform(get("/api/getGroupById/{id}", 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals(asJsonString(group),content);
    }

    @Test
    void findGroupByUser() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User(1,"patelnivedita@icloud.com", "sd3edwfsdfdsfsdf", "Niv", "Patel", "active", new Role(1, "ADMIN")));
        users.add(new User(2,"17bce084@nirmauni.ac.in", "dsfv", "Niv", "Patel", "active", new Role(3, "USER")));
        List<Group> groups = new ArrayList<>();
        groups.add(new Group(1,"NewGroup1", "patelnivedita@icloud.com",users));
        groups.add(new Group(2,"NewGroup2", "patelnivedita@icloud.com",users));
        when(groupService.findGroupByUser(anyInt())).thenReturn(groups);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/getGroupByUser/{user_id}",1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals(asJsonString(groups),content);
    }

    @Test
    void groups() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User(1,"patelnivedita@icloud.com", "sd3edwfsdfdsfsdf", "Niv", "Patel", "active", new Role(1, "ADMIN")));
        users.add(new User(2,"17bce084@nirmauni.ac.in", "dsfv", "Niv", "Patel", "active", new Role(3, "USER")));
        List<Group> groups = new ArrayList<Group>();
        groups.add( new Group(1,"NewGroup1", "patelnivedita@icloud.com",users));
        groups.add(  new Group(2,"NewGroup2", "patelnivedita@icloud.com",users));
        when(groupService.getGroups()).thenReturn(groups);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/getGroups")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals(asJsonString(groups),content);
    }

    @Test
    void groupNames() throws Exception {
        List<String> groupnames = new ArrayList<>();
        groupnames.add("interns");
        groupnames.add("buddies");
        when(groupService.groupNames()).thenReturn(groupnames);
        MvcResult result = this.mockMvc
                .perform(get("/api/getGroupNames"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals(asJsonString(groupnames),content);


    }
}