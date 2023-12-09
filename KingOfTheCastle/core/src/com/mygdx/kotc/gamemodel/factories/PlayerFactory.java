package com.mygdx.kotc.gamemodel.factories;

import com.mygdx.kotc.gamemodel.entities.Equipment;
import com.mygdx.kotc.gamemodel.entities.Modifier;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.Vec2d;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory {
    //public static Player createPlayer(Vec2d position, boolean isBlocking, int speed, int totalHP, int defense, int currentHealth, int strength, Equipment weapon, Equipment shield, Equipment armor, List<Modifier> attackModifiers, List<Modifier> defenseModifiers, boolean playerInCombat){
    //    Player player = new Player();
    //    player.setBlocking(isBlocking);
    //    player.setPosition(position);
    //    player.setDefense(defense);
    //    player.setSpeed(speed);
    //    player.setTotalHp(totalHP);
    //    player.setCurrentHealth(currentHealth);
    //    player.setStrength(strength);
    //    player.setArmor(armor);
    //    player.setShield(shield);
    //    player.setWeapon(weapon);
    //    player.setAttackModifiers(attackModifiers);
    //    player.setDefenseModifiers(defenseModifiers);
    //    player.setPlayerInCombat(playerInCombat);
    //    return player;
    //}

    public static Player createFighter(){
        List<Modifier> attackList = new ArrayList<>();
        List<Modifier> defenseList = new ArrayList<>();
        Vec2d v2d = new Vec2d(8,9);
        return new Player(v2d,false,19,300,50,300,
                21,EquipmentFactory.createSword(), null,null,attackList,defenseList,false);
    }
}
