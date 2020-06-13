package com.eventmanagement.eventmanagement.repository;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.entity.EventDashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    Event findByTitle(String title);

    List<Event> findByEmail(String email);

    @Query(value = "select title,place,start_date_time,end_date_time,email,description FROM event", nativeQuery = true)
    List<String> findEventDashboard();

    @Query(value = "select count(*) from event where start_date_time=(select curdate())", nativeQuery = true)
    Integer findEventsToday();

    @Query(value = "(select * from event where event_id=(select event_id FROM registered where user_id= :user_id and response='accept'))",nativeQuery = true)
    List<Event> findEventByUser (@Param("user_id")  int user_id);


}
