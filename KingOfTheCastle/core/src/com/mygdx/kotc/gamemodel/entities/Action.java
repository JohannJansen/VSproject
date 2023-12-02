package com.mygdx.kotc.gamemodel.entities;

public class Action {
    private ActionIdentifier actionIdentifier;
    private Player player;

    public ActionIdentifier getActionIdentifier() {
        return actionIdentifier;
    }

    public void setActionIdentifier(ActionIdentifier actionIdentifier) {
        this.actionIdentifier = actionIdentifier;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
