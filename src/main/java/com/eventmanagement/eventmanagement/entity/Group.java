package com.eventmanagement.eventmanagement.entity;

import com.eventmanagement.eventmanagement.repository.UserRepository;
import com.eventmanagement.eventmanagement.service.EventService;
import com.eventmanagement.eventmanagement.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

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

   /* public void getEmails() throws Exception{

        String sql = "SELECT column_name from information_schema.columns where table_name='suppliers'";

        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        // extract values from rs


    } */
            /*=getEmails(groupusers);
    @Autowired
    private UserRepository userRepository;
    public List <String> getEmails(String groupusers)
    { List <User> list = null;

        String CSV = groupusers;
        StringTokenizer tokenizer = new StringTokenizer(CSV, ",");
        while (tokenizer.hasMoreTokens())
        {
       list.add(userRepository.findbyEmail(tokenizer.nextToken()));

        }
        return list;
    }*/



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
