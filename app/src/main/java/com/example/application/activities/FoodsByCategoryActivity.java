package com.example.application.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.application.R;
import com.example.application.adapters.FoodsAdapter;
import com.example.application.databinding.ActivityFoodsByCategoryBinding;
import com.example.application.models.Food;
import com.example.application.viewmodels.FoodsViewModel;

import java.util.ArrayList;
import java.util.List;

public class FoodsByCategoryActivity extends AppCompatActivity {
    private ActivityFoodsByCategoryBinding activityFoodsByCategoryBinding;
    private FoodsViewModel viewModel;
    private FoodsAdapter adapter;
    private List<Food> foods = new ArrayList<>();
    private String restaurantId;
    private String catalogId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFoodsByCategoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_foods_by_category);
        doInitialization();
    }

    private void doInitialization() {
        restaurantId = getIntent().getStringExtra("restaurantId");
        catalogId = getIntent().getStringExtra("categoryId");
        viewModel = new ViewModelProvider(this).get(FoodsViewModel.class);
        adapter = new FoodsAdapter(foods);
        activityFoodsByCategoryBinding.foodsRecyclerView.setHasFixedSize(true);
        activityFoodsByCategoryBinding.foodsRecyclerView.setAdapter(adapter);
        activityFoodsByCategoryBinding.imageBack.setOnClickListener(view -> onBackPressed());

        getFoods(restaurantId, catalogId);
    }

    private void getFoods(String rId, String cId) {
        viewModel.getFoods(rId, cId).observe(this, response -> {
            if(response != null) {
                if(response.getFoods() != null) {
                    foods.addAll(response.getFoods());
                    activityFoodsByCategoryBinding.setHeaderText(
                            response.getRestaurantName() + " | " + response.getCategoryName()
                    );
                    adapter.notifyDataSetChanged();

                }
            }
        });
    }
}