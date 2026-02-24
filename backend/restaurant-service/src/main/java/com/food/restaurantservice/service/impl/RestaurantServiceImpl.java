package com.food.restaurantservice.service.impl;

import com.food.restaurantservice.dto.RestaurantRequest;
import com.food.restaurantservice.dto.RestaurantResponse;
import com.food.restaurantservice.entity.Restaurant;
import com.food.restaurantservice.exception.ResourceNotFoundException;
import com.food.restaurantservice.repository.RestaurantRepository;
import com.food.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

    @Override
    public RestaurantResponse create(RestaurantRequest request) {

        log.info("Creating restaurant with name: {}", request.getName());

        Restaurant restaurant = Restaurant.builder()
                .name(request.getName())
                .location(request.getLocation())
                .rating(request.getRating())
                .build();

        Restaurant saved = repository.save(restaurant);

        log.info("Restaurant created with id: {}", saved.getId());

        return mapToResponse(saved);
    }

    @Override
    public RestaurantResponse getById(Long id) {

        log.info("Fetching restaurant with id: {}", id);

        Restaurant restaurant = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Restaurant not found with id: " + id));

        return mapToResponse(restaurant);
    }

    @Override
    public List<RestaurantResponse> getAll() {

        log.info("Fetching all restaurants");

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private RestaurantResponse mapToResponse(Restaurant r) {
        return RestaurantResponse.builder()
                .id(r.getId())
                .name(r.getName())
                .location(r.getLocation())
                .rating(r.getRating())
                .build();
    }
}