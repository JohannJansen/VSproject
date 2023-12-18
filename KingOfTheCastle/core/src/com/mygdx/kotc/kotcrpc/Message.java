package com.mygdx.kotc.kotcrpc;

public class Message {
    private String methodname;
    private Object[] parameters;

    public Message(String methodname, Object[] parameters) {
        this.methodname = methodname;
        this.parameters = parameters;
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
}
