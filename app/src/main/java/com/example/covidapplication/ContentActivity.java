package com.example.covidapplication;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Intent intent = getIntent();
        TextView titleView = findViewById(R.id.content_title);
        titleView.setText(intent.getStringExtra("title"));
        TextView sourceView = findViewById(R.id.content_source);
        sourceView.setText(intent.getStringExtra("source"));
        TextView timeView = findViewById(R.id.content_time);
        timeView.setText(intent.getStringExtra("time"));
        TextView contentView = findViewById(R.id.content);
        contentView.setText(intent.getStringExtra("content"));
        Button button = findViewById(R.id.share);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, intent.getStringExtra("title"));
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "Share to..."));
            }
        });
    }
}