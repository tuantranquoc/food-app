package com.example.giaodien_v1.model;

public class Comment {
    int comment_id;
    int user_id;
    int food_id;
    String comment;


    public Comment() {
    }

    public Comment(int user_id, int food_id, String comment) {
        this.user_id = user_id;
        this.food_id = food_id;
        this.comment = comment;
    }

    public Comment(int comment_id, int user_id, int food_id, String comment) {
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.food_id = food_id;
        this.comment = comment;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
