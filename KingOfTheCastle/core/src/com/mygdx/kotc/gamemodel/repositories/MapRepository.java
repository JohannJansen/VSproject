package com.mygdx.kotc.gamemodel.repositories;

import com.google.common.base.Preconditions;
import com.mygdx.kotc.gamemodel.entities.Map;

import java.util.HashMap;

public class MapRepository implements RepositoryI {

    private final IdGenerator idGenerator;
    private final HashMap<Long, Map> mapHashMap;

    public MapRepository() {
        idGenerator = new IdGenerator();
        mapHashMap = new HashMap<>();
    }

    @Override
    public Long save(Object object) {
        Preconditions.checkArgument(object.getClass().equals(Map.class), "Object in Map Repository must be of class Map");
        Long objectId = idGenerator.newId();
        mapHashMap.put(objectId, (Map)object);
        return objectId;
    }

    @Override
    public Object findById(Long Id) {
        Preconditions.checkArgument(Id != null, "Id cannot be null!");
        return mapHashMap.get(Id);
    }

}
