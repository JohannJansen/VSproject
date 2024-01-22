package com.mygdx.kotc.gamemodel.interfaces;

import com.mygdx.kotc.gamemodel.entities.Action;
import com.mygdx.kotc.gamemodel.entities.Combat;
import com.mygdx.kotc.gamemodel.entities.Modifier;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.exceptions.PlayerHasNoHealthExeception;

import java.util.List;
import java.util.PriorityQueue;

public interface CombatI {
    /**
     * Calculates the damage by the attack
     * @param baseDamage the base damage of the attack
     * @param attackModifire the attack modifiers
     * @param defensiveModifire the defensive modifiers
     * @return the damage by the attack
     */
    int calculateDamage(int baseDamage, List<Modifier> attackModifire, List<Modifier> defensiveModifire);

    /**
     * puts and action in the action queue
     * @param action the action to be put in the queue
     * @param actionQueue the queue to put the action in
     */
    void actionInCombat(Action action, PriorityQueue<Action> actionQueue);

    /**
     * a function that charges an attack from a player
     * the player cant attack for on round but the next round he will do double damage
     * @param player the player that charges
     */
    void charge(Player player);

    /**
     * a function that attacks a player
     * @param player the player that attacks
     * @param player2 the player that gets attacked
     */
    void attack(Player player,Player player2);

    /**
     * a function that blocks an attack from another player
     * @param player the player that wants to block
     */
    void block(Player player);

    /**
     * a function that allows a player to flee from combat
     * @param player the player that wants to flee
     * @param combat the combat the player wants to flee from
     * @throws PlayerHasNoHealthExeception
     */
    void fleeFromCombat(Player player, Combat combat) throws PlayerHasNoHealthExeception;

    /**
     * a function that ends a combat
     * @param combat the combat to be ended
     */
    void endCombat(Combat combat);

    /**
     * a function that creates a combat with two players
     * @param player1 the first player
     * @param player2 the second player
     * @return a combat with two players
     */
    Combat createCombat(Player player1, Player player2);
}
