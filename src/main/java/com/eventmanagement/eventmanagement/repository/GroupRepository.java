package com.eventmanagement.eventmanagement.repository;

import com.eventmanagement.eventmanagement.entity.Group;
import lombok.val;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;



public interface GroupRepository extends JpaRepository<Group,Integer> {

    Optional<Group> findByGroupName(String groupName);

    @Query(value = "SELECT group_name FROM group_table", nativeQuery = true)
    List<String> getGroupName();

    @Query(value = "SELECT group_name,group_user.group_id,creator_email from group_table join group_user on group_table.group_id=group_user.group_id and group_user.user_id=:user_id", nativeQuery = true)
    List<Group> findGroupByUser(@Param("user_id") int user_id);


}
