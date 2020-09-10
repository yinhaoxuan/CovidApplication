package com.example.covidapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

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

    private Context mContext;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;
    private SearchView mSearchView;
    private RecyclerView mSearchRecycler;
    private EventListAdapter mAdapter;
    private boolean mIsRefreshing = false;
    private ArrayList<Event> mEventList = new ArrayList<>();
    private ArrayList<String> searchHistory = new ArrayList<>();
    private TabFragment tabFragment = this;

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
        mAdapter = new EventListAdapter(this.getActivity(), tabFragment, mEventList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this.getContext()));
        new RefreshTask(tabFragment, type).execute();
        mSearchView = view.findViewById(R.id.search);
        mSearchRecycler = view.findViewById(R.id.search_recycler);
        mSearchRecycler.setAdapter(new StringListAdapter(this.getActivity(), searchHistory, type));
        mSearchRecycler.setLayoutManager(new WrapContentLinearLayoutManager(this.getContext()));
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("search", "onQueryTextSubmit");
                searchHistory.add(query);
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("query", query);
                intent.putExtra("type", type);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearchRecycler.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.INVISIBLE);
            }
        });
        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mSearchRecycler.setVisibility(View.INVISIBLE);
                mRecyclerView.setVisibility(View.VISIBLE);
                return false;
            }
        });

        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                new GetMoreTask(tabFragment, type).execute();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                new RefreshTask(tabFragment, type).execute();
            }
        });
        return view;
    }

    public void finishRefresh() {
        updateList();
        mRefreshLayout.finishRefresh();
    }

    public void finishLoadmore() {
        updateList();
        mRefreshLayout.finishLoadmore();
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
        mAdapter.notifyDataSetChanged();
    }
}