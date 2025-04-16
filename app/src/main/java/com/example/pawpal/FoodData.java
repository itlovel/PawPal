package com.example.pawpal;

public class FoodData {
    private String name;
    private int calories;
    private String category;
    private String description;
    private String imageUrl;
    private String day;

    public String getDay() { return day; }

    public FoodData() {} // Diperlukan oleh Firestore

    public FoodData(String name, int calories, String category, String description, String imageUrl) {
        this.name = name;
        this.calories = calories;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getName() { return name; }
    public int getCalories() { return calories; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
}