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

<<<<<<< HEAD
    private String userId;
    private String eventId;

=======
    @Column(name = "userid")
    private int userId;
    @Column(name = "eventid")
    private int eventId;
    private String response;
>>>>>>> a986aa44cfa07b291dd02d9fa7bc0cf6900ab7d2


}
