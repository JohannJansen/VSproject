package com.mygdx.kotc.inputprocessors.inputevents;

public class ButtonPressEvent extends Event {
    public int keycode;
    public ButtonPressEvent(int keycode) {
        super.eventType = EventType.BUTTONPRESS;
        this.keycode = keycode;
    }
}
