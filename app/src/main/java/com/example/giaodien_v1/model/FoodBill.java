package com.example.giaodien_v1.model;

public class FoodBill {
    public int getBill_id() {
        return bill_id;
    }

    int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public FoodBill(int bill_id, int food_id) {
        this.bill_id = bill_id;
        this.food_id = food_id;
    }

    public FoodBill() {
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    int bill_id;
    int food_id;
}
