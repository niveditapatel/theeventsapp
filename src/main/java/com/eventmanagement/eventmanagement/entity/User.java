package com.eventmanagement.eventmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Data
@Builder
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int id;
    @Column(unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String status;


    public String getEmail() {
        return email;
    }

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User(User user) {
        this.status = user.status;
        this.password = user.password;
        this.email = user.email;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.role = user.role;
    }
}
