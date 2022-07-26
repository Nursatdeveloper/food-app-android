package com.example.application.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.R;
import com.example.application.databinding.ItemContainerFoodCategoryBinding;
import com.example.application.listeners.FoodCategoriesListener;
import com.example.application.models.FoodCategory;
import com.example.application.utilities.ImageHandler;

import java.util.List;

public class FoodCategoriesAdapter extends RecyclerView.Adapter<FoodCategoriesAdapter.FoodCategoriesViewHolder> {

    private List<FoodCategory> foodCategories;
    private LayoutInflater layoutInflater;
    private FoodCategoriesListener foodCategoriesListener;

    public FoodCategoriesAdapter(List<FoodCategory> foodCategories, FoodCategoriesListener foodCategoriesListener) {
        this.foodCategories = foodCategories;
        this.foodCategoriesListener = foodCategoriesListener;
    }

    @NonNull
    @Override
    public FoodCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerFoodCategoryBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_container_food_category, parent, false
        );
        return new FoodCategoriesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCategoriesViewHolder holder, int position) {
        holder.bindFoodCategory(foodCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return foodCategories.size();
    }

    class FoodCategoriesViewHolder extends RecyclerView.ViewHolder {

        private ItemContainerFoodCategoryBinding itemContainerFoodCategoryBinding;

        public FoodCategoriesViewHolder(ItemContainerFoodCategoryBinding itemContainerFoodCategoryBinding) {
            super(itemContainerFoodCategoryBinding.getRoot());
            this.itemContainerFoodCategoryBinding = itemContainerFoodCategoryBinding;
        }

        public void bindFoodCategory(FoodCategory foodCategory) {
            itemContainerFoodCategoryBinding.setFoodCategory(foodCategory);
            Log.i("INFO", foodCategory.getId());
            itemContainerFoodCategoryBinding.imageFoodCategory.setImageBitmap(
                    ImageHandler.getDecodedImage(foodCategory.getImage())
            );
            itemContainerFoodCategoryBinding.getRoot().setOnClickListener(view -> {
                foodCategoriesListener.onFoodCategoryClicked(foodCategory);
            });
            itemContainerFoodCategoryBinding.executePendingBindings();
        }
    }
}
