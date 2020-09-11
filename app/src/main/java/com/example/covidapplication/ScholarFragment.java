package com.example.covidapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.ViewPager;
import com.example.covidapplication.itemtouchhelperdemo.demochannel.EditTabActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class ScholarFragment extends Fragment {

    private Context mContext;
    private boolean allIn, newsIn, paperIn;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ArrayList<String> mCurrentTabs = new ArrayList<>();
    public ScholarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        new GetDataTask().execute();
        View view = inflater.inflate(R.layout.fragment_scholar, container, false);
        mTabLayout = view.findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab_label_citation));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab_label_passedaway));
        mCurrentTabs.add("citation");
        mCurrentTabs.add("passedaway");
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mViewPager = view.findViewById(R.id.pager);
        mViewPager.setAdapter(new PagerAdapter(getActivity().getSupportFragmentManager(), mCurrentTabs));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}