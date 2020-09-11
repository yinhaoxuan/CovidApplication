package com.example.covidapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Scholar_db.class},version =1,exportSchema = false)
public abstract class ScholarData extends RoomDatabase {
    public  abstract ScholarDAO scholar_dao();
    public static ScholarData getApp(Context context, String name)
    {
        return Room.databaseBuilder(context.getApplicationContext(),ScholarData.class,name).build();
    }
}
