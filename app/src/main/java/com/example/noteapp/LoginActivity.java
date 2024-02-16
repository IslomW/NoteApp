package com.example.noteapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityLoginBinding;
import com.example.noteapp.model.User;
import com.google.gson.Gson;

import java.io.FileOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

//                String emailFromSh = (String) preferenceManger.getValue( String.class, "email", "");
//                String passwordFromSh = (String) preferenceManger.getValue(String.class, "password", "");
//
//                if (email.equals(emailFromSh) && password.equals(passwordFromSh)){
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
//                }else {
//                    binding.textInputEmail.setError("Email not founded!");
//                    binding.textInputPassword.setError("Password not founded!");
//                }

                User user = new User(email, password);
                 Log.d("LoginActivity", new Gson().toJson(user));

                Call<User> call = mainApi.login(user);

                showLoading();
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        hideLoading();
                        if(response.isSuccessful()){
                            User user = response.body();
                            if(user!=null){
                                preferenceManger.setValue("isLoggedIn", true);
                                preferenceManger.setValue("access_token", user.getAccessToken());
                                preferenceManger.setValue("email", email);
                                preferenceManger.setValue("password", password);

                                saveToFile(user.getAccessToken());
                                preferenceManger.setValue("user", user);


                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        hideLoading();
                        Log.d("onError", t.getMessage());
                    }
                });

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


    private void saveToFile(String access_token) {
        String filename = "my_access_token.txt";

        FileOutputStream out;
        try {
            out = openFileOutput(filename, Context.MODE_PRIVATE);
            out.write(access_token.getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
