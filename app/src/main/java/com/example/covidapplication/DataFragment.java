package com.example.covidapplication;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;


public class DataFragment extends Fragment {

    private PieChart countryPieChart, provincePieChart;

    public DataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        BarChart mCountryBar = view.findViewById(R.id.country_bar);
        ArrayList<Place> countryList = MainActivity.placeManager.countryList;
        Log.d("mylist", Integer.toString(countryList.size()));
        Log.d("mainlist", Integer.toString(MainActivity.placeManager.countryList.size()));
        ArrayList<BarEntry> countryBarList = new ArrayList<>();
        int i = 0;
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

        BarChart mProvinceBar = view.findViewById(R.id.province_bar);
        ArrayList<Place> provinceList = MainActivity.placeManager.provinceList;
        ArrayList<BarEntry> provinceBarList = new ArrayList<>();
        int j = 0;
        for (Place province : provinceList) {
            provinceBarList.add(new BarEntry(province.confirmed.get(province.confirmed.size() - 1), ++j));
        }
        BarData provinceData = new BarData(new BarDataSet(provinceBarList, "Province"));
        mProvinceBar.setData(provinceData);
        mProvinceBar.invalidate();
        return view;
    }
}