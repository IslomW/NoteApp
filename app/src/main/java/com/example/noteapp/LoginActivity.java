package com.example.noteapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    @Override
    protected ActivityLoginBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityLoginBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buttonClickSetup();

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.textInputEmail.getText().toString();
                String password = binding.textInputPassword.getText().toString();
//                SharedPreferences sharedPreferences = getSharedPreferences("NoteAppSharedPef", MODE_PRIVATE);

                String emailFromSh = (String) preferenceManger.getValue( String.class, "email", "");
                String passwordFromSh = (String) preferenceManger.getValue(String.class, "password", "");

                if (email.equals(emailFromSh) && password.equals(passwordFromSh)){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    binding.textInputEmail.setError("Email not founded!");
                    binding.textInputPassword.setError("Password not founded!");
                }

            }
        });


    }


    private void buttonClickSetup() {


        binding.textViewMoveRegisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}
