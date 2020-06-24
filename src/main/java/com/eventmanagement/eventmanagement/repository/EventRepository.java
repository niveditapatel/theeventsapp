package com.eventmanagement.eventmanagement.repository;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.entity.EventDashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    Event findByTitle(String title);

    List<Event> findByEmail(String email);

    @Query(value = "select title,place,start_date_time,email FROM event natural join registered where user_id=:user_id and response='accept' and start_date_time > now()", nativeQuery = true)
    List<String> findEventDashboard(@Param("user_id") int user_id);

    @Query(value = "(select count(*) from event where DATE(start_date_time)=curdate() and event_id IN (select event_id FROM registered where user_id= :user_id and response='accept'))", nativeQuery = true)
    Integer findEventsToday(@Param("user_id")  int user_id);

    @Query(value = "(select * from event where event_id IN (select event_id FROM registered where user_id= :user_id and response='accept'))",nativeQuery = true)
    List<Event> findEventByUser (@Param("user_id")  int user_id);

    @Query(value = "(select * from event where event_id IN (select event_id FROM registered where user_id= :user_id and response='accept'))",nativeQuery = true)
    List<Event> findEventForUser (@Param("user_id")  int user_id);

    @Query(value = "(select * from event where event_id IN(select event_id FROM unseen_event where user_id= :user_id))", nativeQuery = true)
    List<Event> alerts(@Param("user_id") int user_id);

    @Query(value = "(select * from event where start_date_time between now() and date_add(now(), INTERVAL 15 MINUTE) and event_id IN (select event_id FROM registered where user_id= :user_id and response='accept'))", nativeQuery = true)
    List<Event> alertsEventIn15Min(@Param("user_id")  int user_id);

    @Query(value = "(select * from event where event_id IN (select event_id FROM registered where user_id= :user_id and response='pending'))", nativeQuery = true)
    List<Event> findPendingResponse(@Param("user_id") int user_id);

}

