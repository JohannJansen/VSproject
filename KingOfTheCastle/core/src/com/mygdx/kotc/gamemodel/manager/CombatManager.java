package com.mygdx.kotc.gamemodel.manager;

import com.mygdx.kotc.gamemodel.entities.Action;
import com.mygdx.kotc.gamemodel.entities.Combat;
import com.mygdx.kotc.gamemodel.entities.Modifier;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.interfaces.CombatI;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class CombatManager implements CombatI{
    /**
     * a method to calculate the damage with the given modifiers
     * @param baseDamage
     * @param attackModifire
     * @param defensiveModifire
     * @return the total damage that is calculated
     */
    @Override
    public int calculateDamage(int baseDamage, List<Modifier> attackModifire, List<Modifier> defensiveModifire) {
        int percentageIncrease = 0;
        int totalDamage = 0;
        List<Modifier> modifiers = new ArrayList<>();
        modifiers.addAll(attackModifire);
        modifiers.addAll(defensiveModifire);
        for(Modifier m : modifiers){
            switch(m.getOperator().name()){
                case "ADDITION":
                    totalDamage += m.getOperand();
                    break;
                case "PERCENTAGE":
                    percentageIncrease += m.getOperand();
                    break;
                default:
                    return 0;
            }
            float totalPercentageIncrease = 1.0f + percentageIncrease/100;
            totalDamage = totalDamage * (int)totalPercentageIncrease;
        }
        return totalDamage;
    }

    /**
     * A method to
     * @param action 
     * @param actionQueue
     */
    @Override
    public void actionInCombat(Action action, PriorityQueue<Action> actionQueue) {
        actionQueue.add(action);
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
    @Override
    public void fleeFromCombat(Player player1, Combat combat){

    }
}
