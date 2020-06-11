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
public class Group<results> {


    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private int id;
    @Column(unique = true)
    private String groupname;
    private String groupusers;



    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name="group_user",
            joinColumns=@JoinColumn(name="group_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))


    private List <User> users;


    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
      return users;
   }
    public void setEmails(List<User> users)
    {
        this.users = users;
    }

    public Group(Group group) {
        this.groupname = group.groupname;
this.users=group.users;
        this.groupusers=group.groupusers;
    }
}