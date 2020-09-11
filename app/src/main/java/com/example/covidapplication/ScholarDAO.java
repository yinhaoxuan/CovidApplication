package com.example.covidapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ScholarDAO {
    @Query("select * from Scholar_db order by citations DESC")
    List<Scholar_db> get_All();
    //@Query("select * from Scholar_db where is_passedaway = true")
    //List<Scholar_db> get_passedAway();
    @Query("select count(*) from Scholar_db")
    Integer is_null();
    @Insert
    void insert(Scholar_db s);
    @Query("select count(*) from Scholar_db where id=:id")
    Integer already_in(String id);
}
