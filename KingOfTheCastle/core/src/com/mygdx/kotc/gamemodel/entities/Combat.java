package com.mygdx.kotc.gamemodel.entities;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Combat {
    private Player player1;
    private Player player2;
    private int turnCounter = 0;
    private PriorityQueue<Action> actionQueue;

    public Combat(){

    }

    public Combat(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.actionQueue = new PriorityQueue<>(Comparator.comparingInt(Action::getPriority));
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }

    public PriorityQueue<Action> getActionQueue() {
        return actionQueue;
    }
}
