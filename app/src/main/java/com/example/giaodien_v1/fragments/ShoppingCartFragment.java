package com.example.giaodien_v1.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giaodien_v1.Food_Information;
import com.example.giaodien_v1.GetCurrentLocation;
import com.example.giaodien_v1.MainActivity;
import com.example.giaodien_v1.R;
import com.example.giaodien_v1.adapter.CartAdapter;
import com.example.giaodien_v1.adapter.FoodAdapter;
import com.example.giaodien_v1.adapter.ShoppingCartAdapter;
import com.example.giaodien_v1.adapter.ShoppingCartAdapterTest;
import com.example.giaodien_v1.auth.Session;
import com.example.giaodien_v1.model.CardView;
import com.example.giaodien_v1.model.ShoppingCart;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class ShoppingCartFragment extends Fragment implements LocationListener {
    Location currentLocation;
    LocationManager locationManager;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int CODE = 101;
    double v1, v2;
    RecyclerView recycleView;
    ArrayList<ShoppingCart> shoppingCarts;
    ArrayList<CardView> cardViews;
    FoodAdapter adapter;
    ShoppingCartAdapterTest adapter01;
    Session session;
    Button btnPay;

    @Nullable
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.shopping_cart, container, false);
        shoppingCarts = new ArrayList<>();


        btnPay = rootView.findViewById(R.id.btn_pay);

        session = new Session(getContext());
        shoppingCarts = MainActivity.databaseHelper.findAllShoppingCartByUserId(session.getUserId());


        recycleView = rootView.findViewById(R.id.list_shopping_cart);


        recycleView.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycleView.setLayoutManager(layoutManager);
        adapter01 = new ShoppingCartAdapterTest(getActivity(), shoppingCarts);
        recycleView.setAdapter(adapter01);





        if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    CODE);
        }
        locationManager = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1,
                1, mLocationListener);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.databaseHelper.insert(MainActivity.databaseHelper.findAllShoppingCartByUserId(session.getUserId()));
                MainActivity.databaseHelper.insert(session.getUserId(), v1, v2);
                MainActivity.databaseHelper.insertFoodBill(MainActivity.databaseHelper.findAllShoppingCartByUserId(session.getUserId()), session.getUserId());
                MainActivity.databaseHelper.removeListSP(session.getUserId());
                shoppingCarts.clear();
                shoppingCarts.addAll(MainActivity.databaseHelper.findAllShoppingCartByUserId(session.getUserId()));
                adapter01.notifyDataSetChanged();

                Log.e("TAG", "INSERTING");
            }
        });


        return rootView;
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            v1 = location.getLatitude();
            v2 = location.getLongitude();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
