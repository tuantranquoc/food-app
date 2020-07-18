package com.example.giaodien_v1.model;

public class Favorite {
    int favorite_id;
    int user_id;
    int food_id;
    String food_name;
    int src_img;

    public Favorite() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Favorite(int favorite_id, int user_id, int food_id, String food_name, int src_img) {
        this.favorite_id = favorite_id;
        this.user_id = user_id;
        this.food_id = food_id;
        this.food_name = food_name;
        this.src_img = src_img;
    }

    public Favorite(int user_id, int food_id, String food_name, int src_img) {
        this.user_id = user_id;
        this.food_id = food_id;
        this.food_name = food_name;
        this.src_img = src_img;
    }

    public int getFavorite_id() {
        return favorite_id;
    }

    public void setFavorite_id(int favorite_id) {
        this.favorite_id = favorite_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }





    public int getSrc_img() {
        return src_img;
    }

    public void setSrc_img(int src_img) {
        this.src_img = src_img;
    }
}
