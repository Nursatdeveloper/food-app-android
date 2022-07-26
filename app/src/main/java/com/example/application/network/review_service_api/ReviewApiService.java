package com.example.application.network.review_service_api;

import com.example.application.models.RestaurantReview;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReviewApiService {

    @GET("get-statistics")
    Single<List<RestaurantReview>> getReviewStatistics(
            @Query("reviewItem") String reviewItem, @Query("ids") List<Integer> ids);
}
