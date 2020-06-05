package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {


    @Autowired
    private EventRepository repository;

    public Event saveEvent(Event event) {
        return repository.save(event);
    }

    public List<Event> saveEvents(List<Event> events) {
        return repository.saveAll(events);
    }

    public List<Event> getEvents() {
        return repository.findAll();
    }

    public Event getEventById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Event getEventByTitle(String title) {
        return repository.findByTitle(title);
    }

    public String deleteEvent(int id) {
        repository.deleteById(id);
        return "Event Removed";
    }

    public Event updateEvent(Event event) {
        Event existingEvent = repository.findById(event.getId()).orElse(null);
        existingEvent.setTitle(event.getTitle());
        existingEvent.setDescription(event.getDescription());
        existingEvent.setOrganization(event.getOrganization());
        return repository.save(existingEvent);
    }

}
