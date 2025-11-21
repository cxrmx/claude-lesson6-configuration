package com.claude.lesson_6_config.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class EmailNotificationService implements NotificationService{
    private final String emailHost;
    private final int emailPort;

    public EmailNotificationService(
            @Value("${notification.email.port}") int emailPort,
            @Value("${notification.email.host}") String emailHost
    ) {
        this.emailPort =  emailPort;
        this.emailHost = emailHost;
        System.out.printf("\n[PROD] EmailNotificationService initialized (Host: %s, Port: %s)", emailHost, emailPort);
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("📧 [EMAIL] Sending email: " + message);
    }
}
