package com.mygdx.kotc.inputprocessors.inputevents;

import com.mygdx.kotc.inputprocessors.inputevents.Event;
import com.mygdx.kotc.inputprocessors.inputevents.EventType;

public class ButtonPressEvent extends Event {
    public int keycode;
    public ButtonPressEvent(int keycode) {
        super.eventType = EventType.BUTTONPRESS;
        this.keycode = keycode;
    }
}
