package com.bookappproject.sayed.bookapp.book_detail;

import java.util.ArrayList;

public class BookDetail {

    private String bookName;
    private String bookFirebaseID;
    private ArrayList<Chapter>chapters;

    public BookDetail(String bookName, String bookFirebaseID, ArrayList<Chapter> chapters) {
        this.bookName = bookName;
        this.bookFirebaseID = bookFirebaseID;
        this.chapters = chapters;
    }

    public BookDetail() {
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookFirebaseID() {
        return bookFirebaseID;
    }

    public ArrayList<Chapter> getChapters() {
        return chapters;
    }
}
