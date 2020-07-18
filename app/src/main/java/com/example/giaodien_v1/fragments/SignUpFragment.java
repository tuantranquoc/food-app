package com.example.giaodien_v1.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.google.android.material.textfield.TextInputLayout;

public class SignUpFragment extends Fragment {
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputUsername;
    private TextInputLayout textInputPassword;
    private TextInputLayout textInputPasswordConfirm;
    private Button btnConfirm;

    private static final int fund = 1000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container,false);
        View backgroundImage = view.findViewById(R.id.fragment_sign_up);
        Drawable background = backgroundImage.getBackground();
        background.setAlpha(200);

        textInputEmail = view.findViewById(R.id.text_input_email);
        textInputPassword = view.findViewById(R.id.text_input_password);
        textInputUsername = view.findViewById(R.id.text_input_username);
        textInputPasswordConfirm = view.findViewById(R.id.text_input_password_confirm);
        btnConfirm = view.findViewById(R.id.confirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(confirm()){
                    String email = textInputEmail.getEditText().getText().toString();
                    String userName = textInputUsername.getEditText().getText().toString();
                    String password = textInputPassword.getEditText().getText().toString();

                    Boolean result = MainActivity.databaseHelper.insert(email,userName,password,fund);
                    if(!result){
                        Toast.makeText(getActivity(),"SIGN UP FAILURE",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(),"SIGN UP SUCCESS",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), AuhtActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
        return view;
    }
    private Boolean isEmailValid(){
        String email = textInputEmail.getEditText().getText().toString();
        if(email.isEmpty()){
            textInputEmail.setError("Email can't be empty!");
        }else{
            textInputEmail.setError(null);
        }
        return email.isEmpty();
    }
    private Boolean isUserValid(){
        String username = textInputUsername.getEditText().getText().toString();
        if(username.isEmpty()){
            textInputUsername.setError("Username can't be empty!");
        }else if(username.length()>15){
            textInputUsername.setError("Username too long!");
        }else{
            textInputUsername.setError(null);
            return true;
        }
        return false;
    }

    private Boolean isValidPassword(){
        String password = textInputPassword.getEditText().getText().toString();
        if(password.isEmpty()){
            textInputPassword.setError("Password can't be null!");
        }else if(password.length()<5){
            textInputPassword.setError("Password must be more than 5 characters!");
        } else {
            textInputPassword.setError(null);
            return true;
        }
        return false;
    }

    private Boolean samePassword(){
        String password = textInputPassword.getEditText().getText().toString();
        String passwordConfirm = textInputPasswordConfirm.getEditText().getText().toString();
        if(!password.equals(passwordConfirm)){
            textInputPasswordConfirm.setError("Wrong password confirm");
            textInputPassword.getEditText().setText("");
            textInputPasswordConfirm.getEditText().setText("");
            return false;
        }
        return true;
    }

    public Boolean confirm() {
        isEmailValid();
        isUserValid();
        isValidPassword();
        samePassword();
        if(textInputPassword.getError()!=null | textInputUsername.getError()!=null | textInputEmail.getError()!=null){
            return false;
        }
        return true;
    }

}
