package com.eventmanagement.eventmanagement.repository;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.entity.Registered;
import com.eventmanagement.eventmanagement.service.RegisteredService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RegisteredRepository extends JpaRepository<Registered, Integer> {

    List<Registered> findByUserId(int userId);

    @Query(value = "(select * from registered where event_id= :event_id and user_id= :user_id)", nativeQuery = true)
    Optional<Registered> get(@Param("user_id") int user_id,@Param("event_id") int event_id);

}
