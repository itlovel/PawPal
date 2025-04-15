package com.example.pawpal.CustomMenu;


import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawpal.FoodRecomendation.FoodRecommendationActivity;
import com.example.pawpal.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnItemClickListener {
    private List<MenuItem> cartItems;
    private CartAdapter adapter;
    private TextView tvTotalItems, tvTotalCalories, tvEmptyCart;
    private RecyclerView rvCartItems;
    private Button btnNext, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rvCartItems = findViewById(R.id.rvCartItems);
        tvTotalItems = findViewById(R.id.tvTotalItems);
        tvTotalCalories = findViewById(R.id.tvTotalCalories);
        tvEmptyCart = findViewById(R.id.tvEmptyCart);
        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack);

        cartItems = getIntent().getParcelableArrayListExtra("selectedItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        rvCartItems.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartAdapter(cartItems, this);
        rvCartItems.setAdapter(adapter);

        updateTotals();

        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, FoodRecommendationActivity.class);
            startActivity(intent);
            finish();
            // Menutup CartActivity setelah berpindah
        });

//        btnNext.setOnClickListener(v -> {
//            if (cartItems == null || cartItems.isEmpty()) {
//                Toast.makeText(CartActivity.this, "Your cart is empty!", Toast.LENGTH_SHORT).show();
//                return; // Stop execution if cart is empty
//            }
//
//            Intent intent = new Intent(CartActivity.this, com.example.pawpal.FoodRecomendation.FoodRecommendationActivity.class);
//
//            // Cek apakah data Parcelable aman dikirim
//            try {
//                intent.putParcelableArrayListExtra("selectedItems", new ArrayList<>(cartItems));
//            } catch (Exception e) {
//                e.printStackTrace();
//                Toast.makeText(CartActivity.this, "Error while processing items", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            startActivity(intent);
//            finish(); // Agar CartActivity tidak bisa diakses kembali
//        });

        btnBack.setOnClickListener(v -> finish());
    }

    private void updateTotals() {
        int totalItems = 0;
        int totalCalories = 0;

        for (MenuItem item : cartItems) {
            totalItems += item.getQuantity();
            totalCalories += item.getCalories() * item.getQuantity();
        }

        tvTotalItems.setText("Total Items: " + totalItems);
        tvTotalCalories.setText("Total Calories: " + totalCalories + " kcal");

        if (totalItems == 0) {
            tvEmptyCart.setVisibility(View.VISIBLE);
            rvCartItems.setVisibility(View.GONE);
        } else {
            tvEmptyCart.setVisibility(View.GONE);
            rvCartItems.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onIncreaseQuantity(int position) {
        MenuItem item = cartItems.get(position);
        item.setQuantity(item.getQuantity() + 1);
        adapter.notifyItemChanged(position);
        updateTotals();
    }

    @Override
    public void onDecreaseQuantity(int position) {
        MenuItem item = cartItems.get(position);
        if (item.getQuantity() > 1) {
            item.setQuantity(item.getQuantity() - 1);
            adapter.notifyItemChanged(position);
        } else {
            cartItems.remove(position);
            adapter.notifyItemRemoved(position);
        }
        updateTotals();
    }







//    @Override
//    public void onIncreaseQuantity(int position) {
//        MenuItem item = cartItems.get(position);
//        item.setQuantity(item.getQuantity() + 1);
//        adapter.notifyItemChanged(position);
//        updateTotals();
//    }
//
//    @Override
//    public void onDecreaseQuantity(int position) {
//        MenuItem item = cartItems.get(position);
//        if (item.getQuantity() > 1) {
//            item.setQuantity(item.getQuantity() - 1);
//            adapter.notifyItemChanged(position);
//        } else {
//            cartItems.remove(position);
//            adapter.notifyItemRemoved(position);
//        }
//        updateTotals();
//    }
}














//public class CartActivity extends AppCompatActivity {
//    private RecyclerView rvCartItems;
//    private CartAdapter adapter;
//    private List<MenuItem> cartItems;
//    private Button btnNext;
//    private SharedPreferences sharedPreferences;
//    private static final String CART_PREFS = "cart_prefs";
//    private static final String CART_KEY = "cart_items";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cart);
//
//        rvCartItems = findViewById(R.id.rvCartItems);
//        btnNext = findViewById(R.id.btnNext);
//        sharedPreferences = getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE);
//
//        // Load data dari SharedPreferences
//        cartItems = loadCartData();
//
//        rvCartItems.setLayoutManager(new LinearLayoutManager(this));
//
//        adapter = new CartAdapter(cartItems, new CartAdapter.OnItemClickListener() {
//            @Override
//            public void onDeleteClick(int position) {
//                cartItems.remove(position);
//                adapter.notifyItemRemoved(position);
//                saveCartData();
//            }
//        });
//
//        rvCartItems.setAdapter(adapter);
//
//        btnNext.setOnClickListener(v -> {
//            clearCart();
//            Toast.makeText(this, "Cart dikosongkan!", Toast.LENGTH_SHORT).show();
//        });
//    }
//
//    // Simpan cart ke SharedPreferences
//    private void saveCartData() {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        String jsonCart = gson.toJson(cartItems);
//        editor.putString(CART_KEY, jsonCart);
//        editor.apply();
//    }
//
//    // Ambil cart dari SharedPreferences
//    private List<MenuItem> loadCartData() {
//        String jsonCart = sharedPreferences.getString(CART_KEY, null);
//        if (jsonCart != null) {
//            Gson gson = new Gson();
//            Type type = new TypeToken<List<MenuItem>>() {}.getType();
//            return gson.fromJson(jsonCart, type);
//        }
//        return new ArrayList<>();
//    }
//
//    // Hapus cart saat tombol Next ditekan
//    private void clearCart() {
//        cartItems.clear();
//        sharedPreferences.edit().remove(CART_KEY).apply();
//        adapter.notifyDataSetChanged();
//    }
//
//    // Tambahkan item ke cart
//    public void addItemToCart(MenuItem newItem) {
//        boolean found = false;
//
//        for (MenuItem item : cartItems) {
//            if (item.getId() == newItem.getId()) {
//                item.setQuantity(item.getQuantity() + 1);
//                found = true;
//                break;
//            }
//        }
//
//        if (!found) {
//            cartItems.add(newItem);
//        }
//
//        saveCartData();
//        adapter.notifyDataSetChanged();
//    }
//}
