package me.superskidder.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.types.EventType;

public class EventUpdate implements Event {
    float yaw, pitch;
    EventType type;

    public EventUpdate(float yaw, float pitch, EventType type) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.type = type;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public EventType getType() {
        return type;
    }
}
