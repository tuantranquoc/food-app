package com.example.giaodien_v1.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ShoppingCart implements Parcelable {
    private int shoppingCartId;
    private int userId;
    private int food_id;
    private int number;
    private int cost;
    private int total_cost;

    public ShoppingCart(int shoppingCartId, int userId, int food_id, int number, int cost, int total_cost) {
        this.shoppingCartId = shoppingCartId;
        this.userId = userId;
        this.food_id = food_id;
        this.number = number;
        this.cost = cost;
        this.total_cost = total_cost;
    }

    public int getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(int shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(int total_cost) {
        this.total_cost = total_cost;
    }

    public void setTotalCostByCost(int cost){
        this.total_cost = cost*number;
    }

    public ShoppingCart(int userId, int food_id, int number, int cost, int total_cost) {
        this.userId = userId;
        this.food_id = food_id;
        this.number = number;
        this.cost = cost;
        this.total_cost = total_cost;
    }

    public ShoppingCart(int userId, int food_id, int number, int cost) {
        this.userId = userId;
        this.food_id = food_id;
        this.number = number;
        this.cost = cost;
    }



    protected ShoppingCart(Parcel in) {
        String[] data = new String[5];
        in.readStringArray(data);
        this.userId = Integer.parseInt(data[0]);
        this.food_id = Integer.parseInt(data[1]);
        this.number = Integer.parseInt(data[2]);
        this.cost =Integer.parseInt(data[3]);
        this.total_cost =   Integer.parseInt(data[4]);
    }

    public static final Creator<ShoppingCart> CREATOR = new Creator<ShoppingCart>() {
        @Override
        public ShoppingCart createFromParcel(Parcel in) {
            return new ShoppingCart(in);
        }

        @Override
        public ShoppingCart[] newArray(int size) {
            return new ShoppingCart[size];
        }
    };

    public int getTotalCost(int Number) {
        return total_cost;
    }

    public ShoppingCart(int userId, int food_id, int number) {
        this.userId = userId;
        this.food_id = food_id;
        this.number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{String.valueOf(this.userId), String.valueOf(this.food_id), String.valueOf(this.number)
        ,String.valueOf(this.cost),String.valueOf(this.total_cost)});
    }

}
