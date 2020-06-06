package com.eventmanagement.eventmanagement.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Event_TBL")
public class Event {

    @Id
    @GeneratedValue
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
