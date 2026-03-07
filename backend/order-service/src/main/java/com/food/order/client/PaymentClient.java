package com.food.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service")
public interface PaymentClient {

    @PostMapping("/payments/process")
    String processPayment(@RequestBody PaymentRequest request);

    class PaymentRequest {
        public Long orderId;
        public Double amount;

        public PaymentRequest(Long orderId, Double amount) {
            this.orderId = orderId;
            this.amount = amount;
        }
    }
}