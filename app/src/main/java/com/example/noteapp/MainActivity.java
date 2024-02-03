package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityMainBinding;
import com.example.noteapp.fragments.BookFragment;
import com.example.noteapp.fragments.NewsFragment;
import com.example.noteapp.fragments.NotesFragment;
import com.example.noteapp.fragments.ProfileFragment;
import com.example.noteapp.model.Note;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.Firebase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private ArrayList<Note> noteArrayList;
    private NotesFragment notesFragment;
    private NewsFragment newsFragment;
    private BookFragment bookFragment;
    private ProfileFragment profileFragment;


    @Override
    protected ActivityMainBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(R.id.nav_new);
//        generateNotes();
        getFCMToken();

        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                replaceFragment(item.getItemId());
                return true;
            }
        });


    }

    private void getFCMToken() {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                String token = task.getResult();
                Log.i("My token", token);
            }
        });
    }

    private void replaceFragment(int tabId) {

        if (tabId == R.id.nav_notes) {

            if (notesFragment == null)
                notesFragment = new NotesFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, notesFragment).commit();
            setTitle("Notes");
        } else if (tabId == R.id.nav_profile) {

            if (profileFragment == null)
                profileFragment = new ProfileFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, profileFragment).commit();
            setTitle("Profile");

        }else if(tabId == R.id.nav_new){
            if (newsFragment == null)
                newsFragment = new NewsFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newsFragment).commit();
            setTitle("News");
        }else if (tabId == R.id.nav_books){
            if (bookFragment == null)
                bookFragment = new BookFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, bookFragment).commit();
            setTitle("Books");
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        noteArrayList = dataBaseHelper.getNotes();

        for (Note note : noteArrayList) {
            Log.d("Note", note.toString());
        }

    }

    void generateNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Note note = new Note();

            note.setTitle("Title " + (i + 1));
            note.setContent("Content " + (i + 1));
            notes.add(note);
        }
        dataBaseHelper.addNotes(notes);
    }

}
//        noteArrayList = dataBaseHelper.getNotes();
////
////        for (Note note: noteArrayList){
//////            Log.d("Note:", note.toString());
////        }


//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        buttonSetup();
//
//    }


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
