package com.example.giaodien_v1.model;

public class Restaurant {
    private int restaurant_id;
    private String restaurant_name;
    private double v1;
    private double v2;
    private int src_img;

    public Restaurant() {
    }

    public Restaurant(String restaurant_name, double v1, double v2, int src_img) {
        this.restaurant_name = restaurant_name;
        this.v1 = v1;
        this.v2 = v2;
        this.src_img = src_img;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public double getV1() {
        return v1;
    }

    public void setV1(double v1) {
        this.v1 = v1;
    }

    public double getV2() {
        return v2;
    }

    public void setV2(double v2) {
        this.v2 = v2;
    }

    public int getSrc_img() {
        return src_img;
    }

    public void setSrc_img(int src_img) {
        this.src_img = src_img;
    }
}
