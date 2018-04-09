package com.bookappproject.sayed.bookapp.maind_books_database;

public class MainBookObject {
    private int bookId;
    private String bookTitle;
    private String image;
    private String firebaseID;

    public MainBookObject(int bookId, String bookTitle, String image, String firebaseID) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.image = image;
        this.firebaseID = firebaseID;
    }

    public MainBookObject(String bookTitle, String image, String firebaseID) {
        this.bookTitle = bookTitle;
        this.image = image;
        this.firebaseID = firebaseID;
    }

    public MainBookObject() {
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getImage() {
        return image;
    }

    public String getFirebaseID() {
        return firebaseID;
    }
}
