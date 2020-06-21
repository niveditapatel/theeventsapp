package com.eventmanagement.eventmanagement.repository;

import com.eventmanagement.eventmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findByEmail(String email);

    @Query(value = "SELECT email FROM user", nativeQuery = true)
    List<String> getEmail();

}
