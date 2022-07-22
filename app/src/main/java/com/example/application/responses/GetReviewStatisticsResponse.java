package com.example.application.responses;

import com.google.gson.annotations.SerializedName;

public class GetReviewStatisticsResponse {

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

    public void setPercentageOfLikes(String percentageOfLikes) {
        this.percentageOfLikes = percentageOfLikes;
    }

    public void setOverallReviews(String overallReviews) {
        this.overallReviews = overallReviews;
    }
}
