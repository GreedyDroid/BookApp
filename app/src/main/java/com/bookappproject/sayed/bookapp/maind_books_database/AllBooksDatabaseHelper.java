package com.bookappproject.sayed.bookapp.maind_books_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AllBooksDatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "All Books Database";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "tbl_all_books";
    public static final String COL_ID = "tbl_id";
    public static final String COL_TITLE = "tbl_title";
    public static final String COL_FIREBASE_ID = "tbl_firebase_id";
    public static final String COL_IMG = "two";

    public static final String CREATE_TABLE_BOOKS= "create table "+TABLE_NAME+"("+COL_ID+
            " integer primary key autoincrement, "+COL_TITLE+" text, " +COL_IMG+" text, "+COL_FIREBASE_ID+ " text);";


    public AllBooksDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BOOKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
