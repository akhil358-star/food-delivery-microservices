package com.food.order.controller;

import com.food.order.dto.OrderRequest;
import com.food.order.model.FoodOrder;
import com.food.order.service.OrderService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public FoodOrder createOrder(@Valid @RequestBody OrderRequest request) {
        return orderService.createOrder(request);
    }

    @GetMapping("/{id}")
    public FoodOrder getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }
}