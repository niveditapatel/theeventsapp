package com.eventmanagement.eventmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@EntityScan
@SpringBootApplication
public class EventManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(EventManagementApplication.class, args);
	}

}
