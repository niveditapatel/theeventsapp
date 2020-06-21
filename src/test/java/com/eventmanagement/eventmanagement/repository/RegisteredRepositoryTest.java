package com.eventmanagement.eventmanagement.repository;

import com.eventmanagement.eventmanagement.entity.Registered;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Registered Repository Test")
class RegisteredRepositoryTest {

    @Autowired
    RegisteredRepository registeredRepository;

    @Test
    void findByUserId() {
        Registered registered1 = Registered.builder()
                .eventId(1)
                .userId(2)
                .response("accept")
                .build();

        Registered registered2 = Registered.builder()
                .eventId(1)
                .userId(3)
                .response("reject")
                .build();

        registeredRepository.save(registered1);
        registeredRepository.save(registered2);

        List<Registered> registeredList = registeredRepository.findByUserId(2);

        assertThat(registeredList).isNotNull();
        assertThat(registeredList.size()).isEqualTo(1);

    }

    @Test
    void get() {

        Registered registered1 = Registered.builder()
                .eventId(1)
                .userId(2)
                .response("accept")
                .build();

        Registered registered2 = Registered.builder()
                .eventId(1)
                .userId(3)
                .response("reject")
                .build();

        registeredRepository.save(registered1);
        registeredRepository.save(registered2);

        Optional<Registered> optionalRegistered = registeredRepository.get(2, 1);
        assertThat(optionalRegistered.isPresent()).isEqualTo(true);
        assertThat(optionalRegistered.get().getResponse()).isEqualTo("accept");
    }
}