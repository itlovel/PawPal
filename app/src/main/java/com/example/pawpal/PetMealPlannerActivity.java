package com.example.pawpal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pawpal.CustomMenu.CustomMenuActivity;
import com.example.pawpal.FoodRecomendation.FoodRecommendationActivity;

import java.util.ArrayList;

public class PetMealPlannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_meal_planner);

        Button btnFoodRecommendation = findViewById(R.id.btnFoodRecommendation);
        Button btnCustomMenu = findViewById(R.id.btnCustomMenu);

        btnFoodRecommendation.setOnClickListener(v -> {
            Intent intent = new Intent(this, FoodRecommendationActivity.class);
            intent.putParcelableArrayListExtra("selectedItems", new ArrayList<>()); // Tidak ada makanan awalnya
            startActivity(intent);
        });

        btnCustomMenu.setOnClickListener(v -> {
            Intent intent = new Intent(this, CustomMenuActivity.class);
            startActivity(intent);
        });
    }
}