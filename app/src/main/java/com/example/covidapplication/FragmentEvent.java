package com.example.covidapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class FragmentEvent extends Fragment {

    public FragmentEvent() {
        // Required empty public constructor
    }

    private ArrayList<String> searchHistory = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label_all));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label_news));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label_paper));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = view.findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        final SearchView searchView = view.findViewById(R.id.search);
        final ListView listView = view.findViewById(R.id.search_list);
//        listView.setAdapter(new ArrayAdapter<String>(getContext(), , searchHistory.toArray()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("search", "onQueryTextSubmit");
//                listView.set
                ((MainActivity)getActivity()).launchSearchActivity(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("search", "onQueryTextChange");
                return false;
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}