package com.food.restaurantservice.controller;

import com.food.restaurantservice.dto.RestaurantRequest;
import com.food.restaurantservice.dto.RestaurantResponse;
import com.food.restaurantservice.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService service;

    @PostMapping
    public RestaurantResponse create(@Valid @RequestBody RestaurantRequest request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public RestaurantResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<RestaurantResponse> getAll() {
        return service.getAll();
    }
}