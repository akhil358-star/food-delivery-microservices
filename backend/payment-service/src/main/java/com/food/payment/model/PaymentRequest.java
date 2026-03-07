package com.food.payment.model;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long orderId;
    private Double amount;
}