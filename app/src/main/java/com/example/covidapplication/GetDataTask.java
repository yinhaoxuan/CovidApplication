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

    static boolean hasData = false;
    private WeakReference<View> mView;
    public GetDataTask(View view) {
        super();
        mView = new WeakReference<>(view);
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        BarChart mCountryBar = mView.get().findViewById(R.id.country_bar);
        ArrayList<Place> countryList = MainActivity.placeManager.countryList;
        Log.d("mylist", Integer.toString(countryList.size()));
        Log.d("mainlist", Integer.toString(MainActivity.placeManager.countryList.size()));
        ArrayList<BarEntry> countryBarList = new ArrayList<>();
        int i = 0;
        System.out.println(countryList.size());
        for (Place country : countryList)
        {

//            if (country.name == "China" || country.name == "India") {
                countryBarList.add(new BarEntry(country.confirmed.get(country.confirmed.size() - 1), ++i));
//            }
        }
        BarDataSet countryBarSet = new BarDataSet(countryBarList, "Country");
        BarData countryData = new BarData(countryBarSet);
        mCountryBar.setData(countryData);
        mCountryBar.invalidate();

//        BarChart mProvinceBar = mView.get().findViewById(R.id.province_bar);
//        ArrayList<Place> provinceList = MainActivity.placeManager.provinceList;
//        ArrayList<BarEntry> provinceBarList = new ArrayList<>();
//        int j = 0;
//        for (Place province : provinceList) {
//            provinceBarList.add(new BarEntry(province.confirmed.get(province.confirmed.size() - 1), ++j));
//        }
//        BarData provinceData = new BarData(new BarDataSet(provinceBarList, "Province"));
//        mProvinceBar.setData(provinceData);
//        mProvinceBar.invalidate();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (!hasData) {
            Log.d("here", "here");
            MainActivity.placeManager.getData();
            hasData = true;
        }
        Log.d("afterbackground", Integer.toString(MainActivity.placeManager.countryList.size()));
        return null;
    }
}
