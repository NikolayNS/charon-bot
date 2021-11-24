package ru.dmitrenko.charonbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableRetry
@EnableScheduling
@SpringBootApplication
public class CharonBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(CharonBotApplication.class, args);
	}
}
