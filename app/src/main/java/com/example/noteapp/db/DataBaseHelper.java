package com.example.noteapp.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.noteapp.model.Note;

import java.util.ArrayList;
import java.util.Currency;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static String DB_Name = "NOTE_APP";
    private static Integer DB_VERSION = 1;

    private SQLiteDatabase db;


    public DataBaseHelper(Context context) {
        super(context, DB_Name, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Note.Entry.createTableQuery());
    }

    public void addNote(Note note, boolean multiple) {
        //INSERT INTO NOTES (title, content) VALUES ("title 1", "content 1");
        String insert_sql = "INSERT INTO "+Note.Entry.TABLE_NAME+" ("+Note.Entry.TITLE+", "+Note.Entry.CONTENT+
                ") VALUES ('"+ note.getTitle()+"', '"+note.getContent()+"' );";
//        String insert_sql = String.format("INSERT INTO %s (%s, %s) VALUES ('%s', '%s');",
//                Note.Entry.TABLE_NAME, Note.Entry.TITLE, Note.Entry.CONTENT, note.getTitle(), note.getContent());


        Log.d("SQLite", insert_sql);

        if (db == null)
            db = getWritableDatabase();

        db.execSQL(insert_sql);

        if (!multiple)
            db.close();
    }

    public void addNotes(ArrayList<Note> notes){
        db = getWritableDatabase();

        for (Note note : notes){
            addNote(note, true);
        }
        db.close();
    }


    @SuppressLint("Range")
    public ArrayList<Note> getNotes(){
        ArrayList<Note> notes = new ArrayList<>();
         //Dbdan data olish
        db = getReadableDatabase();
        // SELECT * FROM NOTE ORDER BY CREATED_AT DESC;   sql zapros yaratish
        String selectQuery = "SELECT * FROM "+ Note.Entry.TABLE_NAME +" ORDER BY CREATED_AT DESC;";
//        String selectQuery = String.format("SELECT * FROM %s ORDER BY CREATED_AT DESC;", Note.Entry.TABLE_NAME);

        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()){
            Note note = new Note();

            note.setId(cursor.getInt(cursor.getColumnIndex(Note.Entry.ID)));
            note.setTitle(cursor.getString(cursor.getColumnIndex(Note.Entry.TITLE)));
            note.setContent(cursor.getString(cursor.getColumnIndex(Note.Entry.CONTENT)));
            note.setCreatedAt(cursor.getString(cursor.getColumnIndex(Note.Entry.CREATED_AT)));
            notes.add(note);

        }
        cursor.close();
        db.close();

        return notes;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
