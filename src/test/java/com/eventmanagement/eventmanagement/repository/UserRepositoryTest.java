package com.eventmanagement.eventmanagement.repository;


import com.eventmanagement.eventmanagement.entity.Role;
import com.eventmanagement.eventmanagement.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@TestPropertySource("/application.properties")
//@SpringJUnitConfig(EventManagementApplication.class)
//@WebMvcTest(PublicController.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@DisplayName("User Repository Test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save() {
        User user = createUser();
        User result = userRepository.save(user);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getEmail()).isEqualTo(user.getEmail());
        System.out.println("Saved Id (Auto Generated) "+ result.getId());
    }

    @Test
    public void update() {
        User user = createUser();
        User result = userRepository.save(user);

        result.setLastName("Updated Last Name");

        User updatedResult = userRepository.save(result);
        assertThat(updatedResult.getId()).isNotNull();
        assertThat(updatedResult.getLastName()).isEqualTo(result.getLastName());
    }

    @Test
    void findByEmail() {
        User user1 = User.builder()
                .email("test55@test5.com")
                .firstName("Mufaddal")
                .lastName("Naya")
                .password("Hashed Passwords")
                .status("active")
                .build();
        User user2 = User.builder()
                .email("test5@test2.com")
                .firstName("Test")
                .lastName("Train")
                .password("Hashed Passwords")
                .status("active")
                .build();

        userRepository.save(user1);
        userRepository.save(user2);

        Optional<User> optionalUser = userRepository.findByEmail(user1.getEmail());

        assertThat(optionalUser.isPresent()).isEqualTo(true);
        User user = optionalUser.get();
        assertThat(user.getEmail()).isEqualTo(user1.getEmail());
        assertThat(user.getId()).isEqualTo(user1.getId());
    }

    @Test
    void getEmail() {
        User user1 = User.builder()
            .email("test@test.com")
            .firstName("Mufaddal")
            .lastName("Naya")
            .password("Hashed Passwords")
            .status("active")
            .build();
        User user2 = User.builder()
                .email("test2@test2.com")
                .firstName("Test")
                .lastName("Train")
                .password("Hashed Passwords")
                .status("active")
                .build();

        userRepository.save(user1);
        userRepository.save(user2);
        List<String> users = userRepository.getEmail();

        assertTrue(users.contains(user1.getEmail()));
        assertTrue(users.contains(user2.getEmail()));
    }

    private User createUser() {
        return User.builder()
                .email("test@test.com")
                .firstName("Mufaddal")
                .lastName("Naya")
                .role(new Role(3, "USER"))
                .password("Hashed Passwords")
                .status("active")
                .build();
    }
}