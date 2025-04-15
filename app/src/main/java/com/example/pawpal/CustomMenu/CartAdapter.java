package com.example.pawpal.CustomMenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawpal.R;

import java.util.List;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<MenuItem> cartList;
    private OnItemClickListener listener;

    public CartAdapter(List<MenuItem> cartList, OnItemClickListener listener) {
        this.cartList = cartList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        MenuItem item = cartList.get(position);
        holder.itemName.setText(item.getName());
        holder.itemCalories.setText(item.getCalories() + " kcal");
        holder.itemImage.setImageResource(item.getImageResourceId());
        holder.tvQuantity.setText(String.valueOf(item.getQuantity()));

        holder.btnIncrease.setOnClickListener(v -> {
            if (listener != null) {
                listener.onIncreaseQuantity(position);
            }
        });

        holder.btnDecrease.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDecreaseQuantity(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemCalories, tvQuantity;
        ImageView itemImage;
        Button btnIncrease, btnDecrease;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.tvCartItemName);
            itemCalories = itemView.findViewById(R.id.tvCartItemCalories);
            itemImage = itemView.findViewById(R.id.ivCartItemImage);
            tvQuantity = itemView.findViewById(R.id.tvCartItemQuantity);
            btnIncrease = itemView.findViewById(R.id.btnAdd);
            btnDecrease = itemView.findViewById(R.id.btnRemove);
        }
    }

    public interface OnItemClickListener {
        void onIncreaseQuantity(int position);
        void onDecreaseQuantity(int position);
    }
}