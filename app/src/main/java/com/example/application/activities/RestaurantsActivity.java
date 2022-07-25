package com.example.application.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.application.R;
import com.example.application.adapters.RestaurantAdapter;
import com.example.application.databinding.ActivityRestaurantsBinding;
import com.example.application.models.Restaurant;
import com.example.application.models.RestaurantReview;
import com.example.application.network.catalog_service_api.CatalogApiClient;
import com.example.application.network.catalog_service_api.CatalogApiService;
import com.example.application.network.review_service_api.ReviewApiClient;
import com.example.application.network.review_service_api.ReviewApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RestaurantsActivity extends AppCompatActivity {

    private List<Restaurant> restaurants = new ArrayList<>();
    private List<RestaurantReview> restaurantReviews = new ArrayList<>();
    private List<Integer> restaurantIds = new ArrayList<>();
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
        restaurantAdapter = new RestaurantAdapter(restaurants, restaurantReviews);
        activityRestaurantsBinding.restaurantsRecyclerView.setAdapter(restaurantAdapter);
        callApis();
    }

    private void callApis() {
        activityRestaurantsBinding.setIsLoading(true);
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(
                CatalogApiClient.getRetrofit()
                        .create(CatalogApiService.class)
                        .getRestaurants().subscribeOn(Schedulers.io())
                        .flatMap(restaurants1 -> {
                            restaurants.addAll(restaurants1);
                            for(Restaurant r : restaurants) {
                                restaurantIds.add(Integer.parseInt(r.getId()));
                            }
                            return ReviewApiClient.getRetrofit()
                                    .create(ReviewApiService.class)
                                    .getReviewStatistics("restaurant", restaurantIds);
                        }).map(restaurantReviewsResponse -> {
                            restaurantReviews.addAll(restaurantReviewsResponse);
                            return restaurantReviews;
                        }).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(result -> {
                            activityRestaurantsBinding.setIsLoading(false);
                            restaurantAdapter.notifyDataSetChanged();
                        }, error -> {
                            Log.i("ERROR", error.getMessage());
                        })
        );
    }

}