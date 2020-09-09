package com.example.covidapplication;

import android.os.AsyncTask;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.lang.ref.WeakReference;

public class getMoreTask extends AsyncTask<Void, Void, Void> {

    private WeakReference<MainActivity> mContext;
    private WeakReference<TabFragment> mTabFragment;
    private WeakReference<String> mType;
    public getMoreTask(MainActivity context, TabFragment tabFragment, String type) {
        super();
        mContext = new WeakReference<>(context);
        mTabFragment = new WeakReference<>(tabFragment);
        mType = new WeakReference<>(type);
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        mTabFragment.get().finishLoadmore();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mContext.get().eventManager.getMore(mType.get());
        return null;
    }
}
