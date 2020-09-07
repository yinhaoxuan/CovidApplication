package com.example.covidapplication;

import android.os.AsyncTask;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.lang.ref.WeakReference;

public class getMoreTask extends AsyncTask<Void, Void, Void> {

    private WeakReference<MainActivity> mContext;
    private WeakReference<RefreshLayout> mRefreshLayout;
    public getMoreTask(MainActivity context, RefreshLayout refreshLayout) {
        super();
        mContext = new WeakReference<>(context);
        mRefreshLayout = new WeakReference<>(refreshLayout);
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        mRefreshLayout.get().finishLoadmore();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mContext.get().eventManager.getMore();
        return null;
    }
}
