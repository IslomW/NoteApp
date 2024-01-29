package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityNoteDetalisBinding;
import com.example.noteapp.model.Note;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

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
        Log.d("Note", note.toString());

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
//                Call<Note> callUpdate = mainApi.updateNote(getBearerToken(),note.getId(), note);
//                showLoading();
//                callUpdate.enqueue(new Callback<Note>() {
//                    @Override
//                    public void onResponse(Call<Note> call, Response<Note> response) {
//                        hideLoading();
//                        if(response.isSuccessful())
//                            finish();
//                    }
//
//                    @Override
//                    public void onFailure(Call<Note> call, Throwable t) {
//                        hideLoading();
//                        finish();
//                    }
//                });
                finish();

            }
        });


        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                dataBaseHelper.deleteNote(note);
//                finish();
                Call<Void> call = mainApi.deleteNote(getBearerToken(), note.getId());
                showLoading();
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        hideLoading();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        hideLoading();
                        finish();
                    }
                });

            }
        });

    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }
}
