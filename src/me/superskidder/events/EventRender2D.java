package me.superskidder.events;

import com.darkmagician6.eventapi.events.Event;

public class EventRender2D implements Event {
    float partialTicks;
    public EventRender2D(float partialTicks){
        this.partialTicks = partialTicks;
    }
}
