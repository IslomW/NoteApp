package com.example.noteapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.example.noteapp.base.BaseRecyclerAdapter;
import com.example.noteapp.base.BaseViewHolder;
import com.example.noteapp.databinding.ItemNoteBinding;
import com.example.noteapp.model.Note;

import java.util.ArrayList;

public class NoteListAdapter extends BaseRecyclerAdapter {

    private ArrayList<Note> noteArrayList;

    public NoteListAdapter(ArrayList<Note> noteArrayList) {
        this.noteArrayList = noteArrayList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNoteBinding binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }

    public class NoteViewHolder extends BaseViewHolder<ItemNoteBinding>{

        private ItemNoteBinding noteBinding;

        public NoteViewHolder(@NonNull ItemNoteBinding binding) {
            super(binding);
        }


        @Override
        protected void onBind(int position) {
            Note note = noteArrayList.get(position);
            binding.textViewNoteTitle.setText(note.getTitle());
            binding.textViewNoteContent.setText(note.getContent());
        }
    }
}
