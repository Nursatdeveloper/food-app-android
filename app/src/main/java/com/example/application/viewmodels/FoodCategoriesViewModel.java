package com.example.application.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.application.repositories.FoodCategoriesRepository;
import com.example.application.responses.FoodCategoryResponse;

public class FoodCategoriesViewModel extends ViewModel {

    private FoodCategoriesRepository foodCategoriesRepository;

    public FoodCategoriesViewModel() {
        foodCategoriesRepository = new FoodCategoriesRepository();
    }

    public LiveData<FoodCategoryResponse> getFoodCategories(String restaurantId) {
        return foodCategoriesRepository.getFoodCategories(restaurantId);
    }
}
