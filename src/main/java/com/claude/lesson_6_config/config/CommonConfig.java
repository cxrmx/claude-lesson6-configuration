package com.claude.lesson_6_config.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;
import java.util.Random;

@Configuration
public class CommonConfig {
    @Bean
    public DateTimeFormatter dateTimeFormatter() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Common Config: DateTimeFormatter bean created");
        return dateTimeFormatter;
    }

    @Bean
    public Random random() {
        Random random = new Random();
        System.out.println("Common Config: Random bean created");
        return random;
    }
}
