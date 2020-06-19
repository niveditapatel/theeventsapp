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
<<<<<<< HEAD
    //event.title
    private String Host;
    //event.hostemail
    private String Venue;
    //event.place
    private String Time;
    //event.startDateTime
=======
    private String Place;
    private String Start;
    private String End;
>>>>>>> a9b7f805ba05c1c815d400d6e3c1e8e1446edb67


}