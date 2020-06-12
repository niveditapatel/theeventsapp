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
public class Email {
    @Id
    @GeneratedValue
    @Column(name = "email_id")
    private int id;


        @Column(unique = true)
        private String email;

}
