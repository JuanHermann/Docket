package com.test.juan.AppDocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAutoConfiguration
@SpringBootApplication
public class AppDocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppDocketApplication.class, args);
	}

}
