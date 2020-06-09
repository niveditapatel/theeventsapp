package com.eventmanagement.eventmanagement.repository;

import com.eventmanagement.eventmanagement.entity.Group;
import com.eventmanagement.eventmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Integer> {


    List<Group> findByGroupname(String groupname);

   // List<Group> getUsersingroup(String groupname);

    // List<String> getEmails(String groupusers);

@Query("FROM User WHERE email= ?1")
    List<User> findByEmail(String email);


}
