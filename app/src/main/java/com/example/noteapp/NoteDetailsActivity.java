package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityNoteDetalisBinding;
import com.example.noteapp.model.Note;

public class NoteDetailsActivity extends BaseActivity<ActivityNoteDetalisBinding> {

    private Note note;
    @Override
    protected ActivityNoteDetalisBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityNoteDetalisBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Note Details");
        note = (Note) getIntent().getSerializableExtra("note");

        if (note != null){
            binding.editTextTitle.setText(note.getTitle());
            binding.editTextContent.setText(note.getContent());
        }


        binding.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteDetailsActivity.this, AddNoteActivity.class);
                intent.putExtra("note", note);
                startActivity(intent);
                finish();

            }
        });


        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseHelper.deleteNote(note);
                finish();
            }
        });

    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }
}
