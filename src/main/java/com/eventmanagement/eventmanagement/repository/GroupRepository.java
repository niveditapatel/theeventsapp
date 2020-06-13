package com.eventmanagement.eventmanagement.repository;

import com.eventmanagement.eventmanagement.entity.Group;
import com.eventmanagement.eventmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group,Integer> {
    Optional<Group> findByGroupName(String groupName);

    @Query(value = "SELECT group_name FROM group_table", nativeQuery = true)
    List<String> getGroupName();

}
