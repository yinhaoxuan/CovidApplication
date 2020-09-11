package com.example.covidapplication;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ClusterFragment extends Fragment {


    public ClusterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cluster, container, false);
        WebView webView = view.findViewById(R.id.cluster_web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        webView.getSettings().setLoadWithOverviewMode(true);
        Log.d("begin load", "begin load");
        webView.loadUrl("file:///android_asset/cluster.html");
        Log.d("end load", "end load");
        return view;
    }
}