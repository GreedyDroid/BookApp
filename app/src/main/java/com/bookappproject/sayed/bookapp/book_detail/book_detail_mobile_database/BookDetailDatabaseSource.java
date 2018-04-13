package com.bookappproject.sayed.bookapp.book_detail.book_detail_mobile_database;

import android.content.ContentValues;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bookappproject.sayed.bookapp.book_detail.BookDetail;
import com.bookappproject.sayed.bookapp.book_detail.Chapter;

import java.util.ArrayList;

//Important Note: Here BookId is the Table Name;

public class BookDetailDatabaseSource {
    private BookDetailDatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public BookDetailDatabaseSource(Context context) {
        databaseHelper = new BookDetailDatabaseHelper(context);
    }


    public void open(){
        database = databaseHelper.getWritableDatabase();
/*        int version= database.getVersion();
        database.needUpgrade(version+1);*/

    }
    public void openRedable(){
        database = databaseHelper.getReadableDatabase();
    }

    public void close(){
        database.close();
    }



    public boolean addBook(BookDetail bookDetail){
        String bookNameTbl = bookDetail.getBookFirebaseID();
        open();

        ContentValues contentValues;
        boolean status = true;
        for (Chapter chapter:bookDetail.getChapters()) {
            contentValues = new ContentValues();
            contentValues.put(BookDetailDatabaseHelper.COL_FIREBASE_BOOK_ID, bookDetail.getBookFirebaseID());
            contentValues.put(BookDetailDatabaseHelper.COL_BOOK_NAME, bookDetail.getBookName());
            contentValues.put(BookDetailDatabaseHelper.COL_CHAPTER_FIREBASE_ID, chapter.getFirebaseID());
            contentValues.put(BookDetailDatabaseHelper.COL_CHAPTER_SERIAL, chapter.getChapterSerial());
            contentValues.put(BookDetailDatabaseHelper.COL_CHAPTER_TITLE, chapter.getChapterTitle());
            contentValues.put(BookDetailDatabaseHelper.COL_CHAPTER_DESCRIPTION, chapter.getChapterDescription());

            long row_id = database.insert(BookDetailDatabaseHelper.TABLE_NAME, null, contentValues);
            if (!(row_id>0)){
                status = false;
            }
        }
        this.close();
        return status;
    }


    //Get All Chapter
    public BookDetail getBookDetail(String bookFirebaseID){
        ArrayList<Chapter>chapters =  new ArrayList<>();
        this.openRedable();

        Cursor cursor = database.query(BookDetailDatabaseHelper.TABLE_NAME, null, BookDetailDatabaseHelper.COL_FIREBASE_BOOK_ID+" = ?",
                                        new String[]{bookFirebaseID},
                                        null, null, null);
        boolean flag = true;
        String bookName="";
        if (cursor.moveToFirst()){
            do {
                if(flag){
                    bookName = cursor.getString(cursor.getColumnIndex(BookDetailDatabaseHelper.COL_BOOK_NAME));
                    flag = false;
                }
                int id = cursor.getInt(cursor.getColumnIndex(BookDetailDatabaseHelper.COL_ID));
                String firebaseChapterID = cursor.getString(cursor.getColumnIndex(BookDetailDatabaseHelper.COL_CHAPTER_FIREBASE_ID));
                String chapterSerial = cursor.getString(cursor.getColumnIndex(BookDetailDatabaseHelper.COL_CHAPTER_SERIAL));
                String chapterTitle = cursor.getString(cursor.getColumnIndex(BookDetailDatabaseHelper.COL_CHAPTER_TITLE));
                String chapterDescription = cursor.getString(cursor.getColumnIndex(BookDetailDatabaseHelper.COL_CHAPTER_DESCRIPTION));

                Chapter chapter = new Chapter(id, firebaseChapterID, chapterSerial, chapterTitle, chapterDescription);
                chapters.add(chapter);
            }while (cursor.moveToNext());
        }
        cursor.close();
        this.close();

        return new BookDetail(bookName, bookFirebaseID, chapters);
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
    public boolean deleteBook(){
        this.open();
        int deleted = database.delete(BookDetailDatabaseHelper.TABLE_NAME,null,null);
        this.close();
        if (deleted>0){
            return true;
        }else return false;
    }

 /*   //update single Chapter
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
    }*/
}
