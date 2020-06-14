package com.eventmanagement.eventmanagement.repository;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.entity.UnseenEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UnseenEventRepository extends JpaRepository<UnseenEvent, Integer> {

    List<UnseenEvent> findByUserId(int userId);

    @Query(value = "(select count(*) from event where event_id IN (select event_id FROM unseen_event where user_id= :user_id))", nativeQuery = true)
    int alertsCount(@Param("user_id") int user_id);

    @Query(value = "(select count(*) from event where start_date_time between now() and date_add(now(), INTERVAL 15 MINUTE) and event_id IN (select event_id FROM registered where user_id= :user_id and response='accept'))", nativeQuery = true)
    int alertsCountEventIn15Min(@Param("user_id")  int user_id);

}
