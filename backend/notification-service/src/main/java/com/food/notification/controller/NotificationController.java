package com.food.notification.controller;

import com.food.notification.model.NotificationRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @PostMapping("/send")
    public String sendNotification(@RequestBody NotificationRequest request) {

        System.out.println("==================================");
        System.out.println("Sending Notification...");
        System.out.println("Order ID: " + request.getOrderId());
        System.out.println("Message: " + request.getMessage());
        System.out.println("==================================");

        return "Notification sent for order " + request.getOrderId();
    }
}