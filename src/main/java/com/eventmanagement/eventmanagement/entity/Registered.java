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

    private String userId;
    private String eventId;



}
