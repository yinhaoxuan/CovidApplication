package com.example.covidapplication;

import java.util.ArrayList;

public class MyPlaceManager implements PlaceManager {
    ArrayList<Place> countryList = new ArrayList<>(), provinceList = new ArrayList<>();

    @Override
    public void getData() {
        ArrayList<Integer> a = new ArrayList<>(), b = new ArrayList<>(), c = new ArrayList<>();
        a.add(1);
        b.add(2);
        c.add(3);
        countryList.add(new Place("China", "begin", a, b, c));
//        provinceList.add(new Place("Shanxi", "begin", c ,b, a));
    }
}
