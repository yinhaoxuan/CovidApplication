package com.example.covidapplication;

import java.util.ArrayList;

public interface ScholarManager {
    // Sort by citations
    // is_passedaway = true
    public ArrayList<Scholar> citationList = new ArrayList<>(), passedAwayList = new ArrayList<>();
    public void refresh();
}