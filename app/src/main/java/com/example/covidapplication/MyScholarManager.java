package com.example.covidapplication;

import android.graphics.drawable.Drawable;

public class MyScholarManager implements ScholarManager{
    MyScholarManager() {
        Scholar scholar = new Scholar("name", "id", "position", "affiliation", "bio", "edu", "work", 1, 2, 3, 4, 5, 1.0, 2.0, 3.0, 4.0, null, true);
        citationList.add(scholar);
        passedAwayList.add(scholar);
    }
}
