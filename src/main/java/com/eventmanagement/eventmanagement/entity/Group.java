package com.eventmanagement.eventmanagement.entity;

import com.eventmanagement.eventmanagement.service.GroupService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "group_table")
public class Group {

    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private int id;
    @Column(unique = true)
    private String groupName;
    @Column(nullable = false)
    private String creatorEmail;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="group_user",
            joinColumns=@JoinColumn(name="group_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<User> users;

}

