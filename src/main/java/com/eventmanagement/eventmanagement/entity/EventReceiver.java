package com.eventmanagement.eventmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventReceiver {

    private String title;
    private String email;
    private String description;
    private String place;
    private Date startDateTime;
    private Date endDateTime;
    private String type;
    private String target;
    private String action;

}
