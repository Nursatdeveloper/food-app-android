package com.example.application.responses;

import com.example.application.models.Food;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodResponse {
    @SerializedName("restaurantName")
    private String restaurantName;

    @SerializedName("categoryName")
    private String categoryName;

    @SerializedName("foods")
    private List<Food> foods;

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Food> getFoods() {
        return foods;
    }
}
