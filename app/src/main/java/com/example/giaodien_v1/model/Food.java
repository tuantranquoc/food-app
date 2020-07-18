package com.example.giaodien_v1.model;

public class Food {
    private int foodId;
    private String food_name;
    private int cost;
    private int src_img;

    public Food() {
    }

    public Food(String food_name, int cost, int src_img) {
        this.food_name = food_name;
        this.cost = cost;
        this.src_img = src_img;
    }

    public Food(int foodId, String food_name, int cost, int src_img) {
        this.foodId = foodId;
        this.food_name = food_name;
        this.cost = cost;
        this.src_img = src_img;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getSrc_img() {
        return src_img;
    }

    public void setSrc_img(int src_img) {
        this.src_img = src_img;
    }
}
