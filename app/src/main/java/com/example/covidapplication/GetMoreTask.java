package com.example.covidapplication;

import android.os.AsyncTask;
import android.util.Log;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.lang.ref.WeakReference;

public class GetMoreTask extends AsyncTask<Void, Void, Void> {

    private WeakReference<TabFragment> mFragment;
    private WeakReference<String> mType;
    public GetMoreTask(TabFragment fragment, String type) {
        super();
        mFragment = new WeakReference<>(fragment);
        mType = new WeakReference<>(type);
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        mFragment.get().finishLoadmore();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d("before getmore", Integer.toString(MainActivity.eventManager.allList.size()));
        MainActivity.eventManager.getMore(mType.get());
        Log.d("after getmore", Integer.toString(MainActivity.eventManager.allList.size()));
        return null;
    }
}
