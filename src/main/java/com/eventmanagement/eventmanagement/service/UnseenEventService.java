package com.eventmanagement.eventmanagement.service;


import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.entity.EventReceiver;
import com.eventmanagement.eventmanagement.entity.UnseenEvent;
import com.eventmanagement.eventmanagement.repository.EventRepository;
import com.eventmanagement.eventmanagement.repository.UnseenEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UnseenEventService {

    @Autowired
    UnseenEventRepository unseenEventRepository;

    @Autowired
    EventRepository eventRepository;

    public int alertsCountNewEvent(int user_id) {
        return unseenEventRepository.alertsCount(user_id);
    }

    public List<Event> alertsNewEvent(int user_id) {
        List<Event> events = eventRepository.alerts(user_id);
        List<UnseenEvent> unseenEvents = unseenEventRepository.findByUserId(user_id);
        if(unseenEvents == null) return events;
        for(UnseenEvent unseenEvent:unseenEvents) {
            unseenEventRepository.deleteById(unseenEvent.getId());
        }
        return events;
    }

    public int alertsCountEventIn15Min(int user_id) {
        return unseenEventRepository.alertsCountEventIn15Min(user_id);
    }

    public List<Event> alertsEventIn15Min(int user_id) {
        return eventRepository.alertsEventIn15Min(user_id);
    }
}
