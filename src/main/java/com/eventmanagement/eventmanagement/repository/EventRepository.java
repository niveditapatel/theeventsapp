package com.eventmanagement.eventmanagement.repository;

import com.eventmanagement.eventmanagement.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Integer> {

    Event findByTitle(String title);


    @Query(value = "select title,place,start_date_time,email,description FROM Event_TBL", nativeQuery = true)
    String findEventDashboard();

    @Query(value = "select count(*) from Event_TBL where start_date_time=(select curdate())", nativeQuery = true)
    Integer findEventstoday();

   // Integer findnoEvents();
}
