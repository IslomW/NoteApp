package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityRegisterBinding;
import com.example.noteapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> {

    @Override
    protected ActivityRegisterBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityRegisterBinding.inflate(getLayoutInflater());
    }

    private String deviceToken;

    @Override
    protected boolean hasBackButton() {
        return true;
    }

    @Override
    protected int backButtonDrawable() {
        return R.drawable.ic_back;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Registration");
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()) {
                    deviceToken = task.getResult();
                    Log.d("Token", deviceToken);
                }
            }
        });

        buttonClickSetup();
    }

    private void buttonClickSetup() {


        binding.buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = binding.textInputFirstName.getText().toString();
                String lastName = binding.textInputLastName.getText().toString();
                String email = binding.textInputEmail.getText().toString();
                String password = binding.textInputPassword.getText().toString();
                String confirmPassword = binding.textInputConfirmPassword.getText().toString();

                if (firstName.isEmpty()){
                    return;
                }
                if (lastName.isEmpty()){
                    return;
                }
                if (email.isEmpty()){
                    return;
                }
                if (password.isEmpty()){
                    return;
                }
                if (password.equals(confirmPassword)){

                    User user = new User();
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setDeviceToken(deviceToken);


                    Call<User> call = mainApi.createUser(user);

                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.isSuccessful()){

                                preferenceManger.setValue("isLoggedIn", true);
                                preferenceManger.setValue("user", response.body());
                                preferenceManger.setValue("accessToken", response.body().getAccessToken());
                                preferenceManger.setValue("device_token", deviceToken);
                                preferenceManger.setValue("password", password);
                                preferenceManger.setValue("email", user.getEmail());


                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });



                } else {

                }
            }
        });



    }




}
