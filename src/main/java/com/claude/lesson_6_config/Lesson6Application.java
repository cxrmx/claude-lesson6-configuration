package com.claude.lesson_6_config;

import com.claude.lesson_6_config.demo.DemoRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lesson6Application {

	public static void main(String[] args) {
		SpringApplication.run(Lesson6Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(DemoRunner runner) {
		return args -> {
			System.out.println("=".repeat(60));
			System.out.println("\uD83D\uDCDALIBRARY MANAGEMENT SYSTEM");
			System.out.println("=".repeat(60));
			System.out.println();
			runner.runDemo();
		};
	}

}
