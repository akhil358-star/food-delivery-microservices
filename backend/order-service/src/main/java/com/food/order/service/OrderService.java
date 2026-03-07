//package com.food.order.service;
//
//import com.food.order.client.PaymentClient;
//import com.food.order.dto.OrderRequest;
//import com.food.order.model.FoodOrder;
//import com.food.order.repository.OrderRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//@RequiredArgsConstructor
//public class OrderService {
//
//    private final OrderRepository orderRepository;
//    private final PaymentClient paymentClient;
//
//    public FoodOrder createOrder(OrderRequest request) {
//
//        FoodOrder order = FoodOrder.builder()
//                .customerId(request.getCustomerId())
//                .restaurantId(request.getRestaurantId())
//                .foodItems(request.getFoodItems().toString())
//                .totalAmount(request.getTotalAmount())
//                .orderStatus("CREATED")
//                .orderTime(LocalDateTime.now())
//                .build();
//
//        FoodOrder savedOrder = orderRepository.save(order);
//
//        // Call Payment Service
//        PaymentClient.PaymentRequest paymentRequest =
//                new PaymentClient.PaymentRequest(savedOrder.getId(), savedOrder.getTotalAmount());
//
//        paymentClient.processPayment(paymentRequest);
//
//        savedOrder.setOrderStatus("PAID");
//        return orderRepository.save(savedOrder);
//    }
//
//    public FoodOrder getOrder(Long id) {
//        return orderRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Order Not Found"));
//    }
//}

package com.food.order.service;

import com.food.order.client.PaymentClient;
import com.food.order.dto.OrderRequest;
import com.food.order.model.FoodOrder;
import com.food.order.repository.OrderRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;

    @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    public FoodOrder createOrder(OrderRequest request) {

        log.info("Creating order for customer {}", request.getCustomerId());

        FoodOrder order = FoodOrder.builder()
                .customerId(request.getCustomerId())
                .restaurantId(request.getRestaurantId())
                .foodItems(request.getFoodItems())
                .totalAmount(request.getTotalAmount())
                .orderStatus("CREATED")
                .orderTime(LocalDateTime.now())
                .build();

        FoodOrder savedOrder = orderRepository.save(order);

        log.info("Order saved with ID {}", savedOrder.getId());

        log.info("Calling payment service...");

        paymentClient.processPayment(
                new PaymentClient.PaymentRequest(savedOrder.getId(), savedOrder.getTotalAmount())
        );

        savedOrder.setOrderStatus("PAID");

        log.info("Payment successful for order {}", savedOrder.getId());

        return orderRepository.save(savedOrder);
    }

    public FoodOrder paymentFallback(OrderRequest request, Throwable ex) {

        log.error("Payment service is down. Circuit breaker activated.");

        FoodOrder order = FoodOrder.builder()
                .customerId(request.getCustomerId())
                .restaurantId(request.getRestaurantId())
                .foodItems(request.getFoodItems().toString())
                .totalAmount(request.getTotalAmount())
                .orderStatus("PAYMENT_PENDING")
                .orderTime(LocalDateTime.now())
                .build();

        return orderRepository.save(order);
    }

    public FoodOrder getOrder(Long id) {

        log.info("Fetching order with ID {}", id);

        return orderRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Order not found for ID {}", id);
                    return new RuntimeException("Order Not Found");
                });
    }
}