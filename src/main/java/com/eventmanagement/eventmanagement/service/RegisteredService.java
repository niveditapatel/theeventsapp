package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.*;
import com.eventmanagement.eventmanagement.repository.EventRepository;
import com.eventmanagement.eventmanagement.repository.GroupRepository;
import com.eventmanagement.eventmanagement.repository.RegisteredRepository;
import com.eventmanagement.eventmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegisteredService {

    private String SUCCESS = "success";
    private String FAILED = "failed";

    @Autowired
    RegisteredRepository registeredRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GroupRepository groupRepository;

    public String addEvent(EventReceiver eventReceiver) {

        Event event = new Event();
        event.setType(eventReceiver.getType());
        event.setPlace(eventReceiver.getPlace());
        event.setStartDateTime(eventReceiver.getStartDateTime());
        event.setEndDateTime(eventReceiver.getEndDateTime());
        event.setEmail(eventReceiver.getEmail());
        event.setDescription(eventReceiver.getDescription());
        event.setTitle(eventReceiver.getTitle());
        event.setType(eventReceiver.getType());

        event = eventRepository.save(event);

        if(eventReceiver.getTarget().equals("public")) {
            return addPublicEvent(event);
        } else if(eventReceiver.getTarget().equals("group")) {
            return addGroupEvent(eventReceiver.getAction(), event);
        } else if(eventReceiver.getTarget().equals("emails")) {
            return addEmailsEvent(eventReceiver.getAction().split(","),event);
        }

        eventRepository.deleteById(event.getId());
        return FAILED;
    }

    private String addEmailsEvent(String[] emails, Event event) {
        List<User> users = new ArrayList<>();
        for(String email:emails) {
            Optional<User> optionalUser = userRepository.findByEmail(email);
            if(!optionalUser.isPresent()) {
                eventRepository.deleteById(event.getId());
                return FAILED;
            }
            users.add(optionalUser.get());
        }

        for(User user:users) {
            if(!user.getEmail().equals(event.getEmail())) {
                Registered registered = new Registered();
                registered.setResponse("accept");
                registered.setEventId(event.getId());
                registered.setUserId(user.getId());
            }
        }

        return SUCCESS;
    }

    private String addGroupEvent(String groupName, Event event) {

        Optional<Group> optionalGroup = groupRepository.findByGroupName(groupName);
        if(!optionalGroup.isPresent()) {
            eventRepository.deleteById(event.getId());
            return FAILED;
        }
        List<User> users = optionalGroup.get().getUsers();

        for(User user:users) {
            if(!user.getEmail().equals(event.getEmail())) {
                Registered registered = new Registered();
                registered.setUserId(user.getId());
                registered.setEventId(event.getId());
                registered.setResponse("accept");
                registeredRepository.save(registered);
            }
        }

        return SUCCESS;
    }

    private String addPublicEvent(Event event) {

        List<User> users = userRepository.findAll();

        for(User user:users) {
            if(!user.getEmail().equals(event.getEmail())) {
                Registered registered = new Registered();
                registered.setUserId(user.getId());
                registered.setEventId(event.getId());
                registered.setResponse("accept");
                registeredRepository.save(registered);
            }
        }

        return SUCCESS;
    }
}
