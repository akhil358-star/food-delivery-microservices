
package com.food.payment.controller;
import com.food.payment.model.PaymentRequest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @PostMapping("/process")
    public String processPayment(@RequestBody PaymentRequest request) {
        return "Payment successful for order " + request.orderId;
    }

    public static class PaymentRequest {
        public Long orderId;
        public Double amount;
    }
}