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


public class FragmentEvent extends Fragment {

    private Context mContext;
    private ViewPager mViewPager;
    private boolean allIn, newsIn, paperIn;
    private TabLayout mTabLayout;
    private ArrayList<String> mCurrentTabs = new ArrayList<>();
    private TabLayout.OnTabSelectedListener listener = new TabLayout.OnTabSelectedListener() {
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
    };
    public FragmentEvent() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        mTabLayout = view.findViewById(R.id.tab_layout);
        mCurrentTabs.add("all");
        mCurrentTabs.add("news");
        mCurrentTabs.add("paper");
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab_label_all));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab_label_news));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab_label_paper));
        allIn = newsIn = paperIn = true;
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mViewPager = view.findViewById(R.id.pager);
        mViewPager.setAdapter(new PagerAdapter(getActivity().getSupportFragmentManager(), mCurrentTabs));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(listener);
        final Button button = view.findViewById(R.id.edit_button);
        mContext = getContext();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditTabActivity.class);
                intent.putExtra("all", allIn);
                intent.putExtra("news", newsIn);
                intent.putExtra("paper", paperIn);
                startActivityForResult(intent, 1);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCurrentTabs.clear();
        mTabLayout.removeAllTabs();
        if (data.getBooleanExtra("all", true)) {
            allIn = true;
            mCurrentTabs.add("all");
            mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab_label_all));
        }
        else {
            allIn = false;
        }
        if (data.getBooleanExtra("news", true)) {
            newsIn = true;
            mCurrentTabs.add("news");
            mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab_label_news));
        }
        else {
            newsIn = false;
        }
        if (data.getBooleanExtra("paper", true)) {
            paperIn = true;
            mCurrentTabs.add("paper");
            mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab_label_paper));
        }
        else {
            paperIn = false;
        }
        mViewPager.setAdapter(new PagerAdapter(getActivity().getSupportFragmentManager(), mCurrentTabs));
    }
}