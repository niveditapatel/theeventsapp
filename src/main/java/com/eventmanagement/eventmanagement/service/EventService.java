package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.entity.EventDashboard;
import com.eventmanagement.eventmanagement.entity.EventSender;
import com.eventmanagement.eventmanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> saveEvents(List<Event> events) {
        return eventRepository.saveAll(events);
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public List<Event> findEventByHost(String email) {
        return eventRepository.findByEmail(email);
    }

    public Event getEventById(int id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event getEventByTitle(String title) {
        return eventRepository.findByTitle(title);
    }

    public String deleteEvent(int id) {
        eventRepository.deleteById(id);
        return "Event Removed";
    }

    public List<EventDashboard> findEventDashboard() throws ParseException {

        List<String> dashboard = eventRepository.findEventDashboard();
        List<EventDashboard> eventDashboardList = new ArrayList<EventDashboard>();
        for (String event : dashboard) {
            EventDashboard eventDashboard = new EventDashboard();
            String[] data = event.split(",");
            eventDashboard.setTitle(data[0]);
            eventDashboard.setPlace(data[1]);
            eventDashboard.setStartDateTime(data[2]);
            eventDashboard.setEndDateTime(data[3]);
            eventDashboard.setEmail(data[4]);
            eventDashboard.setDescription(data[5]);
            eventDashboardList.add(eventDashboard);
        }
        return eventDashboardList;
    }

    public Integer findEventsToday() {
        return eventRepository.findEventsToday();
    }

    public List<EventSender> findEventByUser(int user_id) {
        List<Event> events = eventRepository.findEventByUser(user_id);
        List<EventSender> eventSenders = new ArrayList<>();

        for(Event event: events) {
            eventSenders.add(new EventSender(event.getTitle(), event.getStartDateTime()));
        }
        return eventSenders;
    }

}
