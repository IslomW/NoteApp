package com.example.noteapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityAddNoteBinding;
import com.example.noteapp.model.Note;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddNoteActivity extends BaseActivity<ActivityAddNoteBinding> {

    private Note note;

    @Override
    protected ActivityAddNoteBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityAddNoteBinding.inflate(inflater);
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
//                    dataBaseHelper.addNote(note, false);
                    Call<Note> call = mainApi.createNote(note);
                    showLoading();

                    call.enqueue(new Callback<Note>() {
                        @Override
                        public void onResponse(Call<Note> call, Response<Note> response) {
                            hideLoading();
                            if (response.isSuccessful()) {
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Note> call, Throwable t) {
                            hideLoading();
                            finish();
                        }
                    });


                } else {
                    note.setTitle(title);
                    note.setContent(content);
                    Call<Note> call = mainApi.updateNote(note.getId(), note);
                    call.enqueue(new Callback<Note>() {
                        @Override
                        public void onResponse(Call<Note> call, Response<Note> response) {
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Note> call, Throwable t) {
                            finish();
                        }
                    });
//                    dataBaseHelper.updateNote(note);

                }
//                finish();

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
