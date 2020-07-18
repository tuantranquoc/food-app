package com.example.giaodien_v1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giaodien_v1.R;
import com.example.giaodien_v1.model.CardView;

import java.util.ArrayList;

public class FoodAdapterv1 extends RecyclerView.Adapter<FoodAdapterv1.FoodViewHolder> {
    private Context mContext;
    ArrayList<CardView> cardViews;


    public FoodAdapterv1(Context mContext, ArrayList<CardView>cardViews){
        this.mContext = mContext;
        this.cardViews = cardViews;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.fragment_restaurant_food_layout,null);
        FoodViewHolder foodViewHolder = new FoodViewHolder(view);
        return foodViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        CardView cardView = cardViews.get(position);
        holder.imageView.setImageResource(cardView.getSrcImage());
    }

    @Override
    public int getItemCount() {
        return cardViews.size();
    }


    public class FoodViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.food_image);
        }
    }
}
