package com.food.notification.model;

import lombok.Data;

@Data
public class NotificationRequest {

    private Long orderId;
    private String message;
}