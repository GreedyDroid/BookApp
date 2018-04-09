package com.bookappproject.sayed.bookapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

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
        bookObjects.add(new MainBookObject("bookOneID","Sample book one", R.drawable.sample_cover_photo));
        bookObjects.add(new MainBookObject("bookTwoID","Sample book Two", R.drawable.sample_cover_photo));
        bookObjects.add(new MainBookObject("bookThreeID","Sample book Three", R.drawable.sample_cover_photo));
        bookObjects.add(new MainBookObject("bookFourID","Sample book Four", R.drawable.sample_cover_photo));
        bookObjects.add(new MainBookObject("bookFiveID","Sample book Five", R.drawable.sample_cover_photo));
        bookObjects.add(new MainBookObject("bookSixID","Sample book Six", R.drawable.sample_cover_photo));

        bookAdapter = new MainBookAdapter(this, bookObjects);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        bookRV.setLayoutManager(llm);
        bookRV.setAdapter(bookAdapter);
    }
}
