package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler
@RestController
public class LineApiApplication {

	@GetMapping
	String home() {
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(LineApiApplication.class, args);
	}

}