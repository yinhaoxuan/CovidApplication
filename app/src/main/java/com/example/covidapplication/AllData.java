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

public class AllData implements EventManager {
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
    public void news(final int page, String types)
    {
        try {
            URL url = new URL("https://covid-dashboard.aminer.cn/api/events/list?type="+types+"&page="+page+"&size=20");
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
                int read=db.event_contentdao().has_read(n.getString("_id"));
                Event e=null;
                if(read==0) {
                    e = new Event(n.getString("_id"), type, n.getString("title"), n.getString("time"), n.getString("source"), false);
                }
                else
                {
                    e = new Event(n.getString("_id"), type, n.getString("title"), n.getString("time"), n.getString("source"), true);
                }
                allList.add(e);
                //db.eventdao().insert(e);
                if(type.equals("news"))
                    newsList.add(e);
                else if(type.equals("paper"))
                    paperList.add(e);
            }
            JSONObject array2 = new JSONObject(job.getString("pagination"));
            total = array2.getInt("total");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void refresh(String type) {
        //get number from now_line~now_line+20
        allList.clear();
        newsList.clear();
        paperList.clear();
        news(1,type);

    }
    @Override
    public void getMore(String type) {
        news(now_page,type);
        now_page++;
    }

    @Override
    public ArrayList<Event> search(String keyword, String type) {
        //ArrayList<Event> key_news= (ArrayList<Event>) db.eventdao().get_news(keyword);
        ArrayList<Event> key_news=new ArrayList<Event>();
        int len=allList.size();
        if(type.equals("all"))
        {
            for(int i=0;i<len;i++)
            {
                if(allList.get(i).title.indexOf(keyword)!=-1)
                    key_news.add(allList.get(i));
            }
        }
        else if(type.equals("news"))
        {
            for(int i=0;i<len;i++)
            {
                if(newsList.get(i).title.indexOf(keyword)!=-1)
                    key_news.add(newsList.get(i));
            }
        }
        else
        {   for(int i=0;i<len;i++)
            {
                if(paperList.get(i).title.indexOf(keyword)!=-1)
                    key_news.add(paperList.get(i));
            }
        }
        return key_news;
    }
    //download the news
    @Override
    public String getContent(String id) {
        String c = null;
        int read=db.event_contentdao().has_read(id);
        if(read!=0)
        {
            c=db.event_contentdao().get_text(id);
            return c;
        }
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
            //Event e=db.eventdao().get_from_id(id);
            Event_content content=new Event_content(id,c);
            db.event_contentdao().insert(content);
            int len=allList.size();
            for(int i=0;i<len;i++)
            {
                if(allList.get(i).id.equals(id))
                    allList.get(i).hasRead=true;
            }
            int len2=newsList.size();
            for(int i=0;i<len2;i++)
            {
                if(newsList.get(i).id.equals(id))
                    newsList.get(i).hasRead=true;
            }
            int len3=paperList.size();
            for(int i=0;i<len3;i++)
            {
                if(paperList.get(i).id.equals(id))
                    paperList.get(i).hasRead=true;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return c;
    }
}
