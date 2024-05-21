package com.utd.q2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Q2Application {

	public static void main(String[] args) {
		SpringApplication.run(Q2Application.class, args);
	}

}
