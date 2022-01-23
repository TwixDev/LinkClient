package me.superskidder.events;

import com.darkmagician6.eventapi.events.Event;

public class EventKey implements Event {
    int key;

    public EventKey(int key) {
        this.key = key;
    }


    public int getKey() {
        return key;
    }

}
