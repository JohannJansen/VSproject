package com.mygdx.kotc.gamemodel.exceptions;

public class CombatNotInitiatableException extends Exception{

    public CombatNotInitiatableException(){
        super("Combat cannot be initiated");
    }
}
