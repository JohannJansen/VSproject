package com.mygdx.kotc.kotcrpc;

import java.util.ArrayList;

public interface RPCI {
    void invoke(String method, String... params);
    void call(String method, String... params);
}
