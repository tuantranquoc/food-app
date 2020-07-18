package com.example.giaodien_v1.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giaodien_v1.MainActivity;
import com.example.giaodien_v1.MapsActivity;
import com.example.giaodien_v1.R;
import com.example.giaodien_v1.model.Bill;
import com.example.giaodien_v1.model.Display;
import com.example.giaodien_v1.model.ShoppingCart;

import java.util.ArrayList;

public class UserBillAdapter extends RecyclerView.Adapter<UserBillAdapter.CartViewHolder> {
    RecyclerView recyclerView;
    private Context mContext;
    ArrayList<Bill> bills;
    ArrayList<ShoppingCart> shoppingCarts;
    BillInforAdapter billInforAdapter;
    private OnItemClickListener mListener;
    ArrayList<Display> displays;
    EditText edBill, edMail, edUser,edTotalCost;
    TextView tvFoodName, tvNumber, tvTotalCost;
    Button btnLocation, btnCOnfirm;

    public UserBillAdapter(Context mContext, ArrayList<Bill> bills){
        this.bills = bills;
        this.mContext = mContext;
    }



    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public UserBillAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.bill_layout,null);
        UserBillAdapter.CartViewHolder cartViewHolder = new UserBillAdapter.CartViewHolder(view,mListener);
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserBillAdapter.CartViewHolder holder, final int position) {
        final int userId = bills.get(position).getUser_id();
        final int totalCost = bills.get(position).getTotal_cost();
        final int billId = bills.get(position).getBill_id();
        final String status = bills.get(position).getStatus();

        holder.edBillId.setText(String.valueOf(bills.get(position).getBill_id()));
        holder.edTotalCost.setText(String.valueOf(bills.get(position).getTotal_cost()));
        holder.tvStatus.setText(bills.get(position).getStatus());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(mContext, R.style.DialogAnimation);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setContentView(R.layout.bill_layout_infor);

                edBill =dialog.findViewById(R.id.bill_id);
                edUser = dialog.findViewById(R.id.user_name);
                edMail = dialog.findViewById(R.id.mail);
                edTotalCost = dialog.findViewById(R.id.total_cost);
                btnLocation = dialog.findViewById(R.id.location);
                btnCOnfirm = dialog.findViewById(R.id.confirm);



                edUser.setText(MainActivity.databaseHelper.findUsernameByUserId(userId));
                edMail.setText( MainActivity.databaseHelper.findUsermailByUserId(userId));
                edTotalCost.setText(String.valueOf(totalCost));
                edBill.setText(String.valueOf(billId));

                displays = MainActivity.databaseHelper.findAllDisplay(billId);

                recyclerView = dialog.findViewById(R.id.list_card_view);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

                shoppingCarts = new ArrayList<>();
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                billInforAdapter = new BillInforAdapter(mContext,displays);
                recyclerView.setAdapter(billInforAdapter);

                recyclerView.setLayoutManager(layoutManager);

                btnLocation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.databaseHelper.findLocationByBillId(billId);
                        Intent intent = new Intent(mContext, MapsActivity.class);
                        intent.putExtra("v1",MainActivity.databaseHelper.findLocationByBillId(billId).getV1());
                       intent.putExtra("v2",MainActivity.databaseHelper.findLocationByBillId(billId).getV2());
                       mContext.startActivity(intent);
                    }
                });
                btnCOnfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int cost = MainActivity.databaseHelper.getFund(userId);
                        if(status.equals("process")){
                            if(cost > totalCost){
                                MainActivity.databaseHelper.updateFund(userId,cost - totalCost);
                                MainActivity.databaseHelper.changeToPaid(bills.get(position));
                                bills.clear();
                                bills.addAll(MainActivity.databaseHelper.getAllBillByBillId(userId));
                                notifyDataSetChanged();
                            }else Toast.makeText(mContext.getApplicationContext(),"Your fund have problem!",Toast.LENGTH_LONG).show();
                        }
                        else Toast.makeText(mContext.getApplicationContext(),"Your bill is not being processed!",Toast.LENGTH_LONG).show();

                    }
                });
                dialog.setCancelable(true);
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bills.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        TextView tvBillId,tvTotalCost, tvStatus;
        CardView cardView;
        EditText edBillId, edTotalCost;
        public CartViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            tvBillId = itemView.findViewById(R.id.bill_id);
            tvTotalCost = itemView.findViewById(R.id.total_cost);
            tvStatus = itemView.findViewById(R.id.status);
            cardView = itemView.findViewById(R.id.card_view);
            edBillId = itemView.findViewById(R.id.ed_bill_id);
            edTotalCost = itemView.findViewById(R.id.ed_total_cost);
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
