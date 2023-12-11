package com.vanier.grading_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class GradingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GradingApiApplication.class, args);
	}

}
