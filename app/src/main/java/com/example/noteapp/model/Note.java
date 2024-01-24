package com.example.noteapp.model;

public class Note {

    private Integer id;
    private String title;
    private String content;
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
            String sql = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME +" ( "+
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    TITLE + " TEXT NOT NULL, " +
                    CONTENT + " TEXT NOT NULL, " +
                    CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ");";
            return sql;
        }

    }

}
