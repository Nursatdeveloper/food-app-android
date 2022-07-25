package com.example.application.models;

import com.google.gson.annotations.SerializedName;

public class FoodCategory {

    @SerializedName("id")
    private String id;

    @SerializedName("category")
    private String name;

    @SerializedName("image")
    private String image;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
