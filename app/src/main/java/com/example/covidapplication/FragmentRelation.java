package com.example.covidapplication;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentRelation extends Fragment {

    public FragmentRelation() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private SearchView mSearchView;
    private RecyclerView mRecyclerView;
    private FragmentRelation mThis;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_relation, container, false);
        mSearchView = view.findViewById(R.id.entity_search);
        mRecyclerView = view.findViewById(R.id.entity_recycler);
        mThis = this;
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("search", "onQueryTextSubmit");
                new SearchEntityTask(view, query).execute();
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