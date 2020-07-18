package com.example.giaodien_v1.fragments;


import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.giaodien_v1.AuhtActivity;
import com.example.giaodien_v1.MainActivity;
import com.example.giaodien_v1.R;
import com.example.giaodien_v1.auth.Session;
import com.example.giaodien_v1.model.Customer;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment {

    public static final String CURRENT_USER = "current_user";
    SharedPreferences sharedpreferences;
    String userName;
    int userId;
    private TextInputLayout textInputUsername;
    private TextInputLayout textInputPassword;
    private Session session;

    private Button btnLogin;
    private Button btnSignUp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container,false);
        /*View backgroundImage = view.findViewById(R.id.login_fragment);
        Drawable background = backgroundImage.getBackground();
        background.setAlpha(200);*/
        session = new Session(getContext());
        btnLogin = (Button)view.findViewById(R.id.login);
        btnSignUp = (Button)view.findViewById(R.id.signUp);
        textInputPassword = view.findViewById(R.id.text_input_password);
        textInputUsername = view.findViewById(R.id.text_input_username);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loginCheck()){
                    setSession();
                    Toast.makeText(getActivity(),"Welcome back " + userName +"!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                }else
                    Toast.makeText(getActivity(),"wrong username or password!",Toast.LENGTH_SHORT).show();

            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Going to sign up",Toast.LENGTH_SHORT).show();
                ((AuhtActivity)getActivity()).setViewPage(1);


            }
        });
        return view;
    }


    public Boolean isUserNameEmpty(){
        String username = textInputUsername.getEditText().getText().toString();
        if(username.isEmpty()){
            textInputUsername.setError("Username can't be empty!");
            return false;
        }
        textInputUsername.setError(null);
        return true;
    }

    public Boolean isPasswordEmpty(){
        String password = textInputPassword.getEditText().getText().toString();
        if(password.isEmpty()){
            textInputPassword.setError("Password can't be empty");
            return false;
        }
        textInputPassword.setError(null);
        return true;
    }
    public Boolean loginCheck(){
        isPasswordEmpty();
        isUserNameEmpty();
        if(textInputPassword.getError()!=null | textInputUsername.getError()!=null){
            return false;
        }
        return MainActivity.databaseHelper.loginByEmail(textInputUsername.getEditText().getText().toString(),textInputPassword.getEditText().getText().toString());
    }


    public void setSession(){
        Customer customer = MainActivity.databaseHelper.login(textInputUsername.getEditText().getText().toString(),textInputPassword.getEditText().getText().toString());
        session.setUsername(customer.getUserName());
        session.setEmail(customer.getEmail());
        session.setUserId(customer.getId());
        session.setRole(MainActivity.databaseHelper.getRoleByUserId(customer.getId()));
        userName = customer.getUserName();
        userId = customer.getId();
    }
}
