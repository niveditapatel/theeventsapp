package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

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

   public Event updateEvent(Event event) {
        Event existingEvent = eventRepository.findById(event.getId()).orElse(null);
        existingEvent.setTitle(event.getTitle());
        existingEvent.setDescription(event.getDescription());
        existingEvent.setOrganization(event.getOrganization());
        return eventRepository.save(existingEvent);
    }



}
