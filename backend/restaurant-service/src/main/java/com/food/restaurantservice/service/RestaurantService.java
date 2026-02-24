package com.food.restaurantservice.service;

import com.food.restaurantservice.dto.RestaurantRequest;
import com.food.restaurantservice.dto.RestaurantResponse;

import java.util.List;

public interface RestaurantService {

    RestaurantResponse create(RestaurantRequest request);

    RestaurantResponse getById(Long id);

    List<RestaurantResponse> getAll();
}