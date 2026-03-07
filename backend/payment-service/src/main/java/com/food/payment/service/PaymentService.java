package com.food.payment.service;

import com.food.payment.model.PaymentRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    public String processPayment(PaymentRequest request) {

        if (request.getOrderId() == null || request.getAmount() == null) {
            throw new RuntimeException("Invalid payment request");
        }

        System.out.println("===================================");
        System.out.println("Processing Payment...");
        System.out.println("Order ID: " + request.getOrderId());
        System.out.println("Amount: " + request.getAmount());
        System.out.println("Time: " + LocalDateTime.now());
        System.out.println("===================================");

        // Simulate payment success
        return "Payment successful for order " + request.getOrderId();
    }
}