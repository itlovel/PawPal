package com.example.pawpal.FoodRecomendation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.pawpal.CustomMenu.MenuItem;
import com.example.pawpal.FoodData;
import com.example.pawpal.R;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private List<FoodData> foodList;

    public FoodAdapter(List<FoodData> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_recommendation, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodData food = foodList.get(position);
        holder.foodName.setText(food.getName());
        holder.foodCalories.setText(food.getCalories() + " kcal");
        holder.foodCategory.setText(food.getCategory());
        holder.foodDescription.setText(food.getDescription());


        Glide.with(holder.itemView.getContext())
                .load(food.getImageUrl())
                .placeholder(R.drawable.placeholder_image) // Tambahkan gambar placeholder di drawable
                .into(holder.foodImage);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView foodName, foodCalories, foodCategory, foodDescription;
        ImageView foodImage;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.tvFoodName);
            foodCalories = itemView.findViewById(R.id.tvMenuItemCaloriesRecomendation);
            foodCategory = itemView.findViewById(R.id.tvFoodCategory);
            foodImage = itemView.findViewById(R.id.imgFoodIcon);
            foodDescription = itemView.findViewById(R.id.tvFoodDescription);
        }
    }
}












//public class FoodRecommendationAdapter extends RecyclerView.Adapter<FoodRecommendationAdapter.ViewHolder> {
//    private List<FoodRecommendation> foodList;
//    private Context context;
//
//    public FoodRecommendationAdapter(Context context, List<FoodRecommendation> foodList) {
//        this.context = context;
//        this.foodList = foodList;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_recommendation, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        FoodRecommendation food = foodList.get(position);
//        holder.tvFoodName.setText(food.getName());
//        holder.tvFoodDescription.setText(food.getDescription());
//        holder.tvCalories.setText(food.getCalories() + " kcal");
//
//        Glide.with(context).load(food.getImageUrl()).into(holder.ivFoodImage);
//    }
//
//    @Override
//    public int getItemCount() {
//        return foodList.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView ivFoodImage;
//        TextView tvFoodName, tvFoodDescription, tvCalories;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            ivFoodImage = itemView.findViewById(R.id.ivFoodImage);
//            tvFoodName = itemView.findViewById(R.id.tvFoodName);
//            tvFoodDescription = itemView.findViewById(R.id.tvFoodDescription);
//            tvCalories = itemView.findViewById(R.id.tvCalories);
//        }
//    }
//}