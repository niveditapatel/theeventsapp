package com.eventmanagement.eventmanagement.repository;

import com.eventmanagement.eventmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


   Optional<User> findByEmail(String email);


    //User findbyEmail(String nextToken);
}
