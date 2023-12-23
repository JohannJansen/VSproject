package com.mygdx.kotc.gamemodel.entities;

public class Action {
    private ActionIdentifier actionIdentifier;
    private Player player;
    private int priority;

    public Action(ActionIdentifier actionIdentifier, Player player, int priority) {
        this.actionIdentifier = actionIdentifier;
        this.player = player;
        this.priority = priority;
    }

    public ActionIdentifier getActionIdentifier() {
        return actionIdentifier;
    }

    public void setActionIdentifier(ActionIdentifier actionIdentifier) {
        this.actionIdentifier = actionIdentifier;
    }

    public Player getPlayer() {
        return player;
    }

    public int getPriority() {
        return priority;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
