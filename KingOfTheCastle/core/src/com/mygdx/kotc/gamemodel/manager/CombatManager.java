package com.mygdx.kotc.gamemodel.manager;

import com.mygdx.kotc.gamemodel.entities.*;
import com.mygdx.kotc.gamemodel.interfaces.CombatI;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class CombatManager implements CombatI{
    /**
     * a method to calculate the damage with the given modifiers
     * @param baseDamage
     * @param attackmodifier
     * @param defensivemodifier
     * @return the total damage that is calculated
     */
    @Override
    public int calculateDamage(int baseDamage, List<Modifier> attackmodifier, List<Modifier> defensivemodifier){
        int percentageIncrease = 0;
        int totalDamage = baseDamage;
        List<Modifier> modifiers = new ArrayList<>();
        modifiers.addAll(attackmodifier);
        modifiers.addAll(defensivemodifier);
        for(Modifier m : modifiers) {
            switch (m.getOperator().name()) {
                case "ADDITION":
                    totalDamage += m.getOperand();
                    break;
                case "PERCENTAGE":
                    percentageIncrease += m.getOperand();
                    break;
                default:
                    return 0;
            }
        }
        float totalPercentageIncrease = 1.0f + (float) percentageIncrease /100;
        totalDamage = totalDamage * (int)totalPercentageIncrease;
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
        player.getAttackModifiers().add(new Modifier(50,Operator.PERCENTAGE,2,true));
    }

    @Override
    public void attack(Player player, Player player2) {
        int totalDamage = calculateDamage(player.getStrength(),player.getAttackModifiers(),player2.getDefenseModifiers());
        player2.setCurrentHealth(player.getCurrentHealth()-totalDamage);
        if(totalDamage > 0){
            for(Modifier modifier : player2.getAttackModifiers()){
                if(modifier.isRequiresConcentration()){
                    player2.getAttackModifiers().remove(modifier);
                }
            }
        }
    }

    @Override
    public void block(Player player) {
        player.getDefenseModifiers().add(new Modifier(-player.getShield().getEquipmentValue(), Operator.ADDITION, 1, false));
    }

    @Override
    public void fleeFromCombat(Player player1, Combat combat){
        player1.setPlayerInCombat(false);
        combat.setPlayer1(null);
        combat.getPlayer2().setPlayerInCombat(false);
        combat.setPlayer2(null);
        System.out.println(player1 + " fleed from combat. The combat is over...");
    }

    @Override
    public void endCombat(Combat combat){
        combat.getPlayer2().getAttackModifiers().clear();
        combat.getPlayer2().getDefenseModifiers().clear();
        combat.getPlayer1().getAttackModifiers().clear();
        combat.getPlayer2().getDefenseModifiers().clear();
        combat.getPlayer2().setPlayerInCombat(false);
        combat.getPlayer1().setPlayerInCombat(false);
    }
}
