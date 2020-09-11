package com.example.covidapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Event.class,Event_content.class},version =1,exportSchema = false)
public abstract class Appdata extends RoomDatabase {
    public abstract EventDAO eventdao();
    public abstract Event_contentDAO event_contentdao();
    //public abstract ScholarDAO scholar_dao();

    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(" create TABLE Scholor_db (id text not null primary key,name text,position text,affilitation text, bio text,edu text,work text,citations int,gindex int,hindex int, num_followed int, num_viewed int,activity double,dicersity double,newStar double,sociability double,avatar text,is_passedaway boolean)");
        }
    };
    public static Appdata getApp(Context context, String name)
    {
        return Room.databaseBuilder(context.getApplicationContext(),Appdata.class,name).build();
    }
}
