package com.mygdx.kotc.kotcrpc;

public interface RPCIServer {
    void call(String method, Object[] parameters);
}
