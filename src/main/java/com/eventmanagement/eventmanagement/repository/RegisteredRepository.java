package com.eventmanagement.eventmanagement.repository;

import com.eventmanagement.eventmanagement.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredRepository extends JpaRepository<Event, Integer> {


    Event findByTitle(String title);
}
