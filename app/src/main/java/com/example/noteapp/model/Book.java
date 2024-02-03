package com.example.noteapp.model;

import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("page_count")
    private int pageCount;
    @SerializedName("image")
    private String imageUrl;


    public Book() {
    }

    public Book(String title, String description, int pageCount, String imageUrl) {
        this.title = title;
        this.description = description;
        this.pageCount = pageCount;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
