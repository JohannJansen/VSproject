package com.mygdx.kotc.kotcrpc;

public class Message {
    private String methodname;
    private Object[] parameters;

    public Message(String methodname, Object[] parameters) {
        this.methodname = methodname;
        this.parameters = parameters;
    }
}
