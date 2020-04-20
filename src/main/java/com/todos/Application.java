package com.todos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//Where the application starts (the entry point)
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}