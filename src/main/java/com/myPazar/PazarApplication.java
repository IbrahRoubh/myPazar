package com.myPazar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class PazarApplication {
	public static void main(String[] args) {
		SpringApplication.run(PazarApplication.class, args);
	}
}
