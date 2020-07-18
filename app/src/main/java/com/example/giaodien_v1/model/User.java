package com.example.giaodien_v1.model;

public class User {
    int userId;
    String email;
    String userName;
    String password;
    long money;

    public User(String email, String userName, String password, long money) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.money = money;
    }
    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }
}
