package com.example.covidapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.room.Room;

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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.LogRecord;

public class AllData implements EventManager,PlaceManager {
    private Appdata db;
    public int total;
    private  static AllData INSTANCE=null;
    private int now_page=1;
    private static Handler handler;
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
    public void news(final int page)
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
                String type=n.getString("type");
                Event e = new Event(n.getString("_id"), type, n.getString("title"), n.getString("time"), n.getString("source"), false);
                allList.add(e);
                if(type.equals("news"))
                    newsList.add(e);
                else if(type.equals("paper"))
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
        ArrayList<Event> key_news= (ArrayList<Event>) db.eventdao().get_news(keyword);
        return key_news;
    }

    @Override
    public String getContent(String id) {
        String c = null;
        try {
            URL url = new URL("https://covid-dashboard.aminer.cn/api/event/"+id);
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
            JSONObject data=job.getJSONObject("data");
            c=data.getString("content");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void getData() {
        try {
            URL url = new URL("https://covid-dashboard.aminer.cn/api/dist/epidemic.json");
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
            Iterator<String> it=job.keys();
            while(it.hasNext())
            {
                String con=(String)it.next().toString();
                JSONObject result=job.getJSONObject(con);
                String[] all_con=con.split("|");
                String time=result.getString("begin");
                ArrayList<Integer> confirmed=new ArrayList<Integer>(),cured=new ArrayList<Integer>(),dead=new ArrayList<Integer>();
                JSONArray data=result.getJSONArray("data");
                int len=data.length();
                for(int i=0;i<len;i++)
                {
                    JSONArray data2= data.getJSONArray(i);
                    confirmed.add(data2.getInt(0));
                    cured.add(data2.getInt(2));
                    dead.add(data2.getInt(3));
                }
                String name;
                //country
                if(all_con.length==1)
                {
                    name=all_con[0];
                    Place p=new Place(name,time,confirmed,cured,dead);
                    countryList.add(p);
                }
                //province
                else {
                    name = all_con[1];
                    Place p=new Place(name,time,confirmed,cured,dead);
                    provinceList.add(p);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
