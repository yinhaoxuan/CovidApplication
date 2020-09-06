package com.example.covidapplication;

import android.content.Context;

import androidx.room.Room;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AllData implements EventManager {
    private Appdata db;
    public int total;
    private  static AllData INSTANCE=null;
    private int now_page=1;
    News news;
    private AllData(Context context)
    {
        this.db=Appdata.getApp(context,"News-db");
        //read all the current news
    }
    public static AllData get_AllData(Context context)
    {
        if(INSTANCE==null)
        {
            INSTANCE=new AllData(context);

        }
        return INSTANCE;
    }
    public void news(int page)
    {
        try {
                URL url = new URL("https://covid-dashboard.aminer.cn/api/events/list?type=paper&page="+page+"&size=20");
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
                    allList.add(e);
                    if(e.type.equals("news"))
                        newsList.add(e);
                    else if(e.type.equals("paper"))
                        paperList.add(e);
                    db.eventdao().insert(e);
                }
                JSONObject array2 = new JSONObject(job.getString("pagination"));
                total = array2.getInt("total");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void refresh() {
        //get number from now_line~now_line+20
        allList.clear();
        newsList.clear();
        paperList.clear();
        news(now_page);
        now_page++;
    }
    @Override
    public void getMore() {
        allList.clear();
        newsList.clear();
        paperList.clear();
        news(total/20);
    }

    @Override
    public ArrayList<Event> search(String keyword) {

        return null;
    }

    @Override
    public String getContent(String id) {
        return null;
    }
}
