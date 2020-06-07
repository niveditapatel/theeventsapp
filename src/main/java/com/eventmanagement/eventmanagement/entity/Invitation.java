package com.eventmanagement.eventmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Invitation {

    @Id
    @GeneratedValue
    @Column(name = "invitation_id")
    private int id;

    private String email;
    private String invitationType;
    private Date expireDate;
    private String response;

}
