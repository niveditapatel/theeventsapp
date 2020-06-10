package com.eventmanagement.eventmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
@EntityScan
@SpringBootApplication
public class EventManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(EventManagementApplication.class, args);
	}

}
