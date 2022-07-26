package com.example.application.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.application.network.catalog_service_api.CatalogApiClient;
import com.example.application.network.catalog_service_api.CatalogApiService;
import com.example.application.responses.FoodResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodsRepository {

    private CatalogApiService apiService;

    public FoodsRepository() {
        apiService = CatalogApiClient.getRetrofit().create(CatalogApiService.class);
    }

    public LiveData<FoodResponse> getFoods(String restaurantId, String categoryId) {
        Log.i("INFO", "rId"+restaurantId);
        Log.i("INFO", "cId"+categoryId);
        MutableLiveData<FoodResponse> data = new MutableLiveData<>();
        apiService.getFoods(Integer.parseInt(restaurantId), Integer.parseInt(categoryId)).enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                if(response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    Log.i("API_CALL_ERROR", String.valueOf(response.code()) + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
