package com.example.noteapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.noteapp.SplashActivity;
import com.example.noteapp.base.BaseFragment;
import com.example.noteapp.databinding.FragmentProfileBinding;
import com.example.noteapp.model.Book;
import com.example.noteapp.model.User;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {



    @Override
    protected FragmentProfileBinding inflateViewBinding(LayoutInflater inflater, ViewGroup container, boolean attachToParent) {
        return FragmentProfileBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseActivity.preferenceManger.clear();
                Intent intent = new Intent(requireContext(), SplashActivity.class);
                startActivity(intent);
            }
        });
    }
}
