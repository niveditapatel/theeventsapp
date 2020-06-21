package com.eventmanagement.eventmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Data
@Builder
public class UnseenEvent {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "event_id")
    private int eventId;

}
