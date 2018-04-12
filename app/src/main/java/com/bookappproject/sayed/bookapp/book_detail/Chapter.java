package com.bookappproject.sayed.bookapp.book_detail;

public class Chapter {
    private int chapterId;
    private String chapterSerial;
    private String chapterTitle;
    private String chapterDescription;
    private String firebaseID;

    public Chapter(int chapterId,  String firebaseID, String chapterSerial, String chapterTitle, String chapterDescription) {
        this.chapterId = chapterId;
        this.chapterSerial = chapterSerial;
        this.chapterTitle = chapterTitle;
        this.chapterDescription = chapterDescription;
        this.firebaseID = firebaseID;
    }

    public Chapter(String chapterSerial, String chapterTitle, String chapterDescription, String firebaseID) {
        this.chapterSerial = chapterSerial;
        this.chapterTitle = chapterTitle;
        this.chapterDescription = chapterDescription;
        this.firebaseID = firebaseID;
    }

    public Chapter() {
    }

    public int getChapterId() {
        return chapterId;
    }

    public String getChapterSerial() {
        return chapterSerial;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public String getChapterDescription() {
        return chapterDescription;
    }

    public String getFirebaseID() {
        return firebaseID;
    }
}
