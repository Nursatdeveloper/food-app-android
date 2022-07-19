package com.example.application.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.application.R;
import com.example.application.adapters.RestaurantAdapter;
import com.example.application.databinding.ActivityMainBinding;
import com.example.application.models.Restaurant;
import com.example.application.viewmodels.GetRestaurantsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        doInitialization();
    }

    private void doInitialization() {
        activityMainBinding.cardRestaurants.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RestaurantsActivity.class);
            startActivity(intent);
        });
    }


}