package com.mygdx.kotc.gamecontroller;

import com.mygdx.kotc.gamemodel.exceptions.TileNotReachableException;

public interface InputI {
    void left() throws TileNotReachableException;
    void right();
    void up();
    void down();
}
