package com.eventmanagement.eventmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDashboard {

    private String Title;
    //event.title
    private String Host;
    //event.hostemail
    private String Venue;
    //event.place
    private String Time;
    //event.startDateTime


}