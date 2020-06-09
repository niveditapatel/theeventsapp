package com.eventmanagement.eventmanagement.repository;

import com.eventmanagement.eventmanagement.entity.Group;
import com.eventmanagement.eventmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, String> {


    List<Group> findByGroupname(String groupname);
}
