package com.example.noteapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityAddNewCardBinding;
import com.example.noteapp.model.Note;


public class AddNoteActivity extends BaseActivity<ActivityAddNewCardBinding> {
    @Override
    protected ActivityAddNewCardBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityAddNewCardBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.buttonCreateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.editTextTitle.getText().toString();
                String context = binding.editTextTitle.getText().toString();
                Note note = new Note(title, context);


                dataBaseHelper.addNote(note, false);

                finish();
            }
        });
    }

//    private void buttonClickSetup() {
//
//        binding.buttonCreateNote.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String title = binding.editTextTitle.getText().toString();
//                String context = binding.editTextTitle.getText().toString();
//                Note note = new Note(title, context);
//
//                dataBaseHelper.addNote(note, false);
//            }
//        });
//
//
//    }
}
