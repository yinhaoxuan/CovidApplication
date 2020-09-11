package com.example.covidapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class PagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<String> mCurrentTab;
    public PagerAdapter(FragmentManager fm, ArrayList<String> currentTab) {
        super(fm, BEHAVIOR_SET_USER_VISIBLE_HINT);
        mCurrentTab = currentTab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        String name = mCurrentTab.get(position);
        if (name.equals("citation") || name.equals("passedaway")) {
            return ScholarTabFragment.newInstance(name);
        }
        else {
            return TabFragment.newInstance(name);
        }
    }

    @Override
    public int getCount() {
        return mCurrentTab.size();
    }
}
