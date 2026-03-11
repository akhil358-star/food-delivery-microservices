package com.food.notification.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationEventConsumer {

    @KafkaListener(topics = "order-topic", groupId = "notification-group")
    public void consumeOrderEvent(String message) {

        System.out.println("Notification Service received order event: " + message);

        // here you can trigger email/SMS logic later
    }
}