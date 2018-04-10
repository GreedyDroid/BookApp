package com.bookappproject.sayed.bookapp.book_detail;

public class Chapter {
    private String chapterId;
    private String chapterSerial;
    private String chapterTitle;
    private String chapterDescription;

    public Chapter(String chapterId, String chapterSerial, String chapterTitle, String chapterDescription) {
        this.chapterId = chapterId;
        this.chapterSerial = chapterSerial;
        this.chapterTitle = chapterTitle;
        this.chapterDescription = chapterDescription;
    }

    public Chapter() {
    }

    public String getChapterId() {
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
}
