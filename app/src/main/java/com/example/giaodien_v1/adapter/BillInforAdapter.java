package com.example.giaodien_v1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giaodien_v1.R;
import com.example.giaodien_v1.auth.Session;
import com.example.giaodien_v1.model.Bill;
import com.example.giaodien_v1.model.Comment;
import com.example.giaodien_v1.model.Display;
import com.example.giaodien_v1.model.ShoppingCart;

import java.util.ArrayList;

public class BillInforAdapter extends RecyclerView.Adapter<BillInforAdapter.CommentViewHolder> {
    private Context mContext;
    ArrayList<Display> displays;
    Session session;

    public BillInforAdapter(Context mContext, ArrayList<Display> displays) {
        this.mContext = mContext;
        this.displays = displays;
    }

    @NonNull
    @Override
    public BillInforAdapter.CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.bill_card_layout,null);
        CommentViewHolder commentViewHolder = new CommentViewHolder(view);
        return commentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BillInforAdapter.CommentViewHolder holder, final int position) {
        holder.tvFoodName.setText(displays.get(position).getFoodName());
        holder.tvNumber.setText(String.valueOf(displays.get(position).getNumber()));
        holder.tvTotalCost.setText(String.valueOf(displays.get(position).getTotalCost()));

    }

    @Override
    public int getItemCount() {
        return displays.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{
        TextView tvFoodName,tvNumber,tvTotalCost;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
        tvFoodName = itemView.findViewById(R.id.food_name);
        tvNumber = itemView.findViewById(R.id.number);
        tvTotalCost = itemView.findViewById(R.id.total_cost);
        }
    }

}
