package com.mygdx.kotc.gamemodel.repositories;

import com.google.common.base.Preconditions;
import com.mygdx.kotc.gamemodel.entities.Combat;

import java.util.HashMap;

public class CombatRepository implements RepositoryI{

    private final IdGenerator idGenerator;
    private final HashMap<Long, Combat> combatHashMap;

    public CombatRepository() {
        idGenerator = new IdGenerator();
        combatHashMap = new HashMap<>();
    }

    @Override
    public Long save(Object object) {
        Preconditions.checkArgument(object.getClass().equals(Combat.class), "Object in Combat Repository must be of class Combat");
        Long objectId = idGenerator.newId();
        combatHashMap.put(objectId, (Combat)object);
        return objectId;
    }

    @Override
    public Object findById(Long Id) {
        Preconditions.checkArgument(Id != null, "Id cannot be null!");
        return combatHashMap.get(Id);
    }

}
