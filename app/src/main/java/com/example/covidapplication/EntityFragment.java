package com.example.covidapplication;

import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EntityFragment extends Fragment {

    public EntityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private SearchView mSearchView;
    private RecyclerView mRecyclerView;
    private ArrayList<Entity> mEntityList;
    private EntityFragment mThis;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_entity, container, false);
        mThis = this;
        mSearchView = view.findViewById(R.id.entity_search);
        mRecyclerView = view.findViewById(R.id.entity_recycler);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                new SearchEntityTask(mRecyclerView, query).execute();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return view;
    }
}