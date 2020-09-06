package com.example.covidapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Event.class},version =1,exportSchema = false)
public abstract class Appdata extends RoomDatabase {
    public abstract EventDAO eventdao();
    public static Appdata getApp(Context context, String name)
    {
        return Room.databaseBuilder(context.getApplicationContext(),Appdata.class,name).build();
    }
}
