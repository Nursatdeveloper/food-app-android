package com.example.application.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.example.application.R;
import com.example.application.adapters.RestaurantAdapter;
import com.example.application.databinding.ActivityMainBinding;
import com.example.application.databinding.ActivityRestaurantsBinding;
import com.example.application.models.Restaurant;
import com.example.application.viewmodels.GetRestaurantsViewModel;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsActivity extends AppCompatActivity {

    private GetRestaurantsViewModel viewModel;
    private List<Restaurant> restaurants = new ArrayList<>();
    private RestaurantAdapter restaurantAdapter;
    private ActivityRestaurantsBinding activityRestaurantsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRestaurantsBinding = DataBindingUtil.setContentView(this, R.layout.activity_restaurants);
        doInitialization();
    }

    private void doInitialization() {
        activityRestaurantsBinding.restaurantsRecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(GetRestaurantsViewModel.class);
        restaurantAdapter = new RestaurantAdapter(restaurants);
        activityRestaurantsBinding.restaurantsRecyclerView.setAdapter(restaurantAdapter);
        getRestaurants();
    }

    private void getRestaurants() {
        viewModel.getRestaurants().observe(this, response -> {
            if(response != null) {
                String t = String.valueOf(response.size());
                Toast.makeText(getApplicationContext(), t, Toast.LENGTH_SHORT).show();
                restaurants.addAll(response);
                Log.i("INFO", String.valueOf(restaurants.size()));
                Log.i("INFO", restaurants.get(1).getRestaurant());
                restaurantAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(), "Response null", Toast.LENGTH_SHORT).show();
            }
        });
    }
}