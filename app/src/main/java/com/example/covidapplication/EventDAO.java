package com.example.covidapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EventDAO {
    @Query("select * from Event")
    List<Event> getAll();
    @Query("select * from Event where type=:str")
    List<Event> get_type(String str);
    @Query("select * from Event where number>=:num and number<:num+20")
    List<Event> get_more_news(int num);
    @Insert
    void insert(Event e);
}
