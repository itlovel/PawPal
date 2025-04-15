package com.example.pawpal.CustomMenu;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawpal.PetMealPlannerActivity;
import com.example.pawpal.R;

import java.util.ArrayList;
import java.util.List;

public class CustomMenuActivity extends AppCompatActivity {
    private List<MenuItem> menuItems;
    private List<MenuItem> selectedItems;
    private CustomMenuAdapter adapter;
    private RecyclerView rvCustomMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_menu);

        // Initialize views
        rvCustomMenu = findViewById(R.id.rvCustomMenu);
        EditText etSearchMenu = findViewById(R.id.etSearchMenu);
        Button btnNext = findViewById(R.id.btnNext);
        Button btnBack = findViewById(R.id.btnBack);

        // Initialize lists
        menuItems = new ArrayList<>();
        selectedItems = new ArrayList<>();

        // Populate menu items
        menuItems.add(new MenuItem(1, "Renal Care", 100, R.drawable.ic_renal_care));
        menuItems.add(new MenuItem(2, "Whiskas Meal", 100, R.drawable.ic_makanan_alami));
        menuItems.add(new MenuItem(3, "Cube", 100, R.drawable.ic_cube));
        menuItems.add(new MenuItem(4, "Lamb", 100, R.drawable.ic_lamb));
        menuItems.add(new MenuItem(1, "Renal Care", 100, R.drawable.ic_renal_care));
        menuItems.add(new MenuItem(2, "Whiskas Meal", 100, R.drawable.ic_makanan_alami));
        menuItems.add(new MenuItem(3, "Cube", 100, R.drawable.ic_cube));
        menuItems.add(new MenuItem(4, "Lamb", 100, R.drawable.ic_lamb));
        menuItems.add(new MenuItem(1, "Renal Care", 100, R.drawable.ic_renal_care));
        menuItems.add(new MenuItem(2, "Whiskas Meal", 100, R.drawable.ic_makanan_alami));
        menuItems.add(new MenuItem(3, "Cube", 100, R.drawable.ic_cube));
        menuItems.add(new MenuItem(4, "Lamb", 100, R.drawable.ic_lamb));

        // Setup RecyclerView dengan GridLayoutManager (2 kolom)
        rvCustomMenu.setLayoutManager(new GridLayoutManager(this, 2));

        // Set adapter dengan listener klik item
        adapter = new CustomMenuAdapter(menuItems, new CustomMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MenuItem item) {
                if (selectedItems.contains(item)) {
                    selectedItems.remove(item);
                    Toast.makeText(CustomMenuActivity.this, item.getName() + " dihapus", Toast.LENGTH_SHORT).show();
                } else {
                    selectedItems.add(item);
                    Toast.makeText(CustomMenuActivity.this, item.getName() + " ditambahkan", Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
            }
        });

        rvCustomMenu.setAdapter(adapter);

        // Search functionality
//        etSearchMenu.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                filterMenuItems(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {}
//        });

        // Next button
        btnNext.setOnClickListener(v -> {
            if (!selectedItems.isEmpty()) {
                Intent intent = new Intent(this, CartActivity.class);
                intent.putParcelableArrayListExtra("selectedItems", new ArrayList<>(selectedItems));
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please select at least one menu item", Toast.LENGTH_SHORT).show();
            }
        });

        // Back button
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, PetMealPlannerActivity.class);
            startActivity(intent);
        });
    }

//    private void filterMenuItems(String query) {
//        List<MenuItem> filteredList = new ArrayList<>();
//        for (MenuItem item : menuItems) {
//            if (item.getName().toLowerCase().contains(query.toLowerCase())) {
//                filteredList.add(item);
//            }
//        }
//        adapter.updateList(filteredList);
//    }
}
