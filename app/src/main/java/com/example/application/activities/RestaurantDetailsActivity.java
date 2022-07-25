package com.example.application.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import com.example.application.R;
import com.example.application.databinding.ActivityRestaurantDetailsBinding;
import com.example.application.models.Restaurant;
import com.example.application.models.RestaurantReview;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private ActivityRestaurantDetailsBinding activityRestaurantDetailsBinding;
    private Restaurant restaurant;
    private RestaurantReview review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRestaurantDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_details);
        activityRestaurantDetailsBinding.imageBack.setOnClickListener(view -> onBackPressed());
        restaurant = (Restaurant) getIntent().getSerializableExtra("restaurant");
        review = (RestaurantReview) getIntent().getSerializableExtra("review");
        activityRestaurantDetailsBinding.imageRestaurant.setImageBitmap(getDecodedImage(restaurant.getImage()));
        activityRestaurantDetailsBinding.setRestaurant(restaurant.getRestaurant());
        activityRestaurantDetailsBinding.setRating(
                review.getPercentageOfLikes() + "% " + "(" + review.getOverallReviews() + ")"
        );
        activityRestaurantDetailsBinding.setDelivery("15-20 min");

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        activityRestaurantDetailsBinding.foodCategoriesRecyclerView.setLayoutManager(mLayoutManager);

    }

    private static Bitmap getDecodedImage(String encodedImage) {
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}