package com.example.giaodien_v1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giaodien_v1.FoodActivity;
import com.example.giaodien_v1.Food_Information;
import com.example.giaodien_v1.MainActivity;
import com.example.giaodien_v1.R;
import com.example.giaodien_v1.adapter.FoodAdapter;
import com.example.giaodien_v1.auth.Session;
import com.example.giaodien_v1.model.CardView;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    RecyclerView recycleView;
    ArrayList<CardView> cardViews;
    LinearLayoutManager mLayoutManager;
    FoodAdapter adapter;
    Session session;
    EditText editText;
    Button button;

    public static SearchFragment newInstance(Bundle b) {
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(b);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        recycleView = rootView.findViewById(R.id.list_card_view);
        session = new Session(getContext());
        cardViews = new ArrayList<>();
        editText = rootView.findViewById(R.id.search);
        String tag = null;
        if (getArguments() != null) {
            tag = getArguments().getString("tag");

            Toast.makeText(getContext(), tag, Toast.LENGTH_LONG).show();
        }
        if (tag == null) {
            Log.e("TAG", "fa");
            Log.d("TAG", "fa");
            Log.v("TAG", "fa");
        }
        if (tag != null) {
            Log.e("TAG", tag);
            Log.d("TAG", tag);
            Log.v("TAG", tag);
            cardViews = MainActivity.databaseHelper.getAllFoodByTagName(tag);
            recycleView.setHasFixedSize(true);

            mLayoutManager = new LinearLayoutManager(getContext());
            recycleView.setLayoutManager(mLayoutManager);
            adapter = new FoodAdapter(getActivity(), cardViews);
            recycleView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            if(adapter.getCount()!=0){
                adapter.setOnItemClickListener(new FoodAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getActivity(), Food_Information.class);
                        intent.putExtra("cardView", adapter.getItem(position));
                        startActivity(intent);
                    }
                });
            }
        }
        button = rootView.findViewById(R.id.btn_search);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardViews = MainActivity.databaseHelper.findFoodByName(editText.getText().toString());
                Log.e("TAG", String.valueOf(cardViews.size()));

                // TODO PHAN TRUOC O DAY

                recycleView.setHasFixedSize(true);

                mLayoutManager = new LinearLayoutManager(getContext());
                recycleView.setLayoutManager(mLayoutManager);
                adapter = new FoodAdapter(getActivity(), cardViews);
                recycleView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                if(adapter.getCount()!=0){
                    adapter.setOnItemClickListener(new FoodAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Intent intent = new Intent(getActivity(), Food_Information.class);
                            intent.putExtra("cardView", adapter.getItem(position));
                            startActivity(intent);
                        }
                    });
                }
            }
        });
        return rootView;
    }
}
