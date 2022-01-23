package me.superskidder.utils;

public class TimerHelper {
    long start;

    public TimerHelper() {
        start = System.currentTimeMillis();
    }

    public boolean delay(long time) {
        long current = System.currentTimeMillis();
        if ((current - start) > time) {
            start = System.currentTimeMillis();
            return true;
        }
        return false;
    }

}
