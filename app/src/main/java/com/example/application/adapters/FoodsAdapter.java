package com.example.application.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.R;
import com.example.application.databinding.ItemContainerFoodsBinding;
import com.example.application.models.Food;
import com.example.application.utilities.ImageHandler;

import java.util.List;

public class FoodsAdapter extends RecyclerView.Adapter<FoodsAdapter.FoodsViewHolder> {

    private List<Food> foods;
    private LayoutInflater layoutInflater;

    public FoodsAdapter(List<Food> foods) {
        this.foods = foods;
    }

    @NonNull
    @Override
    public FoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerFoodsBinding itemContainerFoodsBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_container_foods, parent, false
        );

        return new FoodsViewHolder(itemContainerFoodsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodsViewHolder holder, int position) {
        holder.bindFoods(foods.get(position));
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    static class FoodsViewHolder extends RecyclerView.ViewHolder {

        private ItemContainerFoodsBinding itemContainerFoodsBinding;

        public FoodsViewHolder(ItemContainerFoodsBinding itemContainerFoodsBinding) {
            super(itemContainerFoodsBinding.getRoot());
            this.itemContainerFoodsBinding = itemContainerFoodsBinding;
        }

        public void bindFoods(Food food) {
            itemContainerFoodsBinding.setFood(food);
            itemContainerFoodsBinding.imageFood.setImageBitmap(ImageHandler.getDecodedImage(food.getImage()));
            itemContainerFoodsBinding.executePendingBindings();
        }
    }
}
