package com.example.covidapplication;

import java.util.ArrayList;

public class myEventManager implements EventManager {
    myEventManager() {
        Event event1 = new Event("a", "b", "c", "d", "e", false);
        Event event2 = new Event("f", "g", "h", "i", "j", true);
        Event event3 = new Event("k", "l", "m", "n", "o", false);
        allList.add(event1);
        allList.add(event2);
        allList.add(event3);
    }

    @Override
    public void refresh() {
        allList.clear();
        allList.add(new Event("id", "type", "title", "time", "source", false));
    }

    @Override
    public void getMore() {
        allList.add(new Event("id", "type", "title", "time", "source", false));
    }

    @Override
    public ArrayList<Event> search(String keyword) {
        return null;
    }

    @Override
    public String getContent(String id) {
        switch (id) {
            case "a":
                return "Content a";
            case "f":
                return "Content b";
            case "k":
                return "Content c";
        }
        return null;
    }
}
