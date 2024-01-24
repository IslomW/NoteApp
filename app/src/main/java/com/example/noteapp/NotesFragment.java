package com.example.noteapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.noteapp.base.BaseFragment;
import com.example.noteapp.databinding.FragmentNotesBinding;


public class NotesFragment extends BaseFragment<FragmentNotesBinding> {

    @Override
    protected FragmentNotesBinding inflateViewBinding(LayoutInflater inflater, ViewGroup container, boolean attachToParent) {
        return FragmentNotesBinding.inflate(inflater, container, false);
    }
}
