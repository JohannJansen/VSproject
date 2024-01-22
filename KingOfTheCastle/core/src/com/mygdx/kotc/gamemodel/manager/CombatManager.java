package com.mygdx.kotc.gamemodel.manager;

import com.mygdx.kotc.gamemodel.entities.*;
import com.mygdx.kotc.gamemodel.exceptions.PlayerHasNoHealthExeception;
import com.mygdx.kotc.gamemodel.interfaces.CombatI;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class CombatManager implements CombatI{

    private List<Combat> activeCombats = new ArrayList<>();

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

    @Override
    public void actionInCombat(Action action, PriorityQueue<Action> actionQueue) {
        actionQueue.add(action);
        //test
        System.out.println("Action " + action + "added!");
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
    public void fleeFromCombat(Player player1, Combat combat) throws PlayerHasNoHealthExeception{
        if(player1.getCurrentHealth() > 0) {
            endCombat(combat);
            System.out.println(player1 + " fleed from combat. The combat is over...");
        }
        else {
            throw new PlayerHasNoHealthExeception();
        }
    }

    @Override
    public Combat createCombat(Player player1, Player player2){
        Combat combat = new Combat(player1,player2);
        activeCombats.add(combat);
        return combat;
    }

    @Override
    public void endCombat(Combat combat){
        combat.getPlayer2().getAttackModifiers().clear();
        combat.getPlayer2().getDefenseModifiers().clear();
        combat.getPlayer1().getAttackModifiers().clear();
        combat.getPlayer2().getDefenseModifiers().clear();
        combat.getPlayer2().setPlayerInCombat(false);
        combat.getPlayer1().setPlayerInCombat(false);
        combat.setPlayer1(null);
        combat.setPlayer2(null);
        activeCombats.remove(combat);
    }

    public List<Combat> getActiveCombats() {
        return activeCombats;
    }

    public void setActiveCombats(List<Combat> activeCombats) {
        this.activeCombats = activeCombats;
    }
}
