package com.example.application.network;

import com.example.application.models.Restaurant;
import com.example.application.responses.GetRestaurantsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("restaurants")
    Call<List<Restaurant>> getRestaurants();
}
