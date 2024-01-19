package com.mygdx.kotc.gamemodel.interfaces;

import com.mygdx.kotc.gamemodel.entities.State;

public interface GameStateTransferI {
    void updateStateFromServer(State state);
    State exportServerState();
}
