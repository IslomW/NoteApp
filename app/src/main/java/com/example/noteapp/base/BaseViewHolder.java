package com.example.noteapp.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.example.noteapp.adapters.NoteListAdapter;

public abstract class BaseViewHolder<T extends ViewBinding> extends RecyclerView.ViewHolder {

    protected T binding;

    public BaseViewHolder(@NonNull T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    protected abstract void onBind(int positon);
}
