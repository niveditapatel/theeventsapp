package com.eventmanagement.eventmanagement.repository;

import com.eventmanagement.eventmanagement.entity.Group;
import com.eventmanagement.eventmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Integer> {



//    List<Group> findByGroupname(String groupname);

   // List<Group> getUsersingroup(String groupname);
//@Query

  // ArrayList<String> getEmails(String groupusers);

 /*ArrayList<String> getEmails(String groupusers)
    {    ArrayList<String> list = new ArrayList<String>();
          String CSV = groupusers;
            StringTokenizer tokenizer = new StringTokenizer(CSV, ",");
          while (tokenizer.hasMoreTokens()) {
            list.add(tokenizer.nextToken());
     }
     return list;
    }*/

//@Query("FROM User WHERE email= ?1")
//    User findByEmail(String email);
//
//
//   @Modifying
//    @Query(value = "insert into group_user (group_id,user_id) VALUES (:gid,:uid)", nativeQuery = true)
//    @Transactional
//    void enterintogroupuser(@Param("gid") Integer gid, @Param("uid") Integer uid);
}
