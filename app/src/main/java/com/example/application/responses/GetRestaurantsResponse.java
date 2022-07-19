package com.example.application.responses;

import com.example.application.models.Restaurant;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetRestaurantsResponse {

    @SerializedName("restaurants")
    private List<Restaurant> restaurants;


    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
