package com.claude.lesson_6_config.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {
    public DevConfig() {
        System.out.println("=".repeat(60));
        System.out.println("    DEV PROFILE ACTIVATED");
        System.out.println("=".repeat(60));
    }

    @Bean
    public String dataInitializer() {
        System.out.println("[DEV] Data initializer: Using in-memory storage");
        return "DEV_INITIALIZER";
    }

    @Bean
    public boolean debugMode() {
        System.out.println("DEV_INITIALIZER");
        return true;
    }
}
