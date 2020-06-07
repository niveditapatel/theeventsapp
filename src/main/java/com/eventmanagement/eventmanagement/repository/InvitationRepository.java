package com.eventmanagement.eventmanagement.repository;

import com.eventmanagement.eventmanagement.entity.Event;
import com.eventmanagement.eventmanagement.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Integer> {


}
