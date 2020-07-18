package com.example.giaodien_v1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giaodien_v1.adapter.CartAdapter;
import com.example.giaodien_v1.adapter.FoodAdapter;
import com.example.giaodien_v1.auth.Session;
import com.example.giaodien_v1.fragments.BillFragment;
import com.example.giaodien_v1.fragments.FavoriteFragment;
import com.example.giaodien_v1.fragments.HomeFragment;
import com.example.giaodien_v1.fragments.MessageFragment;
import com.example.giaodien_v1.fragments.ProfileFragment;
import com.example.giaodien_v1.fragments.SearchFragment;
import com.example.giaodien_v1.fragments.SettingFragment;
import com.example.giaodien_v1.fragments.ShoppingCartFragment;
import com.example.giaodien_v1.model.CardView;
import com.example.giaodien_v1.model.Food;
import com.example.giaodien_v1.model.ShoppingCart;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Food_Information extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recycleView;
    double v1,v2;
    LocationManager locationManager;
    RecyclerView recyclerViewDialog;
    RecyclerView tagViewAbove;
    ArrayList<String> tags;
    CartAdapter cartAdapter;
    ArrayList<CardView> cardViews;
    FoodAdapter adapter;
    ImageView imageView;
    Button btnRestaurant, btnAddToCart,checkTag, btnAddToCartLayout, location;
    Button btnIncrease, btnDecrease;
    TextView tvNumber,tvFoodName, tvFoodCost,tvRestaurant;
    Session session;
    private DrawerLayout drawerLayout;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_information_layout);


        findViewByIds();
        setLayoutListTags();

        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.fair));
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu menuNav = navigationView.getMenu();
        MenuItem item = menuNav.findItem(R.id.ic_bill);
        MenuItem item1 = menuNav.findItem(R.id.ic_account);
        MenuItem item2 = menuNav.findItem(R.id.ic_log_out);
        if(session.getUserId()==-1){
            item1.setVisible(true);
        }else item1.setVisible(false);
        if(session.getUserId()==-1){
            item2.setVisible(false);
        }else item2.setVisible(true);
        if(session.getRole()==1){
            item.setVisible(true);
        }
        toggle.syncState();

        cardViews = MainActivity.databaseHelper.getAllContacts();
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodAdapter(this,cardViews);
        recycleView.setAdapter(adapter);

       CardView cardView0 = getIntent().getParcelableExtra("cardView");
       cardView0.getFoodId();
       imageView.setImageResource(cardView0.getSrcImage());


        adapter.setOnItemClickListener(new FoodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(Food_Information.this, Food_Information.class);
                intent.putExtra("cardView",adapter.getItem(position));
                startActivity(intent);
            }
        });
       tvFoodName.setText(cardView0.getFoodName());
  //      int cost = MainActivity.databaseHelper.findCostByFoodId(cardView0.getFoodId());
   //    tvFoodCost.setText(String.valueOf(cost));
   //     MainActivity.databaseHelper.getAllFoodTagByFoodId(cardView.getFoodId()).toString()

        setBtn();
     //   setupDrawerContent(navigationView);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);


    }

    public void setBtnRestaurant(){
        btnRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Food_Information.this,RestaurentTest.class);
                startActivity(intent);
            }
        });
    }

    public void setBtnAddToCartLayout(){
        btnAddToCartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog fbDialogue = new Dialog(Food_Information.this, R.style.DialogAnimation);
                fbDialogue.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                fbDialogue.setContentView(R.layout.add_to_cart_layout);

                btnIncrease = fbDialogue.findViewById(R.id.increase);
                btnDecrease = fbDialogue.findViewById(R.id.decrease);
                tvNumber = fbDialogue.findViewById(R.id.number);
                btnAddToCart = fbDialogue.findViewById(R.id.add_to_cart);

                setBtnIncrease();
                setBtnDecrease();
                setBtnAddToCart();

                CardView cardView = getIntent().getParcelableExtra("cardView");
                tags = MainActivity.databaseHelper.getAllFoodTagByFoodId(cardView.getFoodId());

                recyclerViewDialog = fbDialogue.findViewById(R.id.list_tag);
                recyclerViewDialog.setHasFixedSize(true);
                recyclerViewDialog.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                cartAdapter = new CartAdapter(getApplicationContext(),tags);
                recyclerViewDialog.setAdapter(cartAdapter);

                recyclerViewDialog.setLayoutManager(layoutManager);
                fbDialogue.setCancelable(true);
                fbDialogue.show();
            }
        });
    }

    public void findViewByIds(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(Food_Information.this);
        navigationView.bringToFront();
        View header = navigationView.getHeaderView(0);
        session = new Session(getApplicationContext());
        tags = new ArrayList<>();
        recycleView = findViewById(R.id.list_card_view);
        btnRestaurant = findViewById(R.id.restaurant);
        imageView = findViewById(R.id.food_image);
        btnAddToCartLayout = findViewById(R.id.add_to_cart);
        tagViewAbove = findViewById(R.id.list_tag);
        tvFoodName = findViewById(R.id.food_name);
        tvFoodCost = findViewById(R.id.food_cost);
        location = findViewById(R.id.location);
    }

    public void setLayoutListTags(){
        CardView cardView = getIntent().getParcelableExtra("cardView");
        tags = MainActivity.databaseHelper.getAllFoodTagByFoodId(cardView.getFoodId());

        tagViewAbove.setHasFixedSize(true);
        tagViewAbove.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        cartAdapter = new CartAdapter(getApplicationContext(),tags);
        tagViewAbove.setAdapter(cartAdapter);
        tagViewAbove.setLayoutManager(layoutManager);
    }

    public  void setCartAdapter(){
        cartAdapter.setOnItemClickListener(new CartAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                cardViews.clear();
                cardViews.addAll(MainActivity.databaseHelper.getAllFoodByTag(1));
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void setBtnIncrease(){
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     int number = Integer.parseInt(tvNumber.getText().toString());
                      number+= 1;
                     tvNumber.setText(String.valueOf(number));
            }
        });
    }
    public void setBtnDecrease(){
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(tvNumber.getText().toString());
                if(number != 0){
                    number -= 1;
                }
                tvNumber.setText(String.valueOf(number));
            }
        });
    }

    public void setBtn(){
        setBtnRestaurant();
        setLocation();
        setCartAdapter();
        setBtnAddToCartLayout();
    }

    public void setBtnAddToCart(){
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardView cardView = getIntent().getParcelableExtra("cardView");
                int cost = MainActivity.databaseHelper.findCostByFoodId(cardView.getFoodId());
                Intent intent = new Intent(Food_Information.this,Food_Information.class);
                ShoppingCart shoppingCart = new ShoppingCart(session.getUserId(),cardView.getFoodId(),
                        Integer.parseInt(tvNumber.getText().toString()),cost);
                shoppingCart.setTotalCostByCost(cost);
                String result = "0";
                if (MainActivity.databaseHelper.insert(shoppingCart)){
                    result = "1";
                }
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG);
                intent.putExtra("shoppingCart",shoppingCart);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShoppingCartFragment()).commit();
            }
        });
    }

    public void setLocation(){
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Food_Information.this,GetCurrentLocation.class);
                startActivity(intent);
            }
        });
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.ic_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                    break;
                case R.id.ic_favorite:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavoriteFragment()).commit();
                    break;
                case R.id.ic_search:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragment()).commit();
                    break;
                case R.id.ic_shopping_cart:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShoppingCartFragment()).commit();
                    break;
            }
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.ic_message:
                Toast.makeText(getApplicationContext(),"THIS",Toast.LENGTH_LONG).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MessageFragment()).commit();
                break;
            case R.id.ic_bill:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BillFragment()).commit();
                break;
            case R.id.ic_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                break;
            case R.id.ic_setting:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingFragment()).commit();
                break;
            case R.id.ic_share:
                Intent intent = new Intent(Food_Information.this, RestaurentTest.class);
                startActivity(intent);
                break;
            case R.id.ic_account:
                Intent intent01 = new Intent(Food_Information.this, AuhtActivity.class);
                startActivity(intent01);
                break;
            case R.id.ic_file_check:
                Intent intent02 = new Intent(Food_Information.this, FoodActivity.class);
                startActivity(intent02);
                break;
            case R.id.ic_log_out:
                session.clear();
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
