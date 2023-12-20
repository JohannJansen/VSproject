package com.mygdx.kotc.gamemodel.entities;

public class Modifier {
    private int operand;
    private Operator operator;
    private int duration;
    private boolean requiresConcentration;

    public Modifier(){

    }

    public Modifier(int operand, Operator operator, int duration, boolean requiresConcentration) {
        this.operand = operand;
        this.operator = operator;
        this.duration = duration;
        this.requiresConcentration = requiresConcentration;
    }

    public int getOperand() {
        return operand;
    }

    public void setOperand(int operand) {
        this.operand = operand;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isRequiresConcentration() {
        return requiresConcentration;
    }

    public void setRequiresConcentration(boolean requiresConcentration) {
        this.requiresConcentration = requiresConcentration;
    }
}
