package com.example.noteapp.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Note implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("create_at")
    private String createdAt;

    public Note(String title, String content){
        this.title = title;
        this.content = content;
    }

    public Note(){}

    public Note(Integer id, String title, String content, String createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

    public static class Entry{

        public static String TABLE_NAME = "NOTES";
        public static String ID = "ID";
        public static String TITLE = "TITLE";
        public static String CONTENT = "CONTENT";
        public static String CREATED_AT = "CREATED_AT";

        public static String createTableQuery(){
            //CREATE TABLE IF NOT EXISTS NOTE ( ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT NOT NULL,
            //CONTENT TEXT NOT NULL, CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP);
            String sql = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME +" ( "+
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    TITLE + " TEXT NOT NULL, " +
                    CONTENT + " TEXT NOT NULL, " +
                    CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ");";
//            String sql = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
//                    " %s TEXT NOT NULL, %s TEXT NOT NULL, %s TIMESTAMP DEFAULT CURRENT_TIMESTAMP);",
//                    TABLE_NAME, ID, TITLE, CONTENT, CREATED_AT);


            return sql;
        }

    }

}
