package com.bookappproject.sayed.bookapp.maind_books_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class AllBooksDatabaseSource {
    private AllBooksDatabaseHelper allBooksDatabaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public AllBooksDatabaseSource(Context context){
        allBooksDatabaseHelper = new AllBooksDatabaseHelper(context);
    }

    public void open(){
        sqLiteDatabase = allBooksDatabaseHelper.getWritableDatabase();
    }
    public void close(){
        sqLiteDatabase.close();
    }

    public boolean addBook(MainBookObject bookObject){
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AllBooksDatabaseHelper.COL_TITLE, bookObject.getBookTitle());
        contentValues.put(AllBooksDatabaseHelper.COL_IMG, bookObject.getImage());
        contentValues.put(AllBooksDatabaseHelper.COL_FIREBASE_ID, bookObject.getFirebaseID());
        long row_id = sqLiteDatabase.insert(AllBooksDatabaseHelper.TABLE_NAME, null, contentValues);
        this.close();


        if (row_id>0){
            return true;
        }else {
            return false;
        }
    }

    //Get ALl Books
    public ArrayList<MainBookObject> getAllMovies() {
        ArrayList<MainBookObject>bookObjects = new ArrayList<>();
        this.open();

        Cursor cursor = sqLiteDatabase.query(AllBooksDatabaseHelper.TABLE_NAME,
                null, null, null,null,null,null);

        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex(AllBooksDatabaseHelper.COL_ID));
                String bookTitle = cursor.getString(cursor.getColumnIndex(AllBooksDatabaseHelper.COL_TITLE));
                String bookImg = cursor.getString(cursor.getColumnIndex(AllBooksDatabaseHelper.COL_IMG));
                String bookFirebaseId = cursor.getString(cursor.getColumnIndex(AllBooksDatabaseHelper.COL_FIREBASE_ID));
                MainBookObject bookObject = new MainBookObject(id, bookTitle,bookImg, bookFirebaseId);
                bookObjects.add(bookObject);
            }while (cursor.moveToNext());
        }
        cursor.close();
        this.close();

        return bookObjects;
    }

    public boolean deleteBook(int rowId){
        this.open();
        int deleted = sqLiteDatabase.delete(AllBooksDatabaseHelper.TABLE_NAME,
                AllBooksDatabaseHelper.COL_ID+" =?",new String[]{Integer.toString(rowId)});
        this.close();
        if (deleted>0){
            return true;
        }else {
            return false;
        }
    }

    public boolean updateMovie(MainBookObject bookObject, int rowID) {
        this.open();
        ContentValues values = new ContentValues();
        values.put(AllBooksDatabaseHelper.COL_ID, bookObject.getBookId());
        values.put(AllBooksDatabaseHelper.COL_TITLE, bookObject.getBookTitle());
        values.put(AllBooksDatabaseHelper.COL_IMG, bookObject.getImage());
        values.put(AllBooksDatabaseHelper.COL_FIREBASE_ID, bookObject.getFirebaseID());

        int updated = sqLiteDatabase.update(AllBooksDatabaseHelper.TABLE_NAME, values,
                AllBooksDatabaseHelper.COL_ID+" = ?",new String[]{Integer.toString(rowID)});
        if (updated>0){
            return true;
        }else {
            return false;
        }
    }
}
