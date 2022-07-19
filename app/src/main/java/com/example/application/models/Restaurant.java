package com.example.application.models;

import com.google.gson.annotations.SerializedName;

public class Restaurant {

    @SerializedName("id")
    private String id;

    @SerializedName("restaurant")
    private String restaurant;

    @SerializedName("image")
    private String image;

    public String getId() {
        return id;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public String getImage() {
        return image;
    }
}
