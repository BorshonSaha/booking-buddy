package com.project.booking_buddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BookingBuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingBuddyApplication.class, args);
	}

}
