package com.mygdx.kotc.gamemodel.exceptions;

public class MaxPlayersReachedException extends Exception{
    public MaxPlayersReachedException(){
        super("Maximum amount of players reached");
    }
}
