package com.eventmanagement.eventmanagement.controller;

import com.eventmanagement.eventmanagement.entity.Role;
import com.eventmanagement.eventmanagement.entity.User;
import com.eventmanagement.eventmanagement.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("User Controller Test")
class UserControllerTest {
    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UserService userService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @WithMockUser(username = "mufaddal.naya@gmail.com", roles = "ADMIN")
    @Test
    void getUsers() throws Exception {
        List<User> userList = getUsersList();
        when(userService.getUsers()).thenReturn(userList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getUsers");
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse()).isNotNull();
    }

    private List<User> getUsersList() {
        User user1 = User.builder()
                .email("mufaddal.naya@gmail.com")
                .firstName("Mufaddal")
                .lastName("Naya")
                .password("dsfsd")
                .status("active")
                .role(new Role(1, "ADMIN"))
                .id(1)
                .build();

        User user2 = User.builder()
                .email("naya.naya@gmail.com")
                .firstName("Naya")
                .lastName("Naya")
                .password("dsfdsfdsd")
                .status("active")
                .role(new Role(3, "USER"))
                .id(3)
                .build();
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        return userList;
    }

    @WithMockUser(username = "mufaddal.naya@gmail.com", roles = "USER")
    @Test
    void getEmails() throws Exception {
        List<String> emails = new ArrayList<>();
        emails.add("mufaddal.naya@gmail.com");
        emails.add("naya.naya@gmail.com");
        when(userService.getEmails()).thenReturn(emails);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getEmails");
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse()).isNotNull();
    }

    @WithMockUser(username = "mufaddal.naya@gmail.com", roles = "CREATOR")
    @Test
    void addUser() throws Exception {
        User user = getUsersList().get(0);

        when(userService.saveUser(any())).thenReturn("success");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/addUser")
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse()).isNotNull();
    }

    @WithMockUser(username = "mufaddal.naya@gmail.com", roles = "USER")
    @Test
    void findByEmail() throws Exception {
        User user = getUsersList().get(0);
        when(userService.getUserByEmail("mufaddal.naya@gmail.com")).thenReturn(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/userByEmail/mufaddal.naya@gmail.com");
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse()).isNotNull();
    }

    @Test
    void verify() throws Exception {
        when(userService.verify("abc")).thenReturn("invalid URL");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/verify/abc");
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse()).isNotNull();
        assertThat(result.getResponse().getContentAsString()).isEqualTo("invalid URL");
    }
}