package com.eventmanagement.eventmanagement.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
