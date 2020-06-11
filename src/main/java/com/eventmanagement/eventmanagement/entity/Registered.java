package com.eventmanagement.eventmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Data
public class Registered {

    @Id
    @GeneratedValue
    @Column(name = "register_id")
    private int id;

    @Column(name = "userid")
    private int userId;
    @Column(name = "eventid")
    private int eventId;
    private String response;


}
