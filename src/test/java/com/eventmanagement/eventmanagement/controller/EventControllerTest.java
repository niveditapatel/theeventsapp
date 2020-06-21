package com.eventmanagement.eventmanagement.controller;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.entity.EventReceiver;
import com.eventmanagement.eventmanagement.entity.EventSender;
import com.eventmanagement.eventmanagement.entity.UpdatedEvent;
import com.eventmanagement.eventmanagement.service.EventService;
import com.eventmanagement.eventmanagement.service.RegisteredService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "patelnivedita@icloud.com", password = "niv", roles = "CREATOR")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EventControllerTest {
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @MockBean
    EventService eventService;
    @MockBean
    RegisteredService registeredService;
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
    void index() throws Exception {
        RequestBuilder requestBuilder = get("/hello");
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
//        assertEquals("Hello From Event Management API Spring Boot Application", result.getResponse().getContentAsString());
    }

    @Test
    void addEvent() throws Exception {
        Date startdate = new Date();
        Date enddate = new Date();


        EventReceiver event = new EventReceiver("Meeting1", "patelnivedita@icloud.com", "descriptionofmeeting", "place", startdate, enddate, "conference", "public", "");

        when(registeredService.addEvent(event)).thenReturn("success");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/addEvent")
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(event))
                .contentType(MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        // assertEquals("success",content);

    }


    @Test
    void eventsByUserId() throws Exception {
        List<EventSender> events = new ArrayList<EventSender>();
        Date startdate = new Date();
        events.add(new EventSender("Meeting", startdate));
        events.add(new EventSender("Meeting2", startdate));
        when(eventService.findEventByUser(anyInt())).thenReturn(events);
        MvcResult result = this.mockMvc
                .perform(get("/api/eventsByUserId/{user_id}", "1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        System.out.println(content);

    }


    @Test
    void eventsForUser() throws Exception {
        Date startdate = new Date();
        Date enddate = new Date();
        Event event = new Event(1, "Meeting", "patelnivedita@icloud.com", "descriptionofmeeting", "place", startdate, enddate, "conference");
        List<Event> events = new ArrayList<Event>();
        events.add(event);
        when(eventService.findEventForUser(anyInt())).thenReturn(events);
        MvcResult result = this.mockMvc
                .perform(get("/api/eventsForUser/{user_id}", "1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals(asJsonString(events), content);
    }


    @Test
    void findEventById() throws Exception {
        Date startdate = new Date();
        Date enddate = new Date();
        Event event = new Event(1, "Meeting", "patelnivedita@icloud.com", "descriptionofmeeting", "place", startdate, enddate, "conference");
        when(eventService.getEventById(anyInt())).thenReturn(event);
        MvcResult result = this.mockMvc
                .perform(get("/api/eventById/{id}", "1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals(asJsonString(event), content);
        //  JSONAssert.assertEquals( "{id:\"1\"}",content, JSONCompareMode.LENIENT);
    }


    @Test
    void deleteEvent() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/deleteEvent/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void findEventDashboard() throws Exception {
        //  List <EventDashboard> eventDashboardList = null;
        //  eventDashboardList.add(new EventDashboard("Meeting","patelnivedita@icloud.com","place", "2020-06-19 00:00:00"));
        //  eventDashboardList.add(new EventDashboard("Meeting2","patelnivedita@icloud.com","place", "2020-06-19 00:00:00"));
        // when(eventService.findEventDashboard(anyInt())).thenReturn(eventDashboardList);
        MvcResult result = this.mockMvc
                .perform(get("/api/getEventDashboard/{user_id}", "1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        // assertEquals(asJsonString(eventDashboardList),content);
    }

    @Test
    void findEventByHost() throws Exception {
        Date startdate = new Date();
        Date enddate = new Date();
        Event event = new Event(1, "Meeting", "patelnivedita@icloud.com", "descriptionofmeeting", "place", startdate, enddate, "conference");
        List<Event> events = new ArrayList<Event>();
        events.add(event);
        when(eventService.findEventByHost(anyString())).thenReturn(events);
        MvcResult result = this.mockMvc
                .perform(get("/api/findEventByHost/{email}", "patelnivedita@icloud.com"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals(asJsonString(events), content);

    }

    @Test
    void findEventsToday() throws Exception {
        when(eventService.findEventsToday()).thenReturn(0);
        MvcResult result = this.mockMvc
                .perform(get("/api/getEventsToday"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        System.out.println(content);
        assertEquals("0", content);
    }

    @Test
    void getPendingEvents() throws Exception {
        Date startdate = new Date();
        Date enddate = new Date();
        Event event = new Event(1, "Meeting", "patelnivedita@icloud.com", "descriptionofmeeting", "place", startdate, enddate, "conference");
        List<Event> events = new ArrayList<Event>();
        when(eventService.getPendingEvents(anyInt())).thenReturn(events);
        events.add(event);
        MvcResult result = this.mockMvc
                .perform(get("/api/getPendingEvents/{user_id}", "1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        System.out.println(content);
        assertEquals(asJsonString(events), content);
    }

    @Test
    void updateEvent() throws Exception {

        Date startdate = new Date();
        Date enddate = new Date();
        UpdatedEvent event = new UpdatedEvent(1, "Meeting", "patelnivedita@icloud.com", "descriptionofmeeting", "place", startdate, enddate, "conference");

        // when(eventService.updateEvent(event).thenReturn();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/updateEvent")
                .content(asJsonString(event))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

    }
}