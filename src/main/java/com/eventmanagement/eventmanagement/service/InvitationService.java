package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.Invitation;
import com.eventmanagement.eventmanagement.repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InvitationService {
    @Autowired
    InvitationRepository invitationRepository;

    List<Invitation> getInvitations(String email) {
        return invitationRepository.findByEmail(email);
    }

}
