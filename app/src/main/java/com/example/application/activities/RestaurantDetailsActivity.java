package com.example.application.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.example.application.R;
import com.example.application.adapters.FoodCategoriesAdapter;
import com.example.application.databinding.ActivityRestaurantDetailsBinding;
import com.example.application.listeners.FoodCategoriesListener;
import com.example.application.models.FoodCategory;
import com.example.application.models.Restaurant;
import com.example.application.models.RestaurantReview;
import com.example.application.utilities.ImageHandler;
import com.example.application.viewmodels.FoodCategoriesViewModel;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetailsActivity extends AppCompatActivity implements FoodCategoriesListener {

    private List<FoodCategory> foodCategories = new ArrayList<>();
    private FoodCategoriesViewModel viewModel;
    private FoodCategoriesAdapter adapter;
    private ActivityRestaurantDetailsBinding activityRestaurantDetailsBinding;
    private Restaurant restaurant;
    private RestaurantReview review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRestaurantDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_details);
        doInitialization();
    }

    private void doInitialization() {
        activityRestaurantDetailsBinding.imageBack.setOnClickListener(view -> onBackPressed());
        restaurant = (Restaurant) getIntent().getSerializableExtra("restaurant");
        review = (RestaurantReview) getIntent().getSerializableExtra("review");
        activityRestaurantDetailsBinding.imageRestaurant.setImageBitmap(ImageHandler.getDecodedImage(restaurant.getImage()));
        activityRestaurantDetailsBinding.setRestaurant(restaurant.getRestaurant());
        activityRestaurantDetailsBinding.setRating(
                review.getPercentageOfLikes() + "% " + "(" + review.getOverallReviews() + ")"
        );
        activityRestaurantDetailsBinding.setDelivery("15-20 min");

        viewModel = new ViewModelProvider(this).get(FoodCategoriesViewModel.class);
        adapter = new FoodCategoriesAdapter(foodCategories, this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        activityRestaurantDetailsBinding.foodCategoriesRecyclerView.setLayoutManager(mLayoutManager);
        activityRestaurantDetailsBinding.foodCategoriesRecyclerView.setHasFixedSize(true);
        activityRestaurantDetailsBinding.foodCategoriesRecyclerView.setAdapter(adapter);

        getFoodCategories(restaurant.getId());
    }

    private void getFoodCategories(String restaurantId) {
        activityRestaurantDetailsBinding.setIsLoading(true);
        viewModel.getFoodCategories(restaurantId).observe(this, response -> {
            if(response != null) {
                foodCategories.addAll(response.getFoodCategories());
                activityRestaurantDetailsBinding.setIsLoading(false);
                adapter.notifyDataSetChanged();
                Log.i("SUCCESS", String.valueOf(foodCategories.size()));
            } else {
                Log.i("ERROR", "response is null");
            }
        });
    }

    @Override
    public void onFoodCategoryClicked(FoodCategory foodCategory) {
        Log.i("INFO", foodCategory.getId() + foodCategory.getName());
        Intent intent = new Intent(getApplicationContext(), FoodsByCategoryActivity.class);
        intent.putExtra("restaurantId", restaurant.getId());
        intent.putExtra("categoryId", foodCategory.getId());
        startActivity(intent);
    }
}