package com.example.covidapplication;

import android.graphics.drawable.Drawable;

public class Scholar {

    String id;
    String name, posisition, affiliation, bio, edu, work;
    int citations, gindex, hindex, num_followed, num_viewed;
    double activity, diversity, newStar, sociability;
    Drawable avatar;
    boolean is_passedaway;

    public Scholar(String id) {
        this.id = id;
    }
}
