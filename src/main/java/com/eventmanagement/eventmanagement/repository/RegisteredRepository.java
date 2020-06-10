package com.eventmanagement.eventmanagement.repository;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.entity.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisteredRepository extends JpaRepository<Registered, Integer> {


    List<Registered> findByUserId(int userId);

}
