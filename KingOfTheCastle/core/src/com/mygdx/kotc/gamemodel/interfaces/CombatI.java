package com.mygdx.kotc.gamemodel.interfaces;

import com.mygdx.kotc.gamemodel.entities.Action;
import com.mygdx.kotc.gamemodel.entities.Modifier;
import com.mygdx.kotc.gamemodel.entities.Player;

import java.util.List;
import java.util.PriorityQueue;

public interface CombatI {
    int  calculateDamage(int baseDamage, List<Modifier> attackModifire, List<Modifier> defensiveModifire);
    void actionInCombat(Action action, PriorityQueue actionQueue);
    void charge(Player player);
    void attack(Player player);
    void block(Player player1, Player player2);
}
