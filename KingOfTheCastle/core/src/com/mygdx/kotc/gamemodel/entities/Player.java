package com.mygdx.kotc.gamemodel.entities;

import java.util.List;

public class Player {
    private Vec2d position;
    private boolean isBlocking;
    private int speed;
    private int totalHp;
    private int defense;
    private int currentHealth;
    private int strength;
    private Equipment weapon;
    private Equipment shield;
    private Equipment armor;
    private List<Modifier> attackModifiers;
    private List<Modifier> defenseModifiers;
    private boolean playerInCombat;

    public Player(Vec2d position, boolean isBlocking, int speed, int totalHp, int defense, int currentHealth, int strength, Equipment weapon, Equipment shield, Equipment armor, List<Modifier> attackModifiers, List<Modifier> defenseModifiers, boolean playerInCombat) {
        this.position = position;
        this.isBlocking = isBlocking;
        this.speed = speed;
        this.totalHp = totalHp;
        this.defense = defense;
        this.currentHealth = currentHealth;
        this.strength = strength;
        this.weapon = weapon;
        this.shield = shield;
        this.armor = armor;
        this.attackModifiers = attackModifiers;
        this.defenseModifiers = defenseModifiers;
        this.playerInCombat = playerInCombat;
    }

    // -------------------------------- Getter Setter -------------------------------//
    public boolean getPlayerInCombat(){
        return playerInCombat;
    }

    public void setPlayerInCombat(boolean playerInCombat){
        this.playerInCombat = playerInCombat;
    }

    public Vec2d getPosition() {
        return position;
    }

    public void setPosition(Vec2d position) {
        this.position = position;
    }

    public boolean isBlocking() {
        return isBlocking;
    }

    public void setBlocking(boolean blocking) {
        isBlocking = blocking;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getTotalHp() {
        return totalHp;
    }

    public void setTotalHp(int totalHp) {
        this.totalHp = totalHp;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public Equipment getWeapon() {
        return weapon;
    }

    public void setWeapon(Equipment weapon) {
        this.weapon = weapon;
    }

    public Equipment getShield() {
        return shield;
    }

    public void setShield(Equipment shield) {
        this.shield = shield;
    }

    public Equipment getArmor() {
        return armor;
    }

    public void setArmor(Equipment armor) {
        this.armor = armor;
    }

    public List<Modifier> getAttackModifiers() {
        return attackModifiers;
    }

    public void setAttackModifiers(List<Modifier> attackModifiers) {
        this.attackModifiers = attackModifiers;
    }

    public List<Modifier> getDefenseModifiers() {
        return defenseModifiers;
    }

    public void setDefenseModifiers(List<Modifier> defenseModifiers) {
        this.defenseModifiers = defenseModifiers;
    }
}