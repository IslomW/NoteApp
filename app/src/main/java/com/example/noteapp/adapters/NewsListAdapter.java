package com.example.noteapp.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.noteapp.NewsDetailsActivity;
import com.example.noteapp.base.BaseRecyclerAdapter;
import com.example.noteapp.base.BaseViewHolder;
import com.example.noteapp.base.ListLoadingListener;
import com.example.noteapp.databinding.ItemLoadingBinding;
import com.example.noteapp.databinding.ItemNewsBinding;
import com.example.noteapp.model.News;

import java.util.ArrayList;

public class NewsListAdapter extends BaseRecyclerAdapter {

    private ArrayList<News> newsArrayList;

    public void setListLoadingListener(ListLoadingListener listLoadingListener) {
        this.listLoadingListener = listLoadingListener;
    }

    private ListLoadingListener listLoadingListener;


    private static int ITEM_NEWS_VIEW_TYPE = 0;
    private static int ITEM_LOADING_VIEW_TYPE = 1;

    public NewsListAdapter(ArrayList<News> newsArrayList) {
        this.newsArrayList = newsArrayList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == ITEM_NEWS_VIEW_TYPE) {
            ItemNewsBinding binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new NewsViewHolder(binding);
        } else {
            ItemLoadingBinding binding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new LoadingViwHolder(binding);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof LoadingViwHolder){
            if (listLoadingListener != null){
                listLoadingListener.onLoadingCreated();
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                News news = newsArrayList.get(holder.getAdapterPosition());

                Intent intent = new Intent(holder.itemView.getContext(), NewsDetailsActivity.class);
                intent.putExtra("news", newsArrayList.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        //check if item in position is News or Loading
        if (position < newsArrayList.size())
            return ITEM_NEWS_VIEW_TYPE;
        else return ITEM_LOADING_VIEW_TYPE;

    }

    @Override
    public int getItemCount() {
        if (newsArrayList.size() > 0)
            return newsArrayList.size() + 1;
        else return 0;
    }

    class NewsViewHolder extends BaseViewHolder<ItemNewsBinding> {
        public NewsViewHolder(@NonNull ItemNewsBinding binding) {
            super(binding);
        }

        @Override
        protected void onBind(int position) {
            News news = newsArrayList.get(position);
            binding.newsTitle.setText(news.getTitle());
            binding.newsSource.setText(news.getSource());
            binding.newsContent.setText(news.getContent());

            Glide.with(binding.imageViewNews).load(news.getImage_url()).into(binding.imageViewNews);
        }
    }

    class LoadingViwHolder extends BaseViewHolder<ItemLoadingBinding> {

        public LoadingViwHolder(@NonNull ItemLoadingBinding binding) {
            super(binding);
        }

        @Override
        protected void onBind(int position) {

        }
    }

}
