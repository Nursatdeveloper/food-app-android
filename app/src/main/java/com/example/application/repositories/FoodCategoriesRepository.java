package com.example.application.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.application.network.catalog_service_api.CatalogApiClient;
import com.example.application.network.catalog_service_api.CatalogApiService;
import com.example.application.responses.FoodCategoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodCategoriesRepository {

    private CatalogApiService apiService;

    public FoodCategoriesRepository() {
        apiService = CatalogApiClient.getRetrofit().create(CatalogApiService.class);
    }

    public LiveData<FoodCategoryResponse> getFoodCategories(String id) {
        MutableLiveData<FoodCategoryResponse> data = new MutableLiveData<>();
        apiService.getFoodCategories(Integer.parseInt(id)).enqueue(new Callback<FoodCategoryResponse>() {
            @Override
            public void onResponse(Call<FoodCategoryResponse> call, Response<FoodCategoryResponse> response) {
                if(response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    Log.i("API_CALL_ERROR", String.valueOf(response.code()) + response.message());
                }
            }

            @Override
            public void onFailure(Call<FoodCategoryResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
