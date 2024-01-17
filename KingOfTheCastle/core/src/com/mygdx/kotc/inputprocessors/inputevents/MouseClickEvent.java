package com.mygdx.kotc.inputprocessors.inputevents;

import com.mygdx.kotc.inputprocessors.inputevents.Event;
import com.mygdx.kotc.inputprocessors.inputevents.EventType;

public class MouseClickEvent extends Event {
    public int x;
    public int y;
    public MouseClickEvent(int x, int y) {
        super.eventType = EventType.MOUSECLICK;
        this.x = x;
        this.y = y;
    }
}
