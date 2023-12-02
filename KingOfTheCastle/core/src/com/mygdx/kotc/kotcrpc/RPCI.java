package com.mygdx.kotc.kotcrpc;

import java.util.ArrayList;

public interface RPCI {
    void invoke(String method, Object[] parameters);
    void call(String method, Object[] parameters);
}
