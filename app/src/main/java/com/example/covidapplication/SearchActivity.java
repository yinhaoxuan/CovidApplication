package com.example.covidapplication;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        String query = intent.getStringExtra("query");
        String type = intent.getStringExtra("type");
        ArrayList<Event> eventList = MainActivity.eventManager.search(query, type);
        ((TextView)findViewById(R.id.search_text)).setText("Results for:" + query);
        RecyclerView recyclerView = findViewById(R.id.search_recycler);
        recyclerView.setAdapter(new EventListAdapter(this, eventList));
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this));
    }

    public void launchContentActivity(View view, final Event event) {
        Log.d("Main", "launch content");
        new GetContentTask(this, event).execute();
    }
}