package com.ahmed.aziz.notetaker;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper{
    String DatabaseCreateQuery = "CREATE TABLE Notes ( id INTEGER PRIMARY KEY AUTOINCREMENT, category VARCHAR(50), title VARCHAR(200), details VARCHAR(500) )";
    String SQL_DELETE_ENTRIES = "DROP TABLE Notes IF EXIST";
    public Database(Context context) {
        super(context, "Notes.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseCreateQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }
}
