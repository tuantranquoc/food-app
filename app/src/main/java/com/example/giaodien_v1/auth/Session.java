package com.example.giaodien_v1.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context context) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setUserId(int userid){
        prefs.edit().putInt("userid",userid).apply();
    }
    public void setUsername(String usename) {
        prefs.edit().putString("usename", usename).apply();
    }
    public void setEmail(String email) {
        prefs.edit().putString("email", email).apply();
    }
    public void setRole(int role) {
        prefs.edit().putInt("role", role).apply();
    }
    public String getUsername() {
        String usename = prefs.getString("usename","");
        return usename;
    }

    public String getEmail() {
        String email = prefs.getString("email","");
        return email;
    }
    public int getUserId(){
        int userId = prefs.getInt("userid",-1);
        return userId;
    }
    public int getRole(){
        return prefs.getInt("role",-1);
    }

    public void clear(){
        prefs.edit().clear().apply();
    }
}
