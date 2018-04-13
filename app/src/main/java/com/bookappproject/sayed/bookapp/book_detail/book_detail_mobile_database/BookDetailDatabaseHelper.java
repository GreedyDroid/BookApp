package com.bookappproject.sayed.bookapp.book_detail.book_detail_mobile_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookDetailDatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Detail Book Database";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "tbl_books";
    public static final String COL_ID = "tbl_id";
    public static final String COL_FIREBASE_BOOK_ID = "col_firebase_book_id";
    public static final String COL_BOOK_NAME= "book_name";
    public static final String COL_CHAPTER_FIREBASE_ID = "tbl_chapter_firbase_id";
    public static final String COL_CHAPTER_SERIAL = "tbl_chapter_serial";
    public static final String COL_CHAPTER_TITLE = "tbl_chapter_title";
    public static final String COL_CHAPTER_DESCRIPTION = "tbl_chapter_description";


/*    private String createTableBook= "create table "+tableName+"("+COL_ID+
            " integer primary key autoincrement, "+COL_FIREBASE_ID+" text, "
            +COL_TITLE+" text, " +COL_SERIAL+" text, "+COL_DESCRIPTION + " text);";*/

    private String createTableQuery(){
        return "create table "+TABLE_NAME+"("+COL_ID+
                " integer primary key autoincrement, "+COL_FIREBASE_BOOK_ID+" text, "
                +COL_BOOK_NAME+" text, " +COL_CHAPTER_FIREBASE_ID+" text, "
                +COL_CHAPTER_SERIAL+" text, " +COL_CHAPTER_TITLE+" text, "+COL_CHAPTER_DESCRIPTION + " text);";
    }
    public BookDetailDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
