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
    private String Host;
    private String Venue;
    private String Time;

}