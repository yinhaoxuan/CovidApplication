package com.example.covidapplication;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Event {
    @PrimaryKey(autoGenerate = true)
    private int number;
    @NonNull
    public final String id;
    public String type, title, time, source;
    public boolean hasRead;

    public Event(String id, String type, String title, String time, String source, boolean hasRead) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.time = time;
        this.source = source;
        this.hasRead = hasRead;
        hasRead = false;
    }

    public int getNumber() {
        return number;
    }
}
