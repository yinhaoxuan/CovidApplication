package com.example.covidapplication;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Scholar implements Serializable {
    String name, id, posisition, affiliation, bio, edu, work;
    int citations, gindex, hindex, num_followed, num_viewed;
    double activity, diversity, newStar, sociability;
    String avatar;
    boolean is_passedaway;

    public Scholar(String name, String id, String posisition, String affiliation, String bio, String edu, String work, int citations, int gindex, int hindex, int num_followed, int num_viewed, double activity, double diversity, double newStar, double sociability, String avatar, boolean is_passedaway) {
        this.name = name;
        this.id = id;
        this.posisition = posisition;
        this.affiliation = affiliation;
        this.bio = bio;
        this.edu = edu;
        this.work = work;
        this.citations = citations;
        this.gindex = gindex;
        this.hindex = hindex;
        this.num_followed = num_followed;
        this.num_viewed = num_viewed;
        this.activity = activity;
        this.diversity = diversity;
        this.newStar = newStar;
        this.sociability = sociability;
        this.avatar = avatar;
        this.is_passedaway = is_passedaway;
    }
    public Scholar(String id) {
        this.id = id;
    }
}
