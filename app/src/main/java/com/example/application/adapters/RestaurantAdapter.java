package com.example.application.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.R;
import com.example.application.databinding.ItemContainerRestaurantBinding;
import com.example.application.models.Restaurant;
import com.example.application.models.RestaurantReview;
import com.example.application.responses.GetReviewStatisticsResponse;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurants;
    private List<RestaurantReview> restaurantReviews;
    private LayoutInflater layoutInflater;

    public RestaurantAdapter(List<Restaurant> restaurants, List<RestaurantReview> restaurantReviews) {
        this.restaurants = restaurants;
        this.restaurantReviews = restaurantReviews;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerRestaurantBinding itemContainerRestaurantBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_container_restaurant, parent, false
        );
        return new RestaurantViewHolder(itemContainerRestaurantBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        holder.bindRestaurant(restaurants.get(position), restaurantReviews.get(position));
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {

        private ItemContainerRestaurantBinding itemContainerRestaurantBinding;

        public RestaurantViewHolder(ItemContainerRestaurantBinding itemContainerRestaurantBinding){
            super(itemContainerRestaurantBinding.getRoot());
            this.itemContainerRestaurantBinding = itemContainerRestaurantBinding;
        }

        public void bindRestaurant(Restaurant restaurant, RestaurantReview restaurantReview) {
            itemContainerRestaurantBinding.setRestaurant(restaurant);
            itemContainerRestaurantBinding.setReview(restaurantReview);
            itemContainerRestaurantBinding.imageRestaurant.setImageBitmap(
                    getDecodedImage(restaurant.getImage())
            );
            itemContainerRestaurantBinding.executePendingBindings();
        }
    }

    private static Bitmap getDecodedImage(String encodedImage) {
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
