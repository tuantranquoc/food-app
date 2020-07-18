package com.example.giaodien_v1.model;

public class FoodTag {
    private int food_id;
    private int tag_id;

    public FoodTag(int tag_id) {
        this.tag_id = tag_id;
    }

    public FoodTag(int food_id, int tag_id) {
        this.food_id = food_id;
        this.tag_id = tag_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }
}
