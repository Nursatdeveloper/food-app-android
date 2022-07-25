package com.example.application.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RestaurantReview implements Serializable {
    @SerializedName("reviewItemId")
    private String id;

    @SerializedName("percentageOfLikes")
    private String percentageOfLikes;

    @SerializedName("overallReviews")
    private String overallReviews;

    public String getId() {
        return id;
    }

    public String getPercentageOfLikes() {
        return percentageOfLikes;
    }

    public String getOverallReviews() {
        return overallReviews;
    }
}
