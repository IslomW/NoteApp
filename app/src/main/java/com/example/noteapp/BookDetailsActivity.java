package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivitiyBookDetailsBinding;
import com.example.noteapp.model.Book;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailsActivity extends BaseActivity<ActivitiyBookDetailsBinding>  {

    private Book book;
    @Override
    protected ActivitiyBookDetailsBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivitiyBookDetailsBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        book = (Book) getIntent().getSerializableExtra("book");


        if (book!= null){
            binding.textViewBookTitle.setText(book.getTitle());
            binding.editTextDescription.setText(book.getDescription());

            Glide.with(binding.imageViewBook).load(book.getImageUrl()).into(binding.imageViewBook);
        }


        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Void> call = mainApi.deleteBook(book.getId());
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });

        binding.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookDetailsActivity.this, AddBookActivity.class);
                intent.putExtra("book", book);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }
}
