package com.example.noteapp.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.noteapp.base.BaseRecyclerAdapter;
import com.example.noteapp.base.BaseViewHolder;
import com.example.noteapp.databinding.ItemBookBinding;
import com.example.noteapp.model.Book;

import java.util.ArrayList;

public class BookListAdapter extends BaseRecyclerAdapter {

    private ArrayList<Book> bookArrayList;

    public BookListAdapter(ArrayList<Book> bookArrayList) {
        this.bookArrayList = bookArrayList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookBinding binding = ItemBookBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BookViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Book book = bookArrayList.get(holder.getAdapterPosition());
//
//                Intent intent = new Intent(holder.itemView.getContext(), )
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }


    public class BookViewHolder extends BaseViewHolder<ItemBookBinding>{

        public BookViewHolder(@NonNull ItemBookBinding binding) {
            super(binding);
        }

        @Override
        protected void onBind(int positon) {
            Book book = bookArrayList.get(positon);
            binding.textViewBookTitle.setText(book.getTitle());
            binding.textViewDescription.setText(book.getDescription());

            Glide.with(binding.imageViewBook).load(book.getImageUrl()).into(binding.imageViewBook);
        }

    }
}
