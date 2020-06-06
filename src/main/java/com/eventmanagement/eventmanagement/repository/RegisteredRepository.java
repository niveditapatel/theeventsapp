package com.eventmanagement.eventmanagement.repository;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.entity.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredRepository extends JpaRepository<Registered, Integer> {


    Event findByTitle(String title);
}
