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


    private String title;
    private String email;
    private String description;
    private String place;
    private String startDateTime;
    private String endDateTime;




}