package com.example.covidapplication;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class EntityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity);
        Intent intent = getIntent();
        Entity entity = intent.getParcelableExtra("entity");
//        TextView titleView = findViewById(R.id.content_title);
//        titleView.setText(intent.getStringExtra("title"));
//        TextView sourceView = findViewById(R.id.content_source);
//        sourceView.setText(intent.getStringExtra("source"));
//        TextView timeView = findViewById(R.id.content_time);
//        timeView.setText(intent.getStringExtra("time"));
//        TextView contentView = findViewById(R.id.content);
//        contentView.setText(intent.getStringExtra("content"));
    }
}