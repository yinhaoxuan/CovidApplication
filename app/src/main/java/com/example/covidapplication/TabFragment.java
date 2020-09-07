package com.example.covidapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
    private RefreshLayout mRefreshLayout;
    private SearchView mSearchView;
    private RecyclerView mSearchRecycler;
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
        ArrayList<Event> list = null;
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
        mRecyclerView.setAdapter(new EventListAdapter(this.getActivity(), list));
        /*TODO: solve crash*/
        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this.getActivity()));
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mSearchView = view.findViewById(R.id.search);
        mSearchRecycler = view.findViewById(R.id.search_recycler);
        mSearchRecycler.setAdapter(new StringListAdapter(this.getActivity(), searchHistory, type));
        mSearchRecycler.setLayoutManager(new WrapContentLinearLayoutManager(this.getActivity()));
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
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (mContext instanceof MainActivity) {
                    ((MainActivity) mContext).refresh(mRefreshLayout, type);
                }
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (mContext instanceof MainActivity) {
                    ((MainActivity) mContext).getMore(mRefreshLayout, type);
                }
            }
        });
        return view;
    }
}