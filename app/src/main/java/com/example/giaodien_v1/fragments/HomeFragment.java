package com.example.giaodien_v1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giaodien_v1.FoodActivity;
import com.example.giaodien_v1.Food_Information;
import com.example.giaodien_v1.MainActivity;
import com.example.giaodien_v1.MapsActivity;
import com.example.giaodien_v1.R;
import com.example.giaodien_v1.RestaurentTest;
import com.example.giaodien_v1.adapter.FoodAdapter;
import com.example.giaodien_v1.data.File;
import com.example.giaodien_v1.model.CardView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView recycleView;
    ArrayList<CardView> cardViews;
    FoodAdapter adapter;
    ProgressBar progressBar;
    LinearLayoutManager mLayoutManager;
    ArrayList<CardView> cardViews1;
    ArrayList<CardView> cardViews2;
    int jump = 5;
    int itemCount = 0;
    int cardSize = 0;
    void showProgressView() {
        progressBar.setVisibility(View.VISIBLE);
    }

    void hideProgressView() {
        progressBar.setVisibility(View.INVISIBLE);
    }
    public interface LoadMoreItems {
        void LoadItems();
    }

    public interface ILoadmore {
        void onLoadMore();
    }

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        recycleView = rootView.findViewById(R.id.list_card_view);
        progressBar = rootView.findViewById(R.id.progress_bar);






        cardViews =  MainActivity.databaseHelper.getAllContacts();

  // TODO PHAN TRUOC O DAY

        recycleView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        recycleView.setLayoutManager(mLayoutManager);
        adapter = new FoodAdapter(getActivity(),cardViews);
        recycleView.setAdapter(adapter);


        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    Log.e("visible item", String.valueOf(visibleItemCount));
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                    Log.e("past visible item", String.valueOf(pastVisiblesItems));
                    if (loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            loading = false;
                            Log.e("...", "Last Item Wow !");

                            //Do pagination.. i.e. fetch new data
                            showProgressView();
                        }
                    }
                }
            }
        });
        adapter.setOnItemClickListener(new FoodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), Food_Information.class);
                intent.putExtra("cardView",adapter.getItem(position));
                startActivity(intent);
            }
        });

  //      adapter.notifyDataSetChanged();

 //////////////////////////////////////////////////////////////


        return rootView;
    }

    public void addListCard(int k, int j){
        for (int i = k; i < j; i++) {
            cardViews1.add(cardViews.get(i));
        }
        cardViews2 = cardViews1;
    }
    public void datachanged(){
        loading = true;
        cardViews1.clear();
        cardViews1.addAll(cardViews2);

        hideProgressView();
    }
}
