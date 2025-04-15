package com.example.pawpal.FoodRecomendation;

//import android.os.Parcel;
//import android.os.Parcelable;
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.pawpal.adapter.FoodAdapter
//import com.example.yourapp.model.FoodData


















//public class FoodRecommendation implements Parcelable {
//    private int id;
//    private String name;
//    private String category;
//    private int calories;
////    private int imageResourceId;
//    private boolean isSelected;
//
//    public FoodRecommendation(int id, String name, String category, int calories) {
//        this.id = id;
//        this.name = name;
//        this.category = category;
//        this.calories = calories;
////        this.imageResourceId = imageResourceId;
//        this.isSelected = false;
//    }
//
//    protected FoodRecommendation(Parcel in) {
//        id = in.readInt();
//        name = in.readString();
//        category = in.readString();
//        calories = in.readInt();
////        imageResourceId = in.readInt();
//        isSelected = in.readByte() != 0;
//    }
//
//    public FoodRecommendation(int i, String renalCare, String sarapan, int i1, int icRenalCare) {
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(id);
//        dest.writeString(name);
//        dest.writeString(category);
//        dest.writeInt(calories);
////        dest.writeInt(imageResourceId);
//        dest.writeByte((byte) (isSelected ? 1 : 0));
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    public static final Creator<FoodRecommendation> CREATOR = new Creator<FoodRecommendation>() {
//        @Override
//        public FoodRecommendation createFromParcel(Parcel in) {
//            return new FoodRecommendation(in);
//        }
//
//        @Override
//        public FoodRecommendation[] newArray(int size) {
//            return new FoodRecommendation[size];
//        }
//    };
//
//    // Getters and setters
//    public int getId() { return id; }
//    public void setId(int id) { this.id = id; }
//
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//
//    public String getCategory() { return category; }
//    public void setCategory(String category) { this.category = category; }
//
//    public int getCalories() { return calories; }
//    public void setCalories(int calories) { this.calories = calories; }
//
////    public int getImageResourceId() { return imageResourceId; }
////    public void setImageResourceId(int imageResourceId) { this.imageResourceId = imageResourceId; }
//
//    public boolean isSelected() { return isSelected; }
//    public void setSelected(boolean selected) { isSelected = selected; }
//}
