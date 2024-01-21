package com.mygdx.kotc.kotcrpc;

public class Message {
    private String methodname = "defaultname";
    private Object[] parameters = null;
    private String playerId;

    private Message(){

    }

    public Message(String methodname, Object[] parameters){
        this.methodname = methodname;
        this.parameters = parameters;
    }

    public Message(String playerId, String methodname, Object[] parameters) {
        this.methodname = methodname;
        this.parameters = parameters;
        this.playerId = playerId;
    }

    public String getMethodname() {
        return methodname;
    }

    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public String getPlayerId() {
        return playerId;
    }
}
