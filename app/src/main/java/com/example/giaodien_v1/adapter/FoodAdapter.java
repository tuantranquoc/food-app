package com.example.giaodien_v1.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giaodien_v1.Food_Information;
import com.example.giaodien_v1.MainActivity;
import com.example.giaodien_v1.R;
import com.example.giaodien_v1.auth.Session;
import com.example.giaodien_v1.model.CardView;
import com.example.giaodien_v1.model.Comment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private Context mContext;
    ArrayList<CardView> cardViews;
    private OnItemClickListener mListener;
    RecyclerView recyclerView;
    CommentAdapter commentAdapter;
    EditText editText;
    Button button;
    Session session;
    int itemJump = 5;

    public FoodAdapter(Context mContext, ArrayList<CardView>cardViews){
        this.mContext = mContext;
        this.cardViews = cardViews;
        session = new Session(mContext);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.card_view_layout,null);
        FoodViewHolder foodViewHolder = new FoodViewHolder(view,mListener);
        return foodViewHolder;
    }

    public void hideItem(){
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, final int position) {
        final CardView cardView = cardViews.get(position);
        holder.textView.setText(cardView.getFoodName());
        holder.imageView.setImageResource(cardView.getSrcImage());
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.databaseHelper.insertFavorite(cardViews.get(position),session.getUserId());
            }
        });
        if(position%2==0){
            holder.cardView.setCardBackgroundColor(Color.parseColor("#efe2ba"));
        }
        holder.tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(mContext, R.style.DialogAnimation);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setContentView(R.layout.comment_layout);


                editText =dialog.findViewById(R.id.comment);
                button = dialog.findViewById(R.id.action_comment);

       //         MainActivity.databaseHelper.insertCommnet(1,1,"THIS IS MY COMMENT");


                final ArrayList<Comment> comments = MainActivity.databaseHelper.getAllCommentByFoodId(cardView.getFoodId());
                Log.e("TAG",String.valueOf(comments.size()));
                recyclerView = dialog.findViewById(R.id.list_comment);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                commentAdapter = new CommentAdapter(mContext,comments);
                recyclerView.setAdapter(commentAdapter);

                recyclerView.setLayoutManager(layoutManager);
                editText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event != null &&
                                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (event == null || !event.isShiftPressed()) {
                                // the user is done typing.
                                MainActivity.databaseHelper.insertCommnet(session.getUserId(),cardView.getFoodId(),editText.getText().toString());
                                comments.clear();
                                comments.addAll(MainActivity.databaseHelper.getAllCommentByFoodId(cardView.getFoodId()));
                                commentAdapter.notifyDataSetChanged();
                                return true; // consume.
                            }
                        }
                        return false;
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!editText.getText().toString().equals("")){
                            MainActivity.databaseHelper.insertCommnet(session.getUserId(),cardView.getFoodId(),editText.getText().toString());
                            comments.clear();
                            comments.addAll(MainActivity.databaseHelper.getAllCommentByFoodId(cardView.getFoodId()));
                            commentAdapter.notifyDataSetChanged();
                            editText.getText().clear();
                        }
                    }
                });
                dialog.setCancelable(true);
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardViews.size();
    }

    public int getCount(){
        if(cardViews.isEmpty()){
            return 0;
        }else return cardViews.size();
    }

    public CardView getItem(int position){
        return cardViews.get(position);
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{
        androidx.cardview.widget.CardView cardView;
        TextView textView,tvComment;
        ImageView imageView,like;
        public FoodViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textView = itemView.findViewById(R.id.food_name);
            imageView = itemView.findViewById(R.id.food_image);
            tvComment = itemView.findViewById(R.id.comment);
            like = itemView.findViewById(R.id.like);
            cardView = itemView.findViewById(R.id.card_view);
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
