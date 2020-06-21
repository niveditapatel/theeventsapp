package com.eventmanagement.eventmanagement.controller;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.entity.UnseenEvent;
import com.eventmanagement.eventmanagement.entity.User;
import com.eventmanagement.eventmanagement.service.UnseenEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("UnSeen Event Controller Test")
class UnseenEventControllerTest {
    @Autowired
    private WebApplicationContext context;

    @MockBean
    UnseenEventService unseenEventService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(username = "mufaddal.naya@gmail.com", roles = "ADMIN")
    @Test
    void alertsCountNewEvent() throws Exception {

        when(unseenEventService.alertsCountNewEvent(1)).thenReturn(3);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/alertsCountNewEvent/1");
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
        assertThat(result.getResponse()).isNotNull();
        assertThat(result.getResponse().getContentAsString()).isEqualTo("3");
    }

    @WithMockUser(username = "mufaddal.naya@gmail.com", roles = "ADMIN")
    @Test
    void alertsCountEventIn15Min() throws Exception {
        when(unseenEventService.alertsCountEventIn15Min(1)).thenReturn(5);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/alertsCountEventIn15Min/1");
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        assertThat(result).isNotNull();
        assertThat(result.getResponse().getContentAsString()).isEqualTo("5");
    }

    @WithMockUser(username = "mufaddal.naya@gmail.com", roles = "ADMIN")
    @Test
    void alertsNewEvent() throws Exception {
        List<Event> eventList = getEvents();
        when(unseenEventService.alertsNewEvent(1)).thenReturn(eventList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/alertsNewEvent/1");
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result).isNotNull();
    }

    @WithMockUser(username = "mufaddal.naya@gmail.com", roles = "ADMIN")
    @Test
    void alertsEventIn15Min() throws Exception {
        List<Event> eventList = getEvents();
        when(unseenEventService.alertsEventIn15Min(1)).thenReturn(eventList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/alertsEventIn15Min/1");
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result).isNotNull();
    }

    private List<Event> getEvents() {

        Event event1 = Event.builder()
                .description("Hello World")
                .email("mufaddal.naya@gmail.com")
                .title("Title")
                .endDateTime(new Date())
                .startDateTime(new Date())
                .place("Meet")
                .build();
        Event event2 = Event.builder()
                .description("Hello World1")
                .email("mufaddal.naya@gmail.com")
                .title("Title1")
                .endDateTime(new Date())
                .startDateTime(new Date())
                .place("Meet1")
                .build();
        Event event3 = Event.builder()
                .description("Hello World2")
                .email("mufaddal.naya@gmail.com")
                .title("Title2")
                .endDateTime(new Date())
                .startDateTime(new Date())
                .place("Meet2")
                .build();

        List<Event> eventList = new ArrayList<>();
        eventList.add(event1);
        eventList.add(event2);
        eventList.add(event3);
        return eventList;
    }
}