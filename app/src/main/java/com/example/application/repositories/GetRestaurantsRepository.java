package com.example.application.repositories;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.PrimaryKey;

import com.example.application.models.Restaurant;
import com.example.application.network.ApiClient;
import com.example.application.network.ApiService;
import com.example.application.responses.GetRestaurantsResponse;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetRestaurantsRepository {

    private ApiService apiService;

    public GetRestaurantsRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<List<Restaurant>> getRestaurants() {
        MutableLiveData<List<Restaurant>> data = new MutableLiveData<>();
        try {
            apiService.getRestaurants().enqueue(new Callback<List<Restaurant>>() {
                @Override
                public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                    Log.i("Response", String.valueOf(response.code()));
                    data.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                    data.setValue(null);
                    Log.i("Error", "Hello motherfucker");
                    t.printStackTrace();
                }
            });
        } catch (Exception ex) {

        }
        return data;

    }
}

