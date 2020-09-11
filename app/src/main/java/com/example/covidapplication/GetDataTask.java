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

//    WeakReference<ScholarTabFragment> mFragment;
    public GetDataTask() {
        super();
//        mFragment = new WeakReference<>(fragment);
    }

    @Override
    protected void onPostExecute(Void unused) {
//        mFragment.
    }

    @Override
    protected Void doInBackground(Void... voids) {
        MainActivity.scholarManager.refresh();
        return null;
    }
}
