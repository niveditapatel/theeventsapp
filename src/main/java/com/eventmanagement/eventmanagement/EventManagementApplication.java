package com.eventmanagement.eventmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

<<<<<<< HEAD

@EnableSwagger2
=======
>>>>>>> a986aa44cfa07b291dd02d9fa7bc0cf6900ab7d2
@EntityScan
@SpringBootApplication
@EnableSwagger2
public class EventManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(EventManagementApplication.class, args);
	}

}
