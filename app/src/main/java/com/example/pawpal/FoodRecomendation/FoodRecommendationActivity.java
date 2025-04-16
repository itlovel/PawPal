package com.example.pawpal.FoodRecomendation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Parcelable;

import com.example.pawpal.CustomMenu.CartActivity;
import com.example.pawpal.FoodData;
import com.example.pawpal.PetMealPlannerActivity;
import com.example.pawpal.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pawpal.FoodRecomendation.FoodAdapter;
import com.example.pawpal.CustomMenu.MenuItem;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class FoodRecommendationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FoodAdapter foodAdapter;
    private List<FoodData> foodList;
    private TextView tvEmptyMessage;
    private Button btnBack;

    private FirebaseFirestore db;

    private String getCurrentDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", new Locale("id", "ID"));
        return sdf.format(new Date());


    }


    private boolean isValidCategory(String category) {
        List<String> validCategories = Arrays.asList("Sarapan", "Snack", "Makan Siang", "Snack", "Makan Malam");
        return validCategories.contains(category);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_recomendation);

        recyclerView = findViewById(R.id.recyclerViewFoodRecommendations);
        tvEmptyMessage = findViewById(R.id.tvEmptyMessage);
        btnBack = findViewById(R.id.btnBack);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TextView tvDayIndicator = findViewById(R.id.tvDayIndicator);
        tvDayIndicator.setText(getCurrentDay());

        foodList = new ArrayList<>();
        foodAdapter = new FoodAdapter(foodList);
        recyclerView.setAdapter(foodAdapter);

        db = FirebaseFirestore.getInstance();
        loadFoodData();

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                navigateBack();
            }
        });

        btnBack.setOnClickListener(v -> navigateBack());
    }

    private void loadFoodData() {
        String currentDay = getCurrentDay(); // Mendapatkan nama hari saat ini
        Log.d("FoodDay", "Hari saat ini: " + currentDay);

        db.collection("food")
                .whereEqualTo("day", currentDay)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    Log.d("FirestoreQuery", "Jumlah data yang ditemukan: " + queryDocumentSnapshots.size());
                    foodList.clear(); // Clear sebelum menambahkan data baru
                    if (queryDocumentSnapshots.isEmpty()) {
                        Log.d("FirestoreQuery", "Tidak ada data untuk hari ini.");
                        tvEmptyMessage.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    } else {
                        for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                            FoodData food = snapshot.toObject(FoodData.class);
                            if (food != null) {
                                Log.d("FoodItem", "Data: " + food.getName() + ", Kategori: " + food.getCategory()); // ✅ Log data makanan
                            }
                            if (food != null && isValidCategory(food.getCategory())) {
                                foodList.add(food);
                            }
                        }
                        foodAdapter.notifyDataSetChanged();
                        tvEmptyMessage.setVisibility(foodList.isEmpty() ? View.VISIBLE : View.GONE);
                        recyclerView.setVisibility(foodList.isEmpty() ? View.GONE : View.VISIBLE);
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("FirestoreError", "Gagal mengambil data: " + e.getMessage());
                    tvEmptyMessage.setText("Failed to load data.");
                    tvEmptyMessage.setVisibility(View.VISIBLE);
                    Toast.makeText(this, "Gagal mengambil data: " + e.getMessage(), Toast.LENGTH_SHORT).show();

                });
    }

    private void navigateBack() {
        Intent intent = new Intent(this, PetMealPlannerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
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
//
//
//
//
//
//
//
//











//public class FoodRecommendationActivity extends AppCompatActivity
//        implements FoodRecommendationAdapter.OnFoodSelectedListener {
//
//    private RecyclerView recyclerViewFoodRecommendations;
//    private FoodRecommendationAdapter foodAdapter;
//    private Button btnNext;
//    private TextView tvDayIndicator;
//    private List<FoodRecommendation> foodRecommendations;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_food_recomendation);
//
//        // Initialize views
//        recyclerViewFoodRecommendations = findViewById(R.id.recyclerViewFoodRecommendations);
////        btnNext = findViewById(R.id.btnNext);
//        tvDayIndicator = findViewById(R.id.tvDayIndicator);
//
//        // Set current day
//        setCurrentDay();
//
//        // Prepare food recommendations
//        prepareFoodRecommendations();
//
//        // Setup RecyclerView
//        setupRecyclerView();
//
//        // Setup click listeners
//        setupClickListeners();
//    }
//
//    private void setCurrentDay() {
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", new Locale("id", "ID"));
//        String currentDay = dayFormat.format(calendar.getTime());
//        tvDayIndicator.setText(currentDay);
//    }
//
//    private void prepareFoodRecommendations() {
//        foodRecommendations = new ArrayList<>();
//
//        // Add sample food recommendations with categories
//        foodRecommendations.add(new FoodRecommendation(1, "Renal Care", "Sarapan", 100, R.drawable.ic_renal_care));
//
//        foodRecommendations.add(new FoodRecommendation(
//                2,
//                "Wetzle Meat 2",
//                "Snack",
//                100,
//                R.drawable.ic_wetzle_meat
//        ));
//
//        foodRecommendations.add(new FoodRecommendation(
//                3,
//                "Makanan Alami",
//                "Makan Malam",
//                100,
//                R.drawable.ic_makanan_alami
//        ));
//
//        // Add more food recommendations as needed
//    }
//
//    private void setupRecyclerView() {
//        foodAdapter = new FoodRecommendationAdapter(foodRecommendations, this);
//        recyclerViewFoodRecommendations.setLayoutManager(new LinearLayoutManager(this));
//        recyclerViewFoodRecommendations.setAdapter(foodAdapter);
//    }
//
//    private void setupClickListeners() {
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                List<FoodRecommendation> selectedFoods = foodAdapter.getSelectedFoods();
//
//                if (selectedFoods.isEmpty()) {
//                    Toast.makeText(FoodRecommendationActivity.this,
//                            "Pilih setidaknya satu makanan",
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Pass selected foods to next screen
//                Intent intent = new Intent(FoodRecommendationActivity.this, CartActivity.class);
//                intent.putParcelableArrayListExtra("SELECTED_FOODS", new ArrayList<>(selectedFoods));
//                startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    public void onFoodSelected(FoodRecommendation food, boolean isSelected) {
//        // Optional: Handle individual food selection if needed
//    }
//}
