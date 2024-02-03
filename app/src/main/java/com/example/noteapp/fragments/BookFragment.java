package com.example.noteapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.noteapp.AddBookActivity;
import com.example.noteapp.AddNoteActivity;
import com.example.noteapp.adapters.BookListAdapter;
import com.example.noteapp.base.BaseFragment;
import com.example.noteapp.databinding.FragmentBooksBinding;
import com.example.noteapp.model.Book;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookFragment extends BaseFragment<FragmentBooksBinding> {

    private ArrayList<Book> bookArrayList;
    private BookListAdapter adapter;


    @Override
    protected FragmentBooksBinding inflateViewBinding(LayoutInflater inflater, ViewGroup parent, boolean toAttachRoot) {
        return FragmentBooksBinding.inflate(inflater, parent, toAttachRoot);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookArrayList = new ArrayList<>();
        adapter = new BookListAdapter(bookArrayList);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerViewBooks.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerViewBooks.setAdapter(adapter);

        buttonSetup();
    }

    @Override
    public void onStart() {
        super.onStart();
        loadBooks();
    }

    private void loadBooks() {
        Call<ArrayList<Book>> call = baseActivity.mainApi.getBooks(baseActivity.getBearerToken());
        call.enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                if (response.isSuccessful()){
                    bookArrayList.clear();
                    ArrayList<Book> books = response.body();
                    bookArrayList.addAll(books);
                    adapter.notifyDataSetChanged();
                }else {
                    onFailure(call, new Throwable("Failed"));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {

            }
        });


    }

    private void buttonSetup(){
        binding.buttonAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), AddBookActivity.class);
                startActivity(intent);
            }
        });
    }
}
