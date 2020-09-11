package com.example.covidapplication;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScholarTabFragment extends Fragment {

    private static final String ARG_PARAM = "param";

    private String type;

    public ScholarTabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param Parameter 1.
     * @return A new instance of fragment TabFragment.
     */
    public static ScholarTabFragment newInstance(String param) {
        ScholarTabFragment fragment = new ScholarTabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    private Context mContext;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;
    private SearchView mSearchView;
    private RecyclerView mSearchRecycler;
    private ScholarListAdapter mAdapter;
    private boolean mIsRefreshing = false;
    private ArrayList<Event> mEventList = new ArrayList<>();
    private ArrayList<String> searchHistory = new ArrayList<>();
    private ScholarTabFragment mTabFragment = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getString(ARG_PARAM);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getContext();
        View view = inflater.inflate(R.layout.fragment_scholar_tab, container, false);
        mRecyclerView = view.findViewById(R.id.recycler);
        ArrayList<Scholar> scholars = type == "citation" ? MainActivity.scholarManager.citationList : MainActivity.scholarManager.passedAwayList;
        mAdapter = new ScholarListAdapter(this.getActivity(), scholars);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this.getContext()));
        return view;
    }
}