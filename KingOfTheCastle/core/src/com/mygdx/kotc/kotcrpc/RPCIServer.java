package com.mygdx.kotc.kotcrpc;

public interface RPCIServer {
    Message listenForIncommingCalls(long timeout);
}
