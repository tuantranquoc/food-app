package com.example.giaodien_v1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giaodien_v1.R;
import com.example.giaodien_v1.model.ShoppingCart;


import java.util.ArrayList;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder> {
    private Context mContext;
    ArrayList<com.example.giaodien_v1.model.ShoppingCart> shoppingCarts;

    public ShoppingCartAdapter(Context mContext, ArrayList<ShoppingCart> shoppingCarts) {
        this.mContext = mContext;
        this.shoppingCarts = shoppingCarts;
    }



    @NonNull
    @Override
    public ShoppingCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.shopping_cart_test,null);
        ShoppingCartViewHolder shoppingCartViewHolder = new ShoppingCartViewHolder(view);
        return shoppingCartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingCartAdapter.ShoppingCartViewHolder holder, int position) {
        holder.tvNumber.setText(shoppingCarts.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ShoppingCartViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvFoodName, tvFoodCost, tvNumber;
        Button btnIn, btnDe;
        public ShoppingCartViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.food_image);
            tvFoodName =itemView.findViewById(R.id.food_name);
            tvNumber = itemView.findViewById(R.id.number);
            btnIn = itemView.findViewById(R.id.increase);
            btnDe = itemView.findViewById(R.id.decrease);

        }
    }
}
