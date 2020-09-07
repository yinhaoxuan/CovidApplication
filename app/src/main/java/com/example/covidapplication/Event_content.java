package com.example.covidapplication;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Event_content {
    @PrimaryKey
    @NonNull
    private String id;
    //public String title;
    public String content;

    public Event_content(@NonNull String id, String content) {
        this.id = id;
        //this.title = title;
        this.content = content;
    }

    @NonNull
    public String getId() {
        return id;
    }
}
