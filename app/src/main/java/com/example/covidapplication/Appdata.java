package com.example.covidapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Event.class,Event_content.class,Scholar_db.class},version =1,exportSchema = false)
public abstract class Appdata extends RoomDatabase {
    public abstract EventDAO eventdao();
    public abstract Event_contentDAO event_contentdao();
    public abstract ScholarDAO scholar_dao();
    public static Appdata getApp(Context context, String name)
    {
        return Room.databaseBuilder(context.getApplicationContext(),Appdata.class,name).build();
    }
}
