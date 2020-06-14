package com.eventmanagement.eventmanagement.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startDateTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endDateTime;
    private String type;


}
