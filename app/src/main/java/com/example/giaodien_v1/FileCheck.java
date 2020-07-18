package com.example.giaodien_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.giaodien_v1.data.File;
import com.example.giaodien_v1.model.User;

import java.util.ArrayList;

public class FileCheck extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_check);
        textView = findViewById(R.id.user_name);
        String Username = null;
        ArrayList<User> users = File.getUserDataFromFile(this);
        for (User user : users){
            Username += user.getUserName() + " ";
        }
        textView.setText(Username);
    }
}
