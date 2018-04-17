package com.bookappproject.sayed.bookapp.book_detail.book_deteil_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bookappproject.sayed.bookapp.R;
import com.bookappproject.sayed.bookapp.book_detail.BookDetail;
import com.bookappproject.sayed.bookapp.book_detail.Chapter;
import com.bookappproject.sayed.bookapp.book_detail.book_detail_mobile_database.BookDetailDatabaseSource;

import java.util.ArrayList;

public class BookCover extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView bookTitleTV, bookFirebaseIdTV, totalChapterTV, chapterTV, chapterDescriptionTV;
    private String bookFirebaseID;
    BookDetailDatabaseSource bookDatabaseSource;
    BookDetail bookDetail;
    ArrayList<Chapter>chapters;
    private ListView navItemListView;
    private NavigationItemAdapter navigationItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_cover);

        bookTitleTV = findViewById(R.id.bookTitleId);
        bookFirebaseIdTV = findViewById(R.id.bookFirebaseID);
        totalChapterTV = findViewById(R.id.bookTotalChapterID);
        chapterTV = findViewById(R.id.chapterOneId);
        chapterDescriptionTV = findViewById(R.id.chapterDescriptionId);

        Intent intent = getIntent();
        bookFirebaseID = intent.getStringExtra("BOOK_ID");
        bookDatabaseSource = new BookDetailDatabaseSource(this);
        bookDetail = bookDatabaseSource.getBookDetail(bookFirebaseID);
        bookTitleTV.setText(bookDetail.getBookName());
        bookFirebaseIdTV.setText(bookDetail.getBookFirebaseID());
        chapters = bookDetail.getChapters();
        String toatalChapter = String.valueOf(chapters.size());
        totalChapterTV.setText(toatalChapter);
        chapterTV.setText(chapters.get(0).getChapterTitle());
        chapterDescriptionTV.setText(chapters.get(0).getChapterDescription());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(bookDetail.getBookName());
        setSupportActionBar(toolbar);

        navItemListView = findViewById(R.id.list_view_inside_nav);
        navigationItemAdapter = new NavigationItemAdapter(this, chapters);
        navItemListView.setAdapter(navigationItemAdapter);
        navItemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Chapter chapter =  chapters.get(position);
                chapterTV.setText(chapter.getChapterTitle());
                chapterDescriptionTV.setText(chapter.getChapterDescription());
                closeDrawer();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void closeDrawer(){
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.book_cover, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
