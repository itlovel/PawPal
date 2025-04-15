package com.example.pawpal.CustomMenu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawpal.R;

import java.util.List;

public class CustomMenuAdapter extends RecyclerView.Adapter<CustomMenuAdapter.ViewHolder> {
    private List<MenuItem> menuItems;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(MenuItem item);
    }

    public CustomMenuAdapter(List<MenuItem> menuItems, OnItemClickListener listener) {
        this.menuItems = menuItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_custom_menu, parent, false);

        // Menambahkan margin antar item
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(20, 20, 20, 20); // Margin atas, bawah, kiri, kanan
        view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItem item = menuItems.get(position);

        holder.tvMenuItemName.setText(item.getName());
        holder.tvMenuItemCalories.setText(item.getCalories() + " kcal");
        holder.ivMenuItemImage.setImageResource(item.getImageResourceId());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
    }

    @Override
    public int getItemCount() {
        return (menuItems != null) ? menuItems.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMenuItemName;
        TextView tvMenuItemCalories;
        ImageView ivMenuItemImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMenuItemName = itemView.findViewById(R.id.tvMenuItemName);
            tvMenuItemCalories = itemView.findViewById(R.id.tvMenuItemCalories);
            ivMenuItemImage = itemView.findViewById(R.id.ivMenuItemImage);
        }
    }
}