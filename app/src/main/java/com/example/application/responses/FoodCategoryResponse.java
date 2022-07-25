package com.example.application.responses;

import com.example.application.models.FoodCategory;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodCategoryResponse {
    @SerializedName("restaurantName")
    private String restaurant;

    @SerializedName("foodCategories")
    private List<FoodCategory> foodCategories;

    public String getRestaurant() {
        return restaurant;
    }

    public List<FoodCategory> getFoodCategories() {
        return foodCategories;
    }
}
