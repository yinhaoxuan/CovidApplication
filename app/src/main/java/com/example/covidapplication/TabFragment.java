package com.example.covidapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFragment extends Fragment {

    private static final String ARG_PARAM = "param";

    private String type;

    public TabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param Parameter 1.
     * @return A new instance of fragment TabFragment.
     */
    public static TabFragment newInstance(String param) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView mRecyclerView;
    private SearchView mSearchView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getString(ARG_PARAM);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        mRecyclerView = view.findViewById(R.id.recycler);
        ArrayList<Event> list;
        switch (type) {
            case "all":
                list = MainActivity.eventManager.allList;
                break;
            case "paper":
                list = MainActivity.eventManager.paperList;
                break;
            case "news":
                list = MainActivity.eventManager.newsList;
                break;
        }
        list = MainActivity.eventManager.allList;
        mRecyclerView.setAdapter(new EventListAdapter(this.getActivity(), list));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        return view;
    }
}