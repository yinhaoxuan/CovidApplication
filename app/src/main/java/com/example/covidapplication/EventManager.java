package com.example.covidapplication;

import java.util.ArrayList;
import java.util.LinkedList;

public interface EventManager {
    int events_each_time = 20;
    ArrayList<Event> newsList = new ArrayList<>(), paperList = new ArrayList<>(), allList = new ArrayList<>();

    /* Update the backend database and fill lists with the newest events_each_time events. */
    /*type: all, news, paper*/
    void refresh(String type);

    /* Get more (older) events from backend database to lists. */
    void getMore(String type);

    ArrayList<Event> search(String keyword);
    String getContent(String id);
}
