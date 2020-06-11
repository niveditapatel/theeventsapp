package com.eventmanagement.eventmanagement.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String organization;
    private String place;
    private int duration;
    private Date startDateTime;
    private String type;
    private int maxParticipants;




}
