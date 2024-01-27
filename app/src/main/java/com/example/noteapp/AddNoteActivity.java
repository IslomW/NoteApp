package com.example.noteapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityAddNewCardBinding;
import com.example.noteapp.model.Note;


public class AddNoteActivity extends BaseActivity<ActivityAddNewCardBinding> {

    private Note note;

    @Override
    protected ActivityAddNewCardBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityAddNewCardBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        int note_id = getIntent().getIntExtra("note_id", 0);
        note = (Note) getIntent().getSerializableExtra("note");




        if (note != null) {
            binding.buttonCreateNote.setText("Update Note");
            binding.editTextTitle.setText(note.getTitle());
            binding.editTextContent.setText(note.getContent());
            setTitle("Update Note");

        } else {
            setTitle("Create Note");
        }

        binding.buttonCreateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.editTextTitle.getText().toString();
                String content = binding.editTextContent.getText().toString();

                if (note == null) {
                    Note note = new Note(title, content);
                    dataBaseHelper.addNote(note, false);
                } else {
                    note.setTitle(title);
                    note.setContent(content);
                    dataBaseHelper.updateNote(note);
                }
                finish();

            }
        });
    }

    @Override
    protected boolean hasBackButton() {
        return true;
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
