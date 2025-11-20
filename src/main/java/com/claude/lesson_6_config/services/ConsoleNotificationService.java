package com.claude.lesson_6_config.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class ConsoleNotificationService implements NotificationService {
    public ConsoleNotificationService() {
        System.out.println("[DEV] ConsoleNotificationService initialized");
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("📝 [CONSOLE] Notification: " + message);
    }
}
