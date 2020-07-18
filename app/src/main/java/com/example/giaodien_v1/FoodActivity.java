package com.example.giaodien_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.giaodien_v1.adapter.FoodAdapter;
import com.example.giaodien_v1.data.File;
import com.example.giaodien_v1.fragments.FavoriteFragment;
import com.example.giaodien_v1.fragments.HomeFragment;
import com.example.giaodien_v1.fragments.SearchFragment;
import com.example.giaodien_v1.model.CardView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {


    RecyclerView recycleView;
    ArrayList<CardView> cardViews;
    FoodAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        recycleView = findViewById(R.id.list_card_view);

        cardViews = MainActivity.databaseHelper.getAllContacts();
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodAdapter(this,cardViews);
        recycleView.setAdapter(adapter);

        adapter.setOnItemClickListener(new FoodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(FoodActivity.this,Food_Information.class);
                intent.putExtra("cardView",adapter.getItem(position));
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.ic_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                    break;
                case R.id.ic_favorite:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavoriteFragment()).commit();
                    break;
                case R.id.ic_search:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragment()).commit();
                    break;
            }
            return true;
        }
    };
}
