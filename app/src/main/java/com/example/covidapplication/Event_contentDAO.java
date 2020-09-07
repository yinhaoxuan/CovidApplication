package com.example.covidapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Event_contentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Event_content e);
    @Query("select * from Event_content")
    List<Event_content> getAll();
    @Query("select content from Event_content where id=:id")
    String get_text(String id);
    @Query("select count(*) from Event_content where id=:id")
    Integer has_read(String id);
}
