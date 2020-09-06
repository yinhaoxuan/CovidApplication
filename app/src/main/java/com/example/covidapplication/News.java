package com.example.covidapplication;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class News {
    private Appdata db;
    public int total;
    private  static News INSTANCE=null;
    private News(Context context)
    {
        this.db=Appdata.getApp(context,"News-db");
        //read all the current news
    }
    public static News get_News(Context context)
    {
        if(INSTANCE==null)
        {
            INSTANCE=new News(context);

        }
        return INSTANCE;
    }
    public void get_all_news()
    {
        URL url= null;
        try {
            for(int j=1;j<100;j++) {
                url = new URL("https://covid-dashboard.aminer.cn/api/events/list?type=paper&page="+j+"&size=20");
                HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                InputStream input = connect.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(input));
                String line = null;
                System.out.println(connect.getResponseCode());
                StringBuffer sb = new StringBuffer();
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                String buf = sb.toString();
                JSONObject job = new JSONObject(buf);
                JSONArray array1 = job.getJSONArray("data");
                for (int i = 0; i < 20; i++) {
                    JSONObject n = array1.getJSONObject(i);
                    Event e = new Event(n.getString("_id"), n.getString("type"), n.getString("title"), n.getString("time"), n.getString("source"), false);
                    db.eventdao().insert(e);
                }
                JSONObject array2 = new JSONObject(job.getString("pagination"));
                total = array2.getInt("total");

            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }
}
