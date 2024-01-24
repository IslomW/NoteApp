package com.example.noteapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.base.BaseFragment;
import com.example.noteapp.databinding.ActivityMainBinding;
import com.example.noteapp.model.Note;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private ArrayList<Note> noteArrayList;


    @Override
    protected ActivityMainBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    //    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        buttonSetup();
//
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        noteArrayList = dataBaseHelper.getNotes();
//
//        for (Note note: noteArrayList){
//            Log.d("Note:", note.toString());
//        }
//    }


    private void buttonSetup() {


//        binding.buttonLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                SharedPreferences sharedPreferences = getSharedPreferences("NoteAppSharedPref" ,MODE_PRIVATE);
////
////                SharedPreferences.Editor editor = sharedPreferences.edit();
////                editor.putBoolean("isLoggedIn", false);
////                editor.apply();
//
//                preferenceManger.setValue("isLoggedIn", false);
//
//                Intent intent = new Intent(MainActivity.this, SplashActivity.class);
//                startActivity(intent);
//
//
//            }
//        });
//
//        binding.buttonCreateN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
//                startActivity(intent);
//            }
//        });

    }



}