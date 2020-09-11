package com.example.covidapplication;

import android.content.Intent;
import android.text.Html;
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
        citationView.setText(Html.fromHtml("<b>Citations: </b>" + scholar.citations, Html.FROM_HTML_MODE_COMPACT));
        TextView gindexView = findViewById(R.id.gindex);
        gindexView.setText(Html.fromHtml("<b>G-index: </b>" + scholar.gindex, Html.FROM_HTML_MODE_COMPACT));
        TextView hindexView = findViewById(R.id.hindex);
        hindexView.setText(Html.fromHtml("<b>H-index: </b>" + scholar.hindex, Html.FROM_HTML_MODE_COMPACT));
        TextView followedView = findViewById(R.id.followed);
        followedView.setText(Html.fromHtml("<b>Number of followed: </b>" + scholar.num_followed, Html.FROM_HTML_MODE_COMPACT));
        TextView viewedView = findViewById(R.id.viewed);
        viewedView.setText(Html.fromHtml("<b>Number of viewed: </b>" + scholar.num_viewed, Html.FROM_HTML_MODE_COMPACT));
        TextView activityView = findViewById(R.id.activity);
        activityView.setText(Html.fromHtml("<b>Activity: </b>" + scholar.activity, Html.FROM_HTML_MODE_COMPACT));
        TextView diversityView = findViewById(R.id.diversity);
        diversityView.setText(Html.fromHtml("<b>Diversity: </b>" + scholar.diversity, Html.FROM_HTML_MODE_COMPACT));
        TextView newStarView = findViewById(R.id.new_star);
        newStarView.setText(Html.fromHtml("<b>New Star: </b>" + scholar.newStar, Html.FROM_HTML_MODE_COMPACT));
        TextView sociabilityView = findViewById(R.id.sociability);
        sociabilityView.setText(Html.fromHtml("<b>Sociability: </b>" + scholar.sociability, Html.FROM_HTML_MODE_COMPACT));
        TextView bioView = findViewById(R.id.bio);
        bioView.setText(Html.fromHtml(scholar.bio, Html.FROM_HTML_MODE_COMPACT));
        TextView eduView = findViewById(R.id.edu);
        eduView.setText(Html.fromHtml(scholar.edu, Html.FROM_HTML_MODE_COMPACT));
        TextView workView = findViewById(R.id.work);
        workView.setText(Html.fromHtml(scholar.work, Html.FROM_HTML_MODE_COMPACT));
    }
}