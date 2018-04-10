package com.bookappproject.sayed.bookapp.book_detail;

import java.util.ArrayList;

public class BookDetail {

    private String bookID;
    private ArrayList<Chapter>chapters;

    public BookDetail(String bookID, ArrayList<Chapter> chapters) {
        this.bookID = bookID;
        this.chapters = chapters;
    }

    public BookDetail() {
    }

    public String getBookID() {
        return bookID;
    }

    public ArrayList<Chapter> getChapters() {
        return chapters;
    }
}
