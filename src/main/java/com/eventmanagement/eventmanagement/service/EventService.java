package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.entity.EventDashboard;
import com.eventmanagement.eventmanagement.entity.EventSender;
import com.eventmanagement.eventmanagement.entity.UpdatedEvent;
import com.eventmanagement.eventmanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public List<Event> findEventByHost(String email) {
        return eventRepository.findByEmail(email);
    }

    public Event getEventById(int id) {
        return eventRepository.findById(id).orElse(null);
    }

    public String deleteEvent(int id) {
        eventRepository.deleteById(id);
        return "Event Removed";
    }

    public List<EventDashboard> findEventDashboard(int user_id) throws ParseException {

        List<String> dashboard = eventRepository.findEventDashboard(user_id);
        List<EventDashboard> eventDashboardList = new ArrayList<EventDashboard>();
        for (String event : dashboard) {
            EventDashboard eventDashboard = new EventDashboard();
            String[] data = event.split(",");
            eventDashboard.setTitle(data[0]);
            eventDashboard.setVenue(data[1]);
            eventDashboard.setTime(data[2]);
            eventDashboard.setHost(data[3]);
            eventDashboardList.add(eventDashboard);
        }
        return eventDashboardList;
    }

    public Integer findEventsToday(int user_id) {
        return eventRepository.findEventsToday(user_id);
    }

    public List<EventSender> findEventByUser(int user_id) {
        List<Event> events = eventRepository.findEventByUser(user_id);
        List<EventSender> eventSenders = new ArrayList<>();

        for (Event event : events) {
            eventSenders.add(new EventSender(event.getTitle(), event.getStartDateTime()));
        }
        return eventSenders;
    }

    public List<Event> getPendingEvents(int user_id) {
        return eventRepository.findPendingResponse(user_id);
    }

   public String updateEvent(UpdatedEvent updatedEvent) {
       Optional<Event> optionalEvent = eventRepository.findById(updatedEvent.getEvent_id());
       if(!optionalEvent.isPresent()) {
           return "Failed Cannot ";
       }
       Event event = optionalEvent.get();
       event.setTitle(updatedEvent.getTitle());
       event.setDescription(updatedEvent.getDescription());
       event.setPlace(updatedEvent.getPlace());
       event.setStartDateTime(updatedEvent.getStartDateTime());
       event.setEndDateTime(updatedEvent.getEndDateTime());
       event.setType(updatedEvent.getType());
       eventRepository.save(event);
       return "success";
   }

    public List<Event> findEventForUser(int user_id) {
       return eventRepository.findEventForUser(user_id);
    }
}