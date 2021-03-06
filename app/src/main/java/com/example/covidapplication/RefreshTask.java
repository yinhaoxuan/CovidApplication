package com.example.covidapplication;

import android.os.AsyncTask;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.lang.ref.WeakReference;

public class RefreshTask extends AsyncTask<Void, Void, Void> {

    private WeakReference<TabFragment> mFragment;
    private WeakReference<String> mType;
    public RefreshTask(TabFragment fragment, String type) {
        super();
        mFragment = new WeakReference<>(fragment);
        mType = new WeakReference<>(type);
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        mFragment.get().finishRefresh();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        MainActivity.eventManager.refresh(mType.get());
        return null;
    }
}
