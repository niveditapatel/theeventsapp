package com.eventmanagement.eventmanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startDateTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endDateTime;
    private String type;
    private String target;
    private String action;

}
