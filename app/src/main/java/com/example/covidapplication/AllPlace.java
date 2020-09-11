package com.example.covidapplication;

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

public class AllPlace implements PlaceManager {

    @Override
    public void getData() {
        try {
            URL url = new URL("https://covid-dashboard.aminer.cn/api/dist/epidemic.json");
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            InputStream input = connect.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            String line = null;
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
                String[] all_con=con.split("\\|");
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
                else if(all_con.length==2 && all_con[0].equals("China")){
                    name = all_con[1];
                    Place p=new Place(name,time,confirmed,cured,dead);
                    System.out.println(name + "," + confirmed.get(confirmed.size() - 1));
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
