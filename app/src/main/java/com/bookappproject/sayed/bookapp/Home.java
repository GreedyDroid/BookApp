package com.bookappproject.sayed.bookapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.bookappproject.sayed.bookapp.maind_books_database.AllBooksDatabaseSource;
import com.bookappproject.sayed.bookapp.maind_books_database.MainBookAdapter;
import com.bookappproject.sayed.bookapp.maind_books_database.MainBookObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    
    RecyclerView bookRV;
    MainBookAdapter bookAdapter;
    ArrayList<MainBookObject>bookObjects;
    AllBooksDatabaseSource booksDatabaseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        booksDatabaseSource = new AllBooksDatabaseSource(this);
        bookObjects = booksDatabaseSource.getAllMovies();

        bookRV = findViewById(R.id.bookRVid);

        setBookAdapter(bookObjects);
    }

    private void setBookAdapter(ArrayList<MainBookObject> bookObjects){
        if (bookObjects.size()>0){
            bookAdapter = new MainBookAdapter(this, bookObjects);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.HORIZONTAL);
            bookRV.setLayoutManager(llm);
            bookRV.setAdapter(bookAdapter);
        }else {
            Toast.makeText(this, "No books Found\nCheck Your Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    public void gotoEdit(View view) {
        startActivity(new Intent(this, DataAddActivity.class));
    }
}
