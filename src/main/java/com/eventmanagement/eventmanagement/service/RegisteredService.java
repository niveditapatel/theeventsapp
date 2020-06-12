package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.entity.EventSender;
import com.eventmanagement.eventmanagement.entity.Registered;
import com.eventmanagement.eventmanagement.repository.EventRepository;
import com.eventmanagement.eventmanagement.repository.RegisteredRepository;
import com.eventmanagement.eventmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegisteredService {

    @Autowired
    RegisteredRepository registeredRepository;

    @Autowired
    EventRepository eventRepository;

    public List<EventSender> eventsByUserId(int user_id) {

        List<Registered> registereds = registeredRepository.findByUserId(user_id);
        System.out.println(registereds);

        List<EventSender> eventSenders = new ArrayList<>();

        for(Registered registered: registereds) {
            Optional<Event> event = eventRepository.findById(registered.getEventId());
            event.ifPresent(value -> eventSenders.add(new EventSender(value.getTitle(), value.getStartDateTime())));
        }


        return eventSenders;
    }
}
