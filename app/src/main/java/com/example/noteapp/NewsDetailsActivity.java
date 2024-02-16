package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityNewsDetailsBinding;
import com.example.noteapp.model.News;


public class NewsDetailsActivity extends BaseActivity<ActivityNewsDetailsBinding> {

    private News news;
    @Override
    protected ActivityNewsDetailsBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityNewsDetailsBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("News detail");
        news = (News) getIntent().getSerializableExtra("news");


        if (news != null){
            binding.textViewNewsTitle.setText(news.getTitle());
            binding.textViewNewsDescription.setText(news.getContent());
            Glide.with(this)
                    .load(news.getImage_url())
                    .placeholder(R.drawable.ic_no_image)
                    .into(binding.imageViewNews);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_details_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.share){
            Intent intent = new Intent(this, UserListActivity.class);
            intent.putExtra("news_id", news.getId());
            startActivity(intent);
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }

    }


    @Override
    protected boolean hasBackButton() {
        return true;
    }
}
