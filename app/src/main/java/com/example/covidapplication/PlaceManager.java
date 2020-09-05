package com.example.covidapplication;

import java.util.ArrayList;

public interface PlaceManager {
    ArrayList<Place> countryList = new ArrayList<>(), provinceList = new ArrayList<>();
    void getData();
}
