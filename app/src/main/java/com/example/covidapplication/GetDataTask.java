package com.example.covidapplication;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class GetDataTask extends AsyncTask<Void, Void, Void> {

    private WeakReference<View> mView;
    public GetDataTask(View view) {
        super();
        mView = new WeakReference<>(view);
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        PieChart mCountryPie = mView.get().findViewById(R.id.country_pie);
        ArrayList<Place> countryList = MainActivity.placeManager.countryList;
        ArrayList<PieEntry> countryPieList = new ArrayList<>();
        for (Place country : countryList) {
            countryPieList.add(new PieEntry(country.confirmed.get(country.confirmed.size() - 1), country.name));
        }
        PieData countryData = new PieData(new PieDataSet(countryPieList, "Country"));
        mCountryPie.setData(countryData);
        mCountryPie.invalidate();

        PieChart mProvincePie = mView.get().findViewById(R.id.province_pie);
        ArrayList<Place> provinceList = MainActivity.placeManager.provinceList;
        ArrayList<PieEntry> provincePieList = new ArrayList<>();
        for (Place province : provinceList) {
            provincePieList.add(new PieEntry(province.confirmed.get(province.confirmed.size() - 1), province.name));
        }
        PieData provinceData = new PieData(new PieDataSet(provincePieList, "Province"));
        mProvincePie.setData(provinceData);
        mProvincePie.invalidate();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        MainActivity.placeManager.getData();
        return null;
    }
}
