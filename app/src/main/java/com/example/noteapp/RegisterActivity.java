package com.example.noteapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityRegisterBinding;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> {

    @Override
    protected ActivityRegisterBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityRegisterBinding.inflate(getLayoutInflater());
    }

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

//                    SharedPreferences sharedPreferences = getSharedPreferences("NoteAppSharedPef", MODE_PRIVATE);
//
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("firstName", firstName);
//                    editor.putString("lastName", lastName);
//                    editor.putString("email", email);
//                    editor.putString("password", password);
//                    editor.putBoolean("isLoggedIn", true);
//                    editor.apply();
//
//                    if (editor.commit()){
//                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//                        startActivity(intent);
//                    }
                    preferenceManger.setValue("firstName", firstName);
                    preferenceManger.setValue("lastName", lastName);
                    preferenceManger.setValue("email", email);
                    preferenceManger.setValue("password", password);
                    preferenceManger.setValue("isLoggedIn", true);

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);

                } else {

                }
            }
        });



    }




}
