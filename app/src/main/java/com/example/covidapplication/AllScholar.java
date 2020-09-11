package com.example.covidapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
import java.util.ArrayList;

public class AllScholar implements ScholarManager {
    //static ArrayList<Scholar> citationList = new ArrayList<>(), passedAwayList = new ArrayList<>();
    private ScholarData db;
    private  static AllScholar INSTANCE=null;

    private AllScholar(Context context)
    {
        this.db=ScholarData.getApp(context,"scholar-db");
    }
    public static AllScholar get_AllScholar(Context context){
        if(INSTANCE==null)
        {
            INSTANCE=new AllScholar(context);
        }
        //if nothing in db,read from url
        return INSTANCE;
    }
    public void refresh() {

//        INSTANCE.search();
        Log.d("refresh", "begin");
        ArrayList<Scholar_db> st=new ArrayList<>();
        st.addAll((ArrayList<Scholar_db>) INSTANCE.db.scholar_dao().get_All());
        int size=st.size();
        for(int i=0;i<size;i++)
        {
            INSTANCE.convert(st.get(i));
        }
        Log.d("refresh", "end");
    }
    private void convert(Scholar_db sd)
    {
        Scholar s=new Scholar(sd.id);
        s.name=sd.name;
        s.posisition=sd.posisition;
        s.affiliation=sd.affiliation;
        s.bio=sd.bio;
        s.edu=sd.edu;
        s.work=sd.work;
        s.citations=sd.citations;
        s.gindex=sd.gindex;
        s.hindex=sd.hindex;
        s.num_followed=sd.num_followed;
        s.num_viewed=sd.num_viewed;
        s.activity=sd.activity;
        s.diversity=sd.diversity;
        s.newStar=sd.newStar;
        s.sociability=sd.sociability;
        s.is_passedaway=sd.is_passedaway;
        s.avatar=sd.avatar;
        citationList.add(s);
        if(s.is_passedaway)
            passedAwayList.add(s);
    }
    private void search()
    {
        int is_null=db.scholar_dao().is_null();
        if(is_null>=83)
            return;
        try {
            URL url = new URL("https://innovaapi.aminer.cn/predictor/api/v1/valhalla/highlight/get_ncov_expers_list?v=2");
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
            JSONArray jdata=job.getJSONArray("data");
            int size=jdata.length();
            Log.d("fafafsize", String.valueOf(size));
            //for every person
            for(int i=0;i<jdata.length();i++)
            {
                JSONObject person=jdata.getJSONObject(i);
                if(db.scholar_dao().already_in(person.getString("id"))!=0)
                    continue;
                Scholar_db s=new Scholar_db(person.getString("id"));
//                Log.d("fafafsize", person.getString("id"));
                s.name=person.getString("name");
                s.avatar=person.getString("avatar");
                s.num_followed=person.getInt("num_followed");
                s.num_viewed=person.getInt("num_viewed");
                s.is_passedaway=person.getBoolean("is_passedaway");
                //indices
                JSONObject indices=person.getJSONObject("indices");
                s.activity=indices.getDouble("activity");
                s.diversity=indices.getDouble("diversity");
                s.newStar=indices.getDouble("newStar");
                s.sociability=indices.getDouble("sociability");
                s.citations=indices.getInt("citations");
                s.gindex=indices.getInt("gindex");
                s.hindex=indices.getInt("hindex");
                //profile
                JSONObject profile=person.getJSONObject("profile");
                if(profile.has("position"))
                    s.posisition=profile.getString("position");
                else s.posisition=null;
                s.affiliation=profile.getString("affiliation");
                s.bio=profile.getString("bio");
                if(profile.has("edu"))
                    s.edu=profile.getString("edu");
                else s.edu=null;
                if(profile.has("work"))
                    s.work=profile.getString("work");
                else
                {
//                    Log.d("fafafwork", person.getString("id"));
                    s.work=null;
                }
                db.scholar_dao().insert(s);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
