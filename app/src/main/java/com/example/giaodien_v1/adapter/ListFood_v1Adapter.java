package com.example.giaodien_v1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giaodien_v1.R;
import com.example.giaodien_v1.model.Url;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ListFood_v1Adapter extends RecyclerView.Adapter<ListFood_v1Adapter.ViewHolder> {

    Context context;
    ArrayList<Url> url;

    public ListFood_v1Adapter(Context context, ArrayList<Url> url){
        this.context = context;
        this.url = url;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_restaurant_food_layout,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Url mUrl = url.get(position);
      // holder.textView.setText("THIS IS THE TEXT");
       holder.imageView.setImageResource(mUrl.getUrl());
  //      holder.imageView.setImageDrawable(mContext.getResources().getDrawable(food.getImage()));
    }

    @Override
    public int getItemCount() {
        return url.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
       //     textView = itemView.findViewById(R.id.food_name);
            imageView = itemView.findViewById(R.id.food_image);
        }
    }
}
