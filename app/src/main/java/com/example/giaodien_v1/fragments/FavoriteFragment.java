package com.example.giaodien_v1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giaodien_v1.Food_Information;
import com.example.giaodien_v1.MainActivity;
import com.example.giaodien_v1.R;
import com.example.giaodien_v1.adapter.FoodAdapter;
import com.example.giaodien_v1.auth.Session;
import com.example.giaodien_v1.model.CardView;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {
    RecyclerView recycleView;
    ArrayList<CardView> cardViews;
    LinearLayoutManager mLayoutManager;
    FoodAdapter adapter;
    Session session;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        recycleView = rootView.findViewById(R.id.list_card_view);
        session = new Session(getContext());





        cardViews =  MainActivity.databaseHelper.getAllFavorites(session.getUserId());

        // TODO PHAN TRUOC O DAY

        recycleView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        recycleView.setLayoutManager(mLayoutManager);
        adapter = new FoodAdapter(getActivity(),cardViews);
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new FoodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), Food_Information.class);
                intent.putExtra("cardView",adapter.getItem(position));
                startActivity(intent);
            }
        });
        return rootView;
    }
}
