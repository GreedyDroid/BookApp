package com.bookappproject.sayed.bookapp;

public class MainBookObject {
    private String bookId;
    private String bookTitle;
    private int image;

    public MainBookObject(String bookId, String bookTitle, int image) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.image = image;
    }

    public MainBookObject() {
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public int getImage() {
        return image;
    }
}
