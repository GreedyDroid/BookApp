package com.bookappproject.sayed.bookapp.book_detail.book_detail_mobile_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bookappproject.sayed.bookapp.book_detail.BookDetail;
import com.bookappproject.sayed.bookapp.book_detail.Chapter;
import com.bookappproject.sayed.bookapp.main_books_database.AllBooksDatabaseHelper;
import com.bookappproject.sayed.bookapp.main_books_database.MainBookObject;

import java.util.ArrayList;

//Important Note: Here BookId is the Table Name;

public class BookDetailDatabaseSource {
    private BookDetailDatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public BookDetailDatabaseSource(Context context, String bookNameTbl) {
        databaseHelper = new BookDetailDatabaseHelper(context, bookNameTbl);
    }

    public void open(){
        database = databaseHelper.getWritableDatabase();
    }
    public void close(){
        database.close();
    }

    public boolean addBook(BookDetail bookDetail){
        String bookNameTbl = bookDetail.getBookID();
        this.open();
        String alu = "alu";
        ContentValues contentValues;
        boolean status = true;
        for (Chapter chapter:bookDetail.getChapters()) {
            contentValues = new ContentValues();
            contentValues.put(BookDetailDatabaseHelper.COL_FIREBASE_ID, chapter.getFirebaseID());
            contentValues.put(BookDetailDatabaseHelper.COL_SERIAL, chapter.getChapterSerial());
            contentValues.put(BookDetailDatabaseHelper.COL_TITLE, chapter.getChapterTitle());
            contentValues.put(BookDetailDatabaseHelper.COL_DESCRIPTION, chapter.getChapterDescription());

            long row_id = database.insert(bookNameTbl, null, contentValues);
            if (!(row_id>0)){
                status = false;
            }
        }
        this.close();
        return status;
    }


    //Get All Chapter
    public ArrayList<Chapter> getAllChapter(String bookNameTbl){
        ArrayList<Chapter>chapters =  new ArrayList<>();
        this.open();

        Cursor cursor = database.query(bookNameTbl, null, null, null,
                                        null, null, null);

        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex(BookDetailDatabaseHelper.COL_ID));
                String firebaseID = cursor.getString(cursor.getColumnIndex(BookDetailDatabaseHelper.COL_FIREBASE_ID));
                String chapterSerial = cursor.getString(cursor.getColumnIndex(BookDetailDatabaseHelper.COL_SERIAL));
                String chapterTitle = cursor.getString(cursor.getColumnIndex(BookDetailDatabaseHelper.COL_TITLE));
                String chapterDescription = cursor.getString(cursor.getColumnIndex(BookDetailDatabaseHelper.COL_DESCRIPTION));

                Chapter chapter = new Chapter(id, firebaseID, chapterSerial, chapterTitle, chapterDescription);
                chapters.add(chapter);
            }while (cursor.moveToNext());
        }
        cursor.close();
        this.close();
        return chapters;
    }

    public boolean deleteChapter(int rowId, String bookNameTbl){
        this.open();
        int deleted = database.delete(bookNameTbl,
                BookDetailDatabaseHelper.COL_ID+" =?",new String[]{Integer.toString(rowId)});
        this.close();
        if (deleted>0){
            return true;
        }else {
            return false;
        }
    }

    //TODO: Is there any error???
    public boolean deleteBook(String bookNameTbl){
        this.open();
        int deleted = database.delete(bookNameTbl,null,null);
        this.close();
        if (deleted>0){
            return true;
        }else return false;
    }

    //update single Chapter
    public boolean updateChapter(String bookNameTbl, Chapter chapter, int rowID) {
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BookDetailDatabaseHelper.COL_ID, chapter.getChapterId());
        contentValues.put(BookDetailDatabaseHelper.COL_FIREBASE_ID, chapter.getFirebaseID());
        contentValues.put(BookDetailDatabaseHelper.COL_SERIAL, chapter.getChapterSerial());
        contentValues.put(BookDetailDatabaseHelper.COL_TITLE, chapter.getChapterTitle());
        contentValues.put(BookDetailDatabaseHelper.COL_DESCRIPTION, chapter.getChapterDescription());


        int updated = database.update(bookNameTbl, contentValues,
                BookDetailDatabaseHelper.COL_ID+" = ?",new String[]{Integer.toString(rowID)});
        if (updated>0){
            return true;
        }else {
            return false;
        }
    }
}
