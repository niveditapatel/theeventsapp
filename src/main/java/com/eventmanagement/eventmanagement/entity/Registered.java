package com.eventmanagement.eventmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Registered {

    @Id
    @GeneratedValue
    private int id;

    private String userid;
    private String eventId;
    private String response;


}
