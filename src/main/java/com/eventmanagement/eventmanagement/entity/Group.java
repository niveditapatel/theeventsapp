package com.eventmanagement.eventmanagement.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "group_table")
public class Group {

@Id
@GeneratedValue
 private int id;
    private String groupname;
    private String email;


}
