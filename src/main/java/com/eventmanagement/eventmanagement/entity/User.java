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

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name="user_role",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"))


    private List<Role> roles;
//
//    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//            name="user_event",
//            joinColumns=@JoinColumn(name="user_id"),
//            inverseJoinColumns=@JoinColumn(name="event_id"))
//    private List<Event> events;

    public void addRole(Role role) {
        roles.add(role);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles)
    {
        this.roles = roles;
    }

    public User(User user) {
        this.status = user.status;
        this.password = user.password;
        this.email = user.email;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.roles = user.roles;
    }
}
