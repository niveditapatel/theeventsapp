package com.eventmanagement.eventmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EntityScan
@SpringBootApplication
@EnableSwagger2
public class EventManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(EventManagementApplication.class, args);
	}

}
