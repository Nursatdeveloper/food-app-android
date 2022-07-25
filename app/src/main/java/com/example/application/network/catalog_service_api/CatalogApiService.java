package com.example.application.network.catalog_service_api;

import com.example.application.models.Restaurant;
import com.example.application.models.RestaurantReview;
import com.example.application.responses.FoodCategoryResponse;
import com.example.application.responses.GetRestaurantsResponse;
import com.example.application.responses.GetReviewStatisticsResponse;

import java.util.List;
import java.util.Observable;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CatalogApiService {

    @GET("restaurants")
    Single<List<Restaurant>> getRestaurants();

    @GET("restaurants/{id}/food-categories")
    Call<FoodCategoryResponse> getFoodCategories(@Path("id") int id);

}
