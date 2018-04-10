package com.bookappproject.sayed.bookapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bookappproject.sayed.bookapp.main_books_database.AllBooksDatabaseSource;
import com.bookappproject.sayed.bookapp.main_books_database.MainBookObject;

public class DataAddActivity extends AppCompatActivity {
    EditText bookNameET;
    AllBooksDatabaseSource booksDatabaseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_add);

        bookNameET =findViewById(R.id.bookNameAddId);
        booksDatabaseSource = new AllBooksDatabaseSource(this);
    }

    public void addBook(View view) {
        String bookName = bookNameET.getText().toString();
        if (bookName.isEmpty()){
            bookNameET.setError("Please Enter Book Name");
        }else {
            MainBookObject bookObject = new MainBookObject(bookName, "One", "TssdSLller");
            boolean status = booksDatabaseSource.addBook(bookObject);
            if (status){
                Toast.makeText(this, "Book Added", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void gotoHome(View view) {
        startActivity(new Intent(this, Home.class));
    }
}
