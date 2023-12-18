package com.mygdx.kotc.gamemodel.exceptions;

public class TileNotReachableException extends Exception {
    public TileNotReachableException(){
        super("Tile cannot be reached");
    }
}
