package com.example.giaodien_v1.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giaodien_v1.MainActivity;
import com.example.giaodien_v1.R;
import com.example.giaodien_v1.auth.Session;
import com.example.giaodien_v1.model.ShoppingCart;

import java.util.ArrayList;

public class ShoppingCartAdapterTest extends RecyclerView.Adapter<ShoppingCartAdapterTest.ShoppingCartViewHolder> {
    private Context mContext;
    ArrayList<ShoppingCart> shoppingCarts;
    Session session;
    ProgressBar progressBar;


    public ShoppingCartAdapterTest(Context mContext, ArrayList<ShoppingCart> shoppingCarts) {
        this.mContext = mContext;
        this.shoppingCarts = shoppingCarts;
    }
    public ShoppingCartAdapterTest(Context mContext, ArrayList<ShoppingCart> shoppingCarts, ProgressBar progressBar) {
        this.mContext = mContext;
        this.shoppingCarts = shoppingCarts;
        this.progressBar = progressBar;
    }

    @NonNull
    @Override
    public ShoppingCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.shopping_cart_layout,null);
        ShoppingCartViewHolder shoppingCartViewHolder =  new ShoppingCartViewHolder(view);
        return shoppingCartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ShoppingCartViewHolder holder, final int position) {
        session = new Session(mContext);
        holder.imageView.setImageResource(MainActivity.databaseHelper.findSrcById(shoppingCarts.get(position).getFood_id()));
        holder.edFoodName.setText(MainActivity.databaseHelper.findNameByFoodId(shoppingCarts.get(position).getFood_id()));
         holder.tvNumber.setText(String.valueOf(shoppingCarts.get(position).getNumber()));
         holder.edTotalCost.setText(String.valueOf(shoppingCarts.get(position).getTotal_cost()));

         holder.btnIn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                int number = Integer.parseInt(holder.tvNumber.getText().toString());
                int foodCost = shoppingCarts.get(position).getCost();
                number ++;
                foodCost*=number;
                final int cost = foodCost;
                holder.tvNumber.setText(String.valueOf(number));

                 holder.edTotalCost.post(new Runnable() {
                     @Override
                     public void run() {
                         holder.edTotalCost.setText(String.valueOf(cost));
                     }
                 });
                    holder.edTotalCost.clearComposingText();
                 holder.edTotalCost.setText(String.valueOf(foodCost));
                 shoppingCarts.get(position).setNumber(number);
                 notifyDataSetChanged();
                 MainActivity.databaseHelper.updateShoppingCart(shoppingCarts.get(position));
             }
         });
         holder.btnDe.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 int number = Integer.parseInt(holder.tvNumber.getText().toString());
                 int foodCost = shoppingCarts.get(position).getCost();
                 if(number!=0)
                    number --;
                 foodCost*=number;
                 final int cost = foodCost;
                 holder.tvNumber.setText(String.valueOf(number));
                 holder.edTotalCost.setText(String.valueOf(foodCost));
                 holder.edTotalCost.post(new Runnable() {
                     @Override
                     public void run() {
                         holder.edTotalCost.setText(String.valueOf(cost));
                     }
                 });
                 shoppingCarts.get(position).setNumber(number);
                 notifyDataSetChanged();
                 MainActivity.databaseHelper.updateShoppingCart(shoppingCarts.get(position));
             }
         });

         holder.tvRemove.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 MainActivity.databaseHelper.remove(shoppingCarts.get(position).getShoppingCartId());
                 shoppingCarts.clear();
                 shoppingCarts.addAll(MainActivity.databaseHelper.findAllShoppingCartByUserId(session.getUserId()));
                 notifyDataSetChanged();
             }
         });


    }

    @Override
    public int getItemCount() {
        return shoppingCarts.size();
    }

    public class ShoppingCartViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvFoodName, tvFoodCost, tvNumber, tvRemove;
        Button btnIn, btnDe, btnPay;
        EditText edFoodName, edTotalCost;
        ProgressBar progressBar;

        public ShoppingCartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRemove = itemView.findViewById(R.id.remove);
            btnIn = itemView.findViewById(R.id.increase);
            btnDe = itemView.findViewById(R.id.decrease);
            tvNumber = itemView.findViewById(R.id.number);
            imageView = itemView.findViewById(R.id.food_image);
            tvFoodName = itemView.findViewById(R.id.food_name);
            edFoodName = itemView.findViewById(R.id.ed_food_name);
            edTotalCost = itemView.findViewById(R.id.ed_total_cost);
            tvFoodCost = itemView.findViewById(R.id.food_cost);
        }
    }

    public ArrayList<ShoppingCart> getList(){
        return shoppingCarts;
    }

    public void UpdateShoppingCart(){

    }
}
