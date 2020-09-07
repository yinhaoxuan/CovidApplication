package com.example.covidapplication;

import android.os.AsyncTask;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.lang.ref.WeakReference;

public class RefreshTask extends AsyncTask<Void, Void, Void> {

    private WeakReference<MainActivity> mContext;
    private WeakReference<RefreshLayout> mRefreshLayout;
    private WeakReference<String> mType;
    public RefreshTask(MainActivity context, RefreshLayout refreshLayout, String type) {
        super();
        mContext = new WeakReference<>(context);
        mRefreshLayout = new WeakReference<>(refreshLayout);
        mType = new WeakReference<>(type);
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        if (mRefreshLayout.get() != null) {
            mRefreshLayout.get().finishRefresh();
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mContext.get().eventManager.refresh(mType.get());
        return null;
    }
}
