package com.mygdx.kotc.gamemodel.repositories;

import com.google.common.base.Preconditions;
import com.mygdx.kotc.gamemodel.entities.Player;
import java.util.HashMap;

public class PlayerRepository implements RepositoryI{

    private final IdGenerator idGenerator;
    private final HashMap<Long, Player> playerHashMap;

    public PlayerRepository() {
        idGenerator = new IdGenerator();
        playerHashMap = new HashMap<>();
    }

    @Override
    public Long save(Object object) {
        Preconditions.checkArgument(object.getClass().equals(Player.class), "Object in Player Repository must be of class Player");
        Long objectId = idGenerator.newId();
        playerHashMap.put(objectId, (Player)object);
        return objectId;
    }

    @Override
    public Object findById(Long Id) {
        Preconditions.checkArgument(Id != null, "Id cannot be null!");
        return playerHashMap.get(Id);
    }

}
