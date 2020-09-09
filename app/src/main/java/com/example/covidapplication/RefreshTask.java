package com.example.covidapplication;

import android.os.AsyncTask;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.lang.ref.WeakReference;

public class RefreshTask extends AsyncTask<Void, Void, Void> {

    private WeakReference<MainActivity> mContext;
    private WeakReference<TabFragment> mTabFragment;
    private WeakReference<String> mType;
    public RefreshTask(MainActivity context, TabFragment tabFragment, String type) {
        super();
        mContext = new WeakReference<>(context);
        mTabFragment = new WeakReference<>(tabFragment);
        mType = new WeakReference<>(type);
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        if (mTabFragment.get() != null) {
            mTabFragment.get().finishRefresh();
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mContext.get().eventManager.refresh(mType.get());
        return null;
    }
}
