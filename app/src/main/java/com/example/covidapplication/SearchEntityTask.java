package com.example.covidapplication;

import android.os.AsyncTask;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class SearchEntityTask extends AsyncTask<Void, Void, Void> {

    private WeakReference<View> mView;
    private WeakReference<String> mQuery;
    private ArrayList<Entity> mList;

    public SearchEntityTask(View view, String query) {
        super();
        mView = new WeakReference<>(view);
        mQuery = new WeakReference<>(query);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mList = MainActivity.entityManager.search(mQuery.get());
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        RecyclerView recyclerView = mView.get().findViewById(R.id.entity_recycler);
        recyclerView.setAdapter(new EntityListAdapter(mView.get().getContext(), mList));
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(mView.get().getContext()));
    }
}
