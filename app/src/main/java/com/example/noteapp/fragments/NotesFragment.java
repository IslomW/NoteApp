package com.example.noteapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.noteapp.AddNoteActivity;
import com.example.noteapp.base.LoadingBarDialog;
import com.example.noteapp.adapters.NoteListAdapter;
import com.example.noteapp.base.BaseFragment;
import com.example.noteapp.databinding.FragmentNotesBinding;
import com.example.noteapp.model.Note;
import com.example.noteapp.model.User;
import com.example.noteapp.remote.MainApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NotesFragment extends BaseFragment<FragmentNotesBinding> {

    private NoteListAdapter noteListAdapter;
    private ArrayList<Note> noteArrayList = new ArrayList<>();

    @Override
    protected FragmentNotesBinding inflateViewBinding(LayoutInflater inflater, ViewGroup parent, boolean toAttachRoot) {
        return FragmentNotesBinding.inflate(inflater, parent, toAttachRoot);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        noteArrayList = baseActivity.dataBaseHelper.getNotes();
        noteListAdapter = new NoteListAdapter(noteArrayList);




        {//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://api.note-app.beknumonov.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        mainApi = retrofit.create(MainApi.class);

//        String bearerToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzA3MDE3NDkyLCJpYXQiOjE3MDY0MTc0OTIsImp0aSI6IjliYmQzYjIzYmVlZDRkYTVhMmJmZjM2NTJlYTM3YzM5IiwidXNlcl9pZCI6NSwiZmlyc3RfbmFtZSI6IlVzZXIgMSIsImxhc3RfbmFtZSI6IlVzZXIgMiJ9.X_8cTZbaHIMvoEQ6GqEiAZtm4cuqL4I_iE4lhZ23EY0";
//
//        Call<ArrayList<Note>> call = mainApi.getNote(bearerToken);
////        call.execute();
//
//        dialog.show();
//        call.enqueue(new Callback<ArrayList<Note>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Note>> call, Response<ArrayList<Note>> response) {
//               dialog.hide();
//                if (response.isSuccessful()){
//                    noteArrayList.clear();
//                    ArrayList<Note> notes = response.body();
//                    noteArrayList.addAll(notes)
//                    ;
//                    noteListAdapter.notifyDataSetChanged();
//                }else {
//                    onFailure(call, new Throwable("Failed"));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Note>> call, Throwable t) {
//
//            }
//        });
        }


    }

    private void retrofitCreateAndGetData(){

        User user = (User) baseActivity.preferenceManger.getValue(User.class, "user", null);
        if (user != null){
            Log.d("User", user.toString());
        }

        Call<ArrayList<Note>> call = baseActivity.mainApi.getNote(baseActivity.getBearerToken());
//        call.execute();

        baseActivity.showLoading();
        call.enqueue(new Callback<ArrayList<Note>>() {
            @Override
            public void onResponse(Call<ArrayList<Note>> call, Response<ArrayList<Note>> response) {
                baseActivity.hideLoading();
                if (response.isSuccessful()){
                    noteArrayList.clear();
                    ArrayList<Note> notes = response.body();
                    noteArrayList.addAll(notes);
                    noteListAdapter.notifyDataSetChanged();
                }else {
                    onFailure(call, new Throwable("Failed"));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Note>> call, Throwable t) {

            }
        });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerviewNotes.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.recyclerviewNotes.setAdapter(noteListAdapter);

        buttonSetup();
    }


    @Override
    public void onStart() {
        super.onStart();
        retrofitCreateAndGetData();
//        noteArrayList.clear();
//        noteArrayList.addAll(baseActivity.dataBaseHelper.getNotes());
    }

    @Override
    public void onResume() {
        super.onResume();
        noteListAdapter.notifyDataSetChanged();
    }


    private void buttonSetup(){
        binding.buttonAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), AddNoteActivity.class);
                startActivity(intent);
            }
        });
    }


}
