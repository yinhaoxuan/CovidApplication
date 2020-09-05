package com.example.covidapplication;

public class Event {
    public final String id, type, title, time, source;
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
}
