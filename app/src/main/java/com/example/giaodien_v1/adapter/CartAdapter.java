package com.example.giaodien_v1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giaodien_v1.MainActivity;
import com.example.giaodien_v1.R;
import com.example.giaodien_v1.fragments.SearchFragment;
import com.example.giaodien_v1.model.CardView;
import com.example.giaodien_v1.model.Tag;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context mContext;
    ArrayList<String> tags;
    private OnItemClickListener mListener;

    public CartAdapter(Context mContext,ArrayList<String> tags){
        this.tags = tags;
        this.mContext = mContext;
    }



    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.tag_layout,null);
        CartAdapter.CartViewHolder cartViewHolder = new CartAdapter.CartViewHolder(view,mListener);
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        final String tag = tags.get(position);
        holder.tvTag.setText(tag);
        holder.tvTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("tag",tag);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        TextView tvTag;
        public CartViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            tvTag = itemView.findViewById(R.id.tag);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
