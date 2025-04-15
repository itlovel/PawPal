package com.example.pawpal.CustomMenu;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuItem implements Parcelable {
    private int id;
    private String name;
    private int calories;
    private int imageResourceId;
    private boolean isSelected;
    private int quantity;

    public MenuItem(int id, String name, int calories, int imageResourceId) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.imageResourceId = imageResourceId;
        this.isSelected = false;
        this.quantity = 1;
    }

    // Parcelable implementation
    protected MenuItem(Parcel in) {
        id = in.readInt();
        name = in.readString();
        calories = in.readInt();
        imageResourceId = in.readInt();
        isSelected = in.readByte() != 0;
        quantity = in.readInt();
    }

    public static final Creator<MenuItem> CREATOR = new Creator<MenuItem>() {
        @Override
        public MenuItem createFromParcel(Parcel in) {
            return new MenuItem(in);
        }

        @Override
        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
        }
    };

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getCalories() { return calories; }
    public int getImageResourceId() { return imageResourceId; }
    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }

    public int getQuantity() { return quantity; } // Getter quantity
    public void setQuantity(int quantity) { this.quantity = quantity; } // Setter quantity

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(calories);
        dest.writeInt(imageResourceId);
        dest.writeByte((byte) (isSelected ? 1 : 0));
        dest.writeInt(quantity);
    }
}
