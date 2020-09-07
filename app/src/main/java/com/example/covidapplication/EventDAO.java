package com.example.covidapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface EventDAO {
    @Query("select * from Event")
    List<Event> getAll();
    @Query("select * from Event where type=:str")
    List<Event> get_type(String str);
    @Query("select * from Event where title like '%'||:str||'%' ")
    List<Event> get_news(String str);
    @Insert
    void insert(Event e);
}
