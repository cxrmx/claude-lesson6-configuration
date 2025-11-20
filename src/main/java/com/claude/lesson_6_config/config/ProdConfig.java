package com.claude.lesson_6_config.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {
    public ProdConfig() {
        System.out.println("=".repeat(60));
        System.out.println("    PROD PROFILE ACTIVATED");
        System.out.println("=".repeat(60));
    }

    @Bean
    public String dataInitializer() {
        System.out.println("[PROD] Data initializer: Using database storage");
        return "PROD_INITIALIZER";
    }

    @Bean
    public boolean debugMode() {
        System.out.println("[PROD] Debug mode: DISABLED");
        return false;
    }
}
