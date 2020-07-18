package com.example.giaodien_v1.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giaodien_v1.MainActivity;
import com.example.giaodien_v1.R;
import com.example.giaodien_v1.auth.Session;
import com.example.giaodien_v1.model.Comment;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private Context mContext;
    ArrayList<Comment> comments;
    Session session;

    public CommentAdapter(Context mContext, ArrayList<Comment> comments) {
        this.mContext = mContext;
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentAdapter.CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.comment,null);
        CommentViewHolder commentViewHolder = new CommentViewHolder(view);
        return commentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.CommentViewHolder holder, final int position) {
         holder.tvUser.setText(String.valueOf(comments.get(position).getUser_id()));
         holder.tvComment.setText(comments.get(position).getComment());

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{
        TextView tvUser, tvComment;
        EditText edComment;
        Button btnComment;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.user_name);
            tvComment = itemView.findViewById(R.id.comment);
        }
    }

}
