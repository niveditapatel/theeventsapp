package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.*;
import com.eventmanagement.eventmanagement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    UnseenEventRepository unseenEventRepository;


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
        System.out.println(Arrays.toString(emails));
        System.out.println(event);
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
                UnseenEvent unseenEvent = new UnseenEvent();
                unseenEvent.setUserId(user.getId());
                unseenEvent.setEventId(event.getId());
                unseenEventRepository.save(unseenEvent);
                Registered registered = new Registered();
                registered.setResponse("pending");
                registered.setEventId(event.getId());
                registered.setUserId(user.getId());
                registeredRepository.save(registered);
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
                UnseenEvent unseenEvent = new UnseenEvent();
                unseenEvent.setUserId(user.getId());
                unseenEvent.setEventId(event.getId());
                unseenEventRepository.save(unseenEvent);
                Registered registered = new Registered();
                registered.setUserId(user.getId());
                registered.setEventId(event.getId());
                registered.setResponse("pending");
                registeredRepository.save(registered);
            }
        }

        return SUCCESS;
    }

    private String addPublicEvent(Event event) {

        List<User> users = userRepository.findAll();

        for(User user:users) {
            if(!user.getEmail().equals(event.getEmail())) {
                UnseenEvent unseenEvent = new UnseenEvent();
                unseenEvent.setUserId(user.getId());
                unseenEvent.setEventId(event.getId());
                unseenEventRepository.save(unseenEvent);
                Registered registered = new Registered();
                registered.setUserId(user.getId());
                registered.setEventId(event.getId());
                registered.setResponse("pending");
                registeredRepository.save(registered);
            }
        }

        return SUCCESS;
    }

    public void accept(int user_id, int event_id) {
        Optional<Registered> optionalRegistered = registeredRepository.get(user_id, event_id);
        if(!optionalRegistered.isPresent()) return;
        Registered registered = optionalRegistered.get();
        registered.setResponse("accept");
        registeredRepository.save(registered);
    }

    public void reject(int user_id, int event_id) {
        Optional<Registered> optionalRegistered = registeredRepository.get(user_id, event_id);
        if(!optionalRegistered.isPresent()) return;
        Registered registered = optionalRegistered.get();
        registered.setResponse("reject");
        registeredRepository.save(registered);
    }

}
