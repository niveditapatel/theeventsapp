package com.eventmanagement.eventmanagement.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Event_TBL")
public class Event {

    @Id
    @GeneratedValue
    @Column(name = "event_id")
    private int id;
    private String title;

    private String email;
    private String description;
    private String organization;
    private int duration;
    private Date startDateTime;
    private String type;
    private int maxParticipants;


}
