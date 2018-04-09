package com.bookappproject.sayed.bookapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.bookappproject.sayed.bookapp.maind_books_database.MainBookAdapter;
import com.bookappproject.sayed.bookapp.maind_books_database.MainBookObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    RecyclerView bookRV;
    MainBookAdapter bookAdapter;
    ArrayList<MainBookObject>bookObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bookRV = findViewById(R.id.bookRVid);
        bookObjects = new ArrayList<>();


        bookAdapter = new MainBookAdapter(this, bookObjects);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        bookRV.setLayoutManager(llm);
        bookRV.setAdapter(bookAdapter);
    }
}
