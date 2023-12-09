package com.mygdx.kotc.gamemodel.repositories;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static AtomicLong uniqueId=new AtomicLong();

    public Long newId() {
        Long newId = uniqueId.getAndIncrement();
        return newId;
    }
}
