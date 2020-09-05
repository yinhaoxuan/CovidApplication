package com.example.covidapplication;

import java.util.ArrayList;

public class Place {
    public final String name, begin;
    public final ArrayList<Integer> confirmed, cured, dead;

    public Place(String name, String begin, ArrayList<Integer> confirmed, ArrayList<Integer> cured, ArrayList<Integer> dead) {
        this.name = name;
        this.begin = begin;
        this.confirmed = confirmed;
        this.cured = cured;
        this.dead = dead;
    }
}
