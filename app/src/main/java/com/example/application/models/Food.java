package com.example.application.models;

import com.google.gson.annotations.SerializedName;

public class Food {
    @SerializedName("id")
    private String id;

    @SerializedName("foodName")
    private String name;

    @SerializedName("ingredients")
    private String ingredients;

    @SerializedName("price")
    private String price;

    @SerializedName("preparationTime")
    private String preparationTime;

    @SerializedName("image")
    private String image;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getPrice() {
        return price;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public String getImage() {
        return image;
    }
}
