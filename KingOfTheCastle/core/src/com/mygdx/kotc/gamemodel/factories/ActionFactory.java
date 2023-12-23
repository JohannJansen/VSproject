package com.mygdx.kotc.gamemodel.factories;

import com.mygdx.kotc.gamemodel.entities.Action;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.ActionIdentifier;

public class ActionFactory {
    public static Action createAttackAction(Player player){
        return new Action(ActionIdentifier.ATTACK, player, 1);
    }
    public static Action createDefenceAction(Player player){
        return new Action(ActionIdentifier.DEFENSE, player, 5);
    }
    public static Action createChargeAction(Player player){
        return new Action(ActionIdentifier.CHARGE, player, 3);
    }

}
