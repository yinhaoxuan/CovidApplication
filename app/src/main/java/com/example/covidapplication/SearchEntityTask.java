package com.example.covidapplication;

import android.os.AsyncTask;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class SearchEntityTask extends AsyncTask<Void, Void, Void> {

    private WeakReference<RecyclerView> mRecyclerView;
    private WeakReference<String> mQuery;
    private ArrayList<Entity> mList;

    public SearchEntityTask(RecyclerView recyclerView, String query) {
        super();
        mRecyclerView = new WeakReference<>(recyclerView);
        mQuery = new WeakReference<>(query);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mList = MainActivity.entityManager.search(mQuery.get());
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        mRecyclerView.get().setAdapter(new EntityListAdapter(mRecyclerView.get().getContext(), mList));
        mRecyclerView.get().setLayoutManager(new WrapContentLinearLayoutManager(mRecyclerView.get().getContext()));
        mRecyclerView.get().setVisibility(View.VISIBLE);
    }
}
