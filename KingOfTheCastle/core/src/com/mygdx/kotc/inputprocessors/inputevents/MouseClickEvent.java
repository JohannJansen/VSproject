package com.mygdx.kotc.inputprocessors.inputevents;

public class MouseClickEvent extends Event {
    public int x;
    public int y;
    public MouseClickEvent(int x, int y) {
        super.eventType = EventType.MOUSECLICK;
        this.x = x;
        this.y = y;
    }
}
