package com.eventmanagement.eventmanagement.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue
    @Column(name = "event_id")
    private int id;
    private String title;

    private String email;
    private String description;

    private String place;

    private Date startDateTime;
    private Date endDateTime;
    private String type;
    private int maxParticipants;


<<<<<<< HEAD
    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name="event_user",
            joinColumns=@JoinColumn(name="event_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))


    private List<User> users;


    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }
    public void setEmails(List<User> users)
    {
        this.users = users;
    }

    public Event(Event event) {
        this.title = event.title;
        this.email = event.email;
        this.description=event.description;
        this.startDateTime=event.startDateTime;
        this.endDateTime=event.endDateTime;

        this.type=event.type;
        this.maxParticipants=event.maxParticipants;


    }
=======


>>>>>>> a986aa44cfa07b291dd02d9fa7bc0cf6900ab7d2
}
