package com.example.giaodien_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.giaodien_v1.adapter.FoodAdapter;
import com.example.giaodien_v1.adapter.FoodAdapterv1;
import com.example.giaodien_v1.adapter.ListFood_v1Adapter;
import com.example.giaodien_v1.data.File;
import com.example.giaodien_v1.fragments.HomeFragment;
import com.example.giaodien_v1.fragments.RestaurentFragment;
import com.example.giaodien_v1.model.CardView;
import com.example.giaodien_v1.model.Url;

import java.util.ArrayList;

public class RestaurentTest extends AppCompatActivity {

    Button btnOpenMap;

    RecyclerView recyclerView;
    ListFood_v1Adapter adapter;
    ArrayList<Url> url;

    RecyclerView recycleView;
    ArrayList<CardView> cardViews;
    FoodAdapterv1 adapter01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_restaurent_copy);

        btnOpenMap = findViewById(R.id.map);
        cardViews = MainActivity.databaseHelper.getAllContacts();

       /*
        url = new ArrayList<>();
        url.add(new Url(R.drawable.milk_shake));
        url.add(new Url(R.drawable.fried_checken));
        url.add(new Url(R.drawable.taco));
        url.add(new Url(R.drawable.cheeseburger));
        url.add(new Url(R.drawable.burrito));
        url.add(new Url(R.drawable.taco));
        recyclerView = findViewById(R.id.list_item);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter =new ListFood_v1Adapter(this,url);
        recyclerView.setAdapter(adapter);*/


        recyclerView = findViewById(R.id.list_item);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter01 = new FoodAdapterv1(this,cardViews);
        recyclerView.setAdapter(adapter01);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        RecyclerView myList = (RecyclerView) findViewById(R.id.list_item);
        myList.setLayoutManager(layoutManager);

        setBtnOpenMap();
    }

    public void setBtnOpenMap(){
        btnOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(RestaurentTest.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
