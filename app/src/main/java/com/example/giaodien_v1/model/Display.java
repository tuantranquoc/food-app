package com.example.giaodien_v1.model;

public class Display {
    String foodName;
    int number;
    int totalCost;

    public Display() {
    }

    public Display(String foodName, int number, int totalCost) {
        this.foodName = foodName;
        this.number = number;
        this.totalCost = totalCost;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
