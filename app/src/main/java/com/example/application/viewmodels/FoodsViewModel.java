package com.example.application.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.application.repositories.FoodsRepository;
import com.example.application.responses.FoodResponse;

public class FoodsViewModel extends ViewModel {

    private FoodsRepository foodsRepository;

    public FoodsViewModel() {
        foodsRepository = new FoodsRepository();
    }

    public LiveData<FoodResponse> getFoods(String restaurantId, String catalogId) {
        return foodsRepository.getFoods(restaurantId, catalogId);
    }
}
