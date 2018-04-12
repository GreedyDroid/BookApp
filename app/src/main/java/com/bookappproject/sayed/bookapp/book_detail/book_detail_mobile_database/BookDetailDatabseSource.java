package com.bookappproject.sayed.bookapp.book_detail.book_detail_mobile_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bookappproject.sayed.bookapp.book_detail.BookDetail;
import com.bookappproject.sayed.bookapp.book_detail.Chapter;
import com.bookappproject.sayed.bookapp.main_books_database.AllBooksDatabaseHelper;

import java.util.ArrayList;

public class BookDetailDatabseSource {
    private  String tableName;
    private BookDetailDatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public BookDetailDatabseSource(Context context, String tableName) {
        databaseHelper = new BookDetailDatabaseHelper(context, tableName);
        this.tableName = tableName;
    }

    public void open(){
        database = databaseHelper.getWritableDatabase();
    }
    public void close(){
        database.close();
    }

    public boolean addBook(ArrayList<Chapter>chapters){
        this.open();
        ContentValues contentValues;
        boolean status = true;
        for (Chapter chapter:chapters) {
            contentValues = new ContentValues();
            contentValues.put(BookDetailDatabaseHelper.COL_FIREBASE_ID, chapter.getFirebaseID());
            contentValues.put(BookDetailDatabaseHelper.COL_SERIAL, chapter.getChapterSerial());
            contentValues.put(BookDetailDatabaseHelper.COL_TITLE, chapter.getChapterTitle());
            contentValues.put(BookDetailDatabaseHelper.COL_DESCRIPTION, chapter.getChapterDescription());

            long row_id = database.insert(tableName, null, contentValues);
            if (!(row_id>0)){
                status = false;
            }
        }
        return status;
    }
}
