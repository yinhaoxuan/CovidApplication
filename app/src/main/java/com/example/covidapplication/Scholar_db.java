package com.example.covidapplication;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Scholar_db {
    @PrimaryKey
            @NonNull
    String id;
    String name, posisition, affiliation, bio, edu, work;
    int citations, gindex, hindex, num_followed, num_viewed;
    double activity, diversity, newStar, sociability;
    String avatar;
    boolean is_passedaway;

    public Scholar_db(String id) {
        this.id = id;
    }
}
