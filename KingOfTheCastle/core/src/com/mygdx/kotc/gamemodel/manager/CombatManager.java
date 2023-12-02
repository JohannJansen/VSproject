package com.mygdx.kotc.gamemodel.manager;

import com.mygdx.kotc.gamemodel.entities.Action;
import com.mygdx.kotc.gamemodel.entities.Modifier;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.interfaces.CombatI;

import java.util.List;
import java.util.PriorityQueue;

public class CombatManager implements CombatI{

    @Override
    public int calculateDamage(int baseDamage, List<Modifier> attackModifire, List<Modifier> defensiveModifire) {
        return 0;
    }

    @Override
    public void actionInCombat(Action action, PriorityQueue actionQueue) {

    }

    @Override
    public void charge(Player player) {

    }

    @Override
    public void attack(Player player) {

    }

    @Override
    public void block(Player player1, Player player2) {

    }
}
