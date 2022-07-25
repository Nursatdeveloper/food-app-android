package com.example.application.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.example.application.R;
import com.example.application.adapters.FoodCategoriesAdapter;
import com.example.application.databinding.ActivityRestaurantDetailsBinding;
import com.example.application.models.FoodCategory;
import com.example.application.models.Restaurant;
import com.example.application.models.RestaurantReview;
import com.example.application.viewmodels.FoodCategoriesViewModel;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetailsActivity extends AppCompatActivity {

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
        activityRestaurantDetailsBinding.imageRestaurant.setImageBitmap(getDecodedImage(restaurant.getImage()));
        activityRestaurantDetailsBinding.setRestaurant(restaurant.getRestaurant());
        activityRestaurantDetailsBinding.setRating(
                review.getPercentageOfLikes() + "% " + "(" + review.getOverallReviews() + ")"
        );
        activityRestaurantDetailsBinding.setDelivery("15-20 min");

        viewModel = new ViewModelProvider(this).get(FoodCategoriesViewModel.class);
        adapter = new FoodCategoriesAdapter(foodCategories);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        activityRestaurantDetailsBinding.foodCategoriesRecyclerView.setLayoutManager(mLayoutManager);
        activityRestaurantDetailsBinding.foodCategoriesRecyclerView.setHasFixedSize(true);
        activityRestaurantDetailsBinding.foodCategoriesRecyclerView.setAdapter(adapter);

        getFoodCategories(restaurant.getId());
    }

    private void getFoodCategories(String restaurantId) {
        viewModel.getFoodCategories(restaurantId).observe(this, response -> {
            if(response != null) {
                foodCategories.addAll(response.getFoodCategories());
                adapter.notifyDataSetChanged();
                Log.i("SUCCESS", String.valueOf(foodCategories.size()));
            } else {
                Log.i("ERROR", "response is null");
            }
        });
    }

    private static Bitmap getDecodedImage(String encodedImage) {
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}