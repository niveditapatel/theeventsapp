package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.*;
import com.eventmanagement.eventmanagement.repository.EventRepository;
import com.eventmanagement.eventmanagement.repository.GroupRepository;
import com.eventmanagement.eventmanagement.repository.RegisteredRepository;
import com.eventmanagement.eventmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
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
    @Autowired
    UserRepository userRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    NotificationService notificationService;
    private final String SUCCESS = "success";
    private final String FAILED = "failed";

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

        if (eventReceiver.getTarget().equals("public")) {
            return addPublicEvent(event);
        } else if (eventReceiver.getTarget().equals("group")) {
            return addGroupEvent(eventReceiver.getAction(), event);
        } else if (eventReceiver.getTarget().equals("emails")) {
            return addEmailsEvent(eventReceiver.getAction().split(","), event);
        }

        eventRepository.deleteById(event.getId());
        return FAILED;
    }

    private String addEmailsEvent(String[] emails, Event event) {
        List<User> users = new ArrayList<>();
        for (String email : emails) {
            Optional<User> optionalUser = userRepository.findByEmail(email);
            if (!optionalUser.isPresent()) {
                eventRepository.deleteById(event.getId());
                return FAILED;
            }
            users.add(optionalUser.get());
        }

        getString(event, users);
        return SUCCESS;
    }

    private String addGroupEvent(String groupName, Event event) {

        Optional<Group> optionalGroup = groupRepository.findByGroupName(groupName);
        if (!optionalGroup.isPresent()) {
            eventRepository.deleteById(event.getId());
            return FAILED;
        }
        List<User> users = optionalGroup.get().getUsers();

        return getString(event, users);
    }

    private String getString(Event event, List<User> users) {
        for (User user : users) {
            if(!user.getStatus().equals("active"))
            {
                return FAILED;
            }
            if (!user.getEmail().equals(event.getEmail())) {
                String email = user.getEmail();
                String subject = "New Event: " + event.getTitle();

                String text = "Hi " + user.getFirstName() + ",\n" +
                        "You have been invited to the following event:\n" +
                        event.getTitle() + "\n" +
                        event.getDescription() + "\n Starting On:" +
                        event.getStartDateTime() + " to " + event.getEndDateTime() +
                        "\n at: " +
                        event.getPlace() + "\n" + "Hosted By:"
                        + event.getEmail();
                Registered registered = new Registered();
                registered.setResponse("accept");
                registered.setEventId(event.getId());
                registered.setUserId(user.getId());
                //send email to each user;
                try {
                    notificationService.sendNotification(email, subject, text);
                } catch (MailException e) {
                    System.out.println("mail not sent " + e);
                }
            }
        }

        return SUCCESS;
    }

    private String addPublicEvent(Event event) {

        List<User> users = userRepository.findAll();

        return getString(event, users);
    }
}
