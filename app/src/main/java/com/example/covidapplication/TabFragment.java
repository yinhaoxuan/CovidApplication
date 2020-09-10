package com.example.covidapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.Collections;

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

    private Context mContext;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;
    private SearchView mSearchView;
    private RecyclerView mSearchRecycler;
    private EventListAdapter mAdapter;
    private boolean mIsRefreshing = false;
    private ArrayList<Event> mEventList = new ArrayList<>();
    private ArrayList<String> searchHistory = new ArrayList<>();

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
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        mRecyclerView = view.findViewById(R.id.recycler);
        mEventList = new ArrayList<>();
        updateList();
        mAdapter = new EventListAdapter(this.getActivity(), mEventList);
        mRecyclerView.setAdapter(mAdapter);
        /*TODO: solve crash*/
        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this.getContext()));
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return mIsRefreshing;
            }
        });
        mSearchView = view.findViewById(R.id.search);
        mSearchRecycler = view.findViewById(R.id.search_recycler);
        mSearchRecycler.setAdapter(new StringListAdapter(this.getActivity(), searchHistory, type));
        mSearchRecycler.setLayoutManager(new WrapContentLinearLayoutManager(this.getContext()));
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("search", "onQueryTextSubmit");
                searchHistory.add(query);
                ((MainActivity)getActivity()).launchSearchActivity(query, type);
                mSearchRecycler.setVisibility(View.INVISIBLE);
                mRecyclerView.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mSearchRecycler.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.INVISIBLE);
                Log.d("search", "onQueryTextChange");
                return false;
            }
        });

        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        final TabFragment tabFragment = this;
        mRefreshLayout.setOnRefreshListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mIsRefreshing = true;
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).getMore(tabFragment, type);
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mIsRefreshing = true;
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).refresh(tabFragment, type);
                }
            }
        });
        return view;
    }

    public void finishRefresh() {
        updateList();
        mRefreshLayout.finishRefresh();
        mIsRefreshing = false;
    }

    public void finishLoadmore() {
        updateList();
        mRefreshLayout.finishLoadmore();
        mIsRefreshing = false;
    }

    public void updateList() {
        switch (type) {
            case "all":
                mEventList.clear();
                mEventList.addAll(MainActivity.eventManager.allList);
                break;
            case "paper":
                mEventList.clear();
                mEventList.addAll(MainActivity.eventManager.paperList);
                break;
            case "news":
                mEventList.clear();
                mEventList.addAll(MainActivity.eventManager.newsList);
                break;
        }
    }
}