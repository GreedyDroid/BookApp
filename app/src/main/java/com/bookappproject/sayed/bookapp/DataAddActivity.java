package com.bookappproject.sayed.bookapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bookappproject.sayed.bookapp.book_detail.BookDetail;
import com.bookappproject.sayed.bookapp.book_detail.Chapter;
import com.bookappproject.sayed.bookapp.book_detail.book_detail_mobile_database.BookDetailDatabaseSource;
import com.bookappproject.sayed.bookapp.main_books_database.AllBooksDatabaseSource;
import com.bookappproject.sayed.bookapp.main_books_database.MainBookObject;

import java.util.ArrayList;

public class DataAddActivity extends AppCompatActivity {
    EditText bookNameET, chapterTitleET, chapterDescriptionET, firebaseBookIdET;
    AllBooksDatabaseSource booksDatabaseSource;
    BookDetailDatabaseSource bookDetailDatabaseSource;
    ArrayList<Chapter>chapters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_add);

        bookNameET =findViewById(R.id.bookNameAddId);
        chapterTitleET = findViewById(R.id.chapterTitleID);
        chapterDescriptionET = findViewById(R.id.chapterDescription);
        firebaseBookIdET = findViewById(R.id.frebaseBookID);

        booksDatabaseSource = new AllBooksDatabaseSource(this);
        chapters = new ArrayList<>();
    }

    public void addBook(View view) {
        String bookName = bookNameET.getText().toString();
        String bookFirebaseIdTbl = firebaseBookIdET.getText().toString();
        addChapter();
        if (bookName.isEmpty()){
            bookNameET.setError("Enter Book Name");
        }else if (bookFirebaseIdTbl.isEmpty()){
            firebaseBookIdET.setError("enter firebase Id");
        }else if (chapterTitleET.getText().toString().isEmpty()){
            chapterTitleET.setError("Please Enter The Title");
        }else if(chapterDescriptionET.getText().toString().isEmpty()){
            chapterDescriptionET.setError("Please Enter the Description");
        }else if (chapters.size()>0){
            BookDetail book = new BookDetail(bookName,bookFirebaseIdTbl, chapters);
            bookDetailDatabaseSource = new BookDetailDatabaseSource(this);
            boolean status = bookDetailDatabaseSource.addBook(book);

            if (status){
                Toast.makeText(this, "Book Added to First Database", Toast.LENGTH_SHORT).show();
                addDataToSecondDatabase(bookName,bookFirebaseIdTbl);
            }else {
                Toast.makeText(this, "Fild To add data to first database", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addDataToSecondDatabase(String bookName,String bookFirebaseIdTbl){
        MainBookObject bookObject = new MainBookObject(bookName, "One", bookFirebaseIdTbl);
        boolean status = booksDatabaseSource.addBook(bookObject);
        if (status){
            Toast.makeText(this, "Book Added to Second database", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, Home.class));
        }else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void gotoHome(View view) {
        startActivity(new Intent(this, Home.class));
    }

    public void addChapter(View view) {
        addChapter();
    }
    private void addChapter(){
        String chapterTitle = chapterTitleET.getText().toString();
        String chapterDescription = chapterDescriptionET.getText().toString();
        if (chapterTitle.isEmpty()){
            chapterTitleET.setError("Please Enter The Title");
        }else if(chapterDescription.isEmpty()){
            chapterDescriptionET.setError("Please Enter the Description");
        }else {
            Chapter chapter = new Chapter("First Part",chapterTitle, chapterDescription, "firebaseID");
            boolean status = chapters.add(chapter);
            if (status){
                Toast.makeText(this, "Chapter Added", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
