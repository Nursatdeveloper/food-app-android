package com.example.application.listeners;

import com.example.application.models.Restaurant;
import com.example.application.models.RestaurantReview;

public interface RestaurantsListener {
    void onRestaurantClicked(Restaurant restaurant, RestaurantReview restaurantReview);
}
