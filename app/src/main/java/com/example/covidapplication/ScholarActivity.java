package com.example.covidapplication;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ScholarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholar);
        Intent intent = getIntent();
        Scholar scholar = (Scholar) intent.getSerializableExtra("scholar");
        ImageView imageView = findViewById(R.id.avatar);
        new AvatarTask(imageView, scholar.avatar).execute();
        TextView nameView = findViewById(R.id.name);
        nameView.setText(scholar.name);
        TextView positionView = findViewById(R.id.position);
        positionView.setText(scholar.posisition);
        TextView affiliationView = findViewById(R.id.affiliation);
        affiliationView.setText(scholar.affiliation);
        TextView citationView = findViewById(R.id.citation);
        citationView.setText("Citations: " + scholar.citations);
        TextView gindexView = findViewById(R.id.gindex);
        gindexView.setText("G-index: " + scholar.gindex);
        TextView hindexView = findViewById(R.id.hindex);
        hindexView.setText("H-index: " + scholar.hindex);
        TextView followedView = findViewById(R.id.followed);
        followedView.setText("Number of followed: " + scholar.num_followed);
        TextView viewedView = findViewById(R.id.viewed);
        viewedView.setText("Number of viewed: " + scholar.num_viewed);
        TextView activityView = findViewById(R.id.activity);
        activityView.setText("Activity: " + scholar.activity);
        TextView diversityView = findViewById(R.id.diversity);
        diversityView.setText("Diversity: " + scholar.diversity);
        TextView newStarView = findViewById(R.id.new_star);
        newStarView.setText("New Star: " + scholar.newStar);
        TextView sociabilityView = findViewById(R.id.sociability);
        sociabilityView.setText("Sociability: " + scholar.sociability);
        TextView bioView = findViewById(R.id.bio);
        bioView.setText(scholar.bio);
        TextView eduView = findViewById(R.id.edu);
        eduView.setText(scholar.edu);
        TextView workView = findViewById(R.id.work);
        workView.setText(scholar.work);
    }
}