package com.example.covidapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;
    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm, BEHAVIOR_SET_USER_VISIBLE_HINT);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new TabFragmentAll();
            case 1: return new TabFragmentNews();
            case 2: return new TabFragmentPaper();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
