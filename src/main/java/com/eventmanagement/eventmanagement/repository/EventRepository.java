package com.eventmanagement.eventmanagement.repository;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.entity.EventDashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    Event findByTitle(String title);


    @Query(value = "select title,place,start_date_time,end_date_time,email,description FROM Event_TBL", nativeQuery = true)
    List<String> findEventDashboard();

    @Query(value = "select count(*) from Event_TBL where start_date_time=(select curdate())", nativeQuery = true)
    Integer findEventstoday();

    @Query(value = "select * FROM Event_TBL where email= :email",nativeQuery = true)
    List<Event> findEventByHost (@Param("email")  String email);

}
