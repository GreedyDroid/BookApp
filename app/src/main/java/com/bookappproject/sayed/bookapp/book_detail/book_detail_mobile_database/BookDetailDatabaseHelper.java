package com.bookappproject.sayed.bookapp.book_detail.book_detail_mobile_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookDetailDatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Detail Book Database";
    public static final int DATABASE_VERSION = 1;
    private String tableName;
    public static final String COL_ID = "tbl_id";
    public static final String COL_FIREBASE_ID = "tbl_firbase_id";
    public static final String COL_TITLE = "tbl_title";
    public static final String COL_SERIAL = "tbl_serial";
    public static final String COL_DESCRIPTION = "tbl_description";


    private String createTableBook= "create table "+tableName+"("+COL_ID+
            " integer primary key autoincrement, "+COL_FIREBASE_ID+" text, "
            +COL_TITLE+" text, " +COL_SERIAL+" text, "+COL_DESCRIPTION + " text);";

    public BookDetailDatabaseHelper(Context context, String tableName) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.tableName = tableName;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableBook);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if EXISTS "+tableName);
        onCreate(db);
    }
}
