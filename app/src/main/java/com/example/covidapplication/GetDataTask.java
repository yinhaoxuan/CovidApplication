package com.example.covidapplication;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.*;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class GetDataTask extends AsyncTask<Void, Void, Void> {

    private WeakReference<MainActivity> mActivity;
    private ScholarManager mManager;
    public GetDataTask(MainActivity activity) {
        super();
        mActivity = new WeakReference<>(activity);
    }

    @Override
    protected void onPostExecute(Void unused) {
        MainActivity.scholarManager = mManager;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mManager = AllScholar.get_AllScholar(mActivity.get());
        Log.d("citation length", Integer.toString(mManager.citationList.size()));
        Log.d("passedaway length", Integer.toString(mManager.passedAwayList.size()));
        return null;
    }
}
