package com.example.giaodien_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.giaodien_v1.auth.Session;

import com.example.giaodien_v1.database.DatabaseHelper;
import com.example.giaodien_v1.database.FoodDatabaseHelper;


import com.example.giaodien_v1.fragments.BillFragment;
import com.example.giaodien_v1.fragments.FavoriteFragment;
import com.example.giaodien_v1.fragments.HomeFragment;

import com.example.giaodien_v1.fragments.MessageFragment;
import com.example.giaodien_v1.fragments.ProfileFragment;
import com.example.giaodien_v1.fragments.SearchFragment;
import com.example.giaodien_v1.fragments.SettingFragment;
import com.example.giaodien_v1.fragments.ShoppingCartFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static FoodDatabaseHelper foodDatabaseHelper;
    public static DatabaseHelper databaseHelper;
    private Session session;
    private DrawerLayout drawerLayout;
    TextView tvUserName, tvEmail;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("ApiUrl = ", "MyApiUrl") ;
        Log.v("ApiUrl = ", "MyApiUrl") ;
        databaseHelper = new DatabaseHelper(this);

        session = new Session(this);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.fair));
        setSupportActionBar(toolbar);

        findViewByIds();
     //  databaseHelper.insert(databaseHelper);
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
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            //       navigationView.setCheckedItem(R.id.ic_home);
        }
        String tag  = getIntent().getStringExtra("tag");
        if(tag!=null){
            Bundle bundle = new Bundle();
            bundle.putString("tag", "fast food");
            SearchFragment f = SearchFragment.newInstance(bundle);
   //         SearchFragment fragobj = new SearchFragment();
   //         fragobj.setArguments(bundle);
      //      f.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f).commit();
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

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
                Intent intent = new Intent(MainActivity.this, RestaurentTest.class);
                startActivity(intent);
                break;
            case R.id.ic_account:
                Intent intent01 = new Intent(MainActivity.this, AuhtActivity.class);
                startActivity(intent01);
                break;
            case R.id.ic_file_check:
                Intent intent02 = new Intent(MainActivity.this, FoodActivity.class);
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

    public void findViewByIds() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        tvUserName = header.findViewById(R.id.user_name);
        tvEmail = header.findViewById(R.id.email);
        tvUserName.setText(session.getUsername());
        tvEmail.setText(session.getEmail());
    }
}
