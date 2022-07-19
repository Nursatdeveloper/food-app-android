package com.example.application.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.application.models.Restaurant;
import com.example.application.repositories.GetRestaurantsRepository;
import com.example.application.responses.GetRestaurantsResponse;

import java.util.List;

public class GetRestaurantsViewModel extends ViewModel {

    private GetRestaurantsRepository getRestaurantsRepository;

    public GetRestaurantsViewModel() {
        getRestaurantsRepository = new GetRestaurantsRepository();
    }

    public LiveData<List<Restaurant>> getRestaurants() {
        return getRestaurantsRepository.getRestaurants();
    }
}
