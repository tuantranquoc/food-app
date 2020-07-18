package com.example.giaodien_v1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giaodien_v1.MainActivity;
import com.example.giaodien_v1.R;
import com.example.giaodien_v1.adapter.BillAdapter;
import com.example.giaodien_v1.adapter.ShoppingCartAdapterTest;
import com.example.giaodien_v1.model.Bill;
import com.example.giaodien_v1.model.Favorite;

import java.util.ArrayList;

public class BillFragment extends Fragment {

    RecyclerView recyclerView;
    BillAdapter billAdapter;
    ArrayList<Bill>bills;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bill, container, false);

        bills = MainActivity.databaseHelper.getAllBill();
        recyclerView = rootView.findViewById(R.id.list_bill);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        billAdapter = new BillAdapter(getActivity(),bills);
        recyclerView.setAdapter(billAdapter);
        return rootView;
    }
}
