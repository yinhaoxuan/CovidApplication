package com.example.covidapplication;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

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
import java.util.HashMap;
import java.util.Iterator;

public class AllEntity implements EntityManager {
    @Override
    public ArrayList<Entity> search(String key) {
        URL url = null;
        ArrayList<Entity> entity=new ArrayList<Entity>();
        try {
            url = new URL("https://innovaapi.aminer.cn/covid/api/v1/pneumonia/entityquery?entity="+key);
            Log.d("search entity", key);
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
            JSONArray data=job.getJSONArray("data");

            int size=data.length();
            for(int i=0;i<size;i++)
            {
                JSONObject sub=data.getJSONObject(i);
                JSONObject sub2=sub.getJSONObject("abstractInfo");
                String en=sub2.getString("enwiki");
                String bai=sub2.getString("baidu");
                String zh=sub2.getString("zhwiki");
                JSONObject covid=sub2.getJSONObject("COVID");
                //properties in covid
                JSONObject pro=covid.getJSONObject("properties");
                Iterator<String> it =pro.keys();
                HashMap<String, String> p=new HashMap<String,String>();
                while(it.hasNext())
                {
                    String k=(String) it.next().toString();
                    String v=pro.getString(k);
                    p.put(k,v);
                }
                //relations in covid
                JSONArray re=covid.getJSONArray("relations");
                ArrayList<Relation> what=new ArrayList<Relation>();
                int resize=re.length();
                for(int j=0;j<resize;j++)
                {
                    JSONObject relate=re.getJSONObject(j);
                    Relation rr=new Relation(relate.getString("relation"),relate.getString("url"),relate.getString("label"),relate.getString("forward") == "true");
                    what.add(rr);
                }
                String img=sub.getString("img");
                Drawable d=null;
                if(img!=null)
                {
                    URL u=new URL(img);
                    InputStream content=(InputStream)u.getContent();
                    d=Drawable.createFromStream(content,"src");
                }
                Entity now=new Entity(sub.getDouble("hot"),sub.getString("label"),sub.getString("url"),en,bai,zh,p,what,d);
                entity.add(now);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("entity count", Integer.toString(entity.size()));
        return entity;
    }
}
