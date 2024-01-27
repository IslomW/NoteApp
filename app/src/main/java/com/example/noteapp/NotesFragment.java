package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.noteapp.adapters.NoteListAdapter;
import com.example.noteapp.base.BaseFragment;
import com.example.noteapp.databinding.FragmentNotesBinding;
import com.example.noteapp.model.Note;

import java.util.ArrayList;


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
        noteArrayList = baseActivity.dataBaseHelper.getNotes();
        noteListAdapter = new NoteListAdapter(noteArrayList);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerviewNotes.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.recyclerviewNotes.setAdapter(noteListAdapter);


        binding.buttonAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), AddNoteActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        noteArrayList.clear();
        noteArrayList.addAll(baseActivity.dataBaseHelper.getNotes());
    }

    @Override
    public void onResume() {
        super.onResume();

        noteListAdapter.notifyDataSetChanged();
    }


}
