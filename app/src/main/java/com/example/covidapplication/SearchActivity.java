package com.example.covidapplication;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        String query = getIntent().getStringExtra("query");
        ArrayList<Event> eventList = MainActivity.eventManager.search(query);
        ((TextView)findViewById(R.id.search_text)).setText("Results for:" + query);
    }

}