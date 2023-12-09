package com.mygdx.kotc.gamemodel.manager;

import com.google.common.base.Preconditions;
import com.mygdx.kotc.gamemodel.entities.Modifier;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.Vec2d;
import com.mygdx.kotc.gamemodel.interfaces.PlayerI;
import com.mygdx.kotc.gamemodel.repositories.PlayerRepository;

import java.util.List;

public class PlayerManager implements PlayerI {
    private PlayerRepository playerRepository = new PlayerRepository();

    @Override
    public void extendModifiers(Modifier modifier, List<Modifier> modifiers) {
        Preconditions.checkArgument(modifier != null, "Modifier cannot be null!");
        modifiers.add(modifier);
    }

    @Override
    public void updatePosition(Long Id, Vec2d newPos) {
        Preconditions.checkArgument(Id != null, "Player Id cannot be null!");
        Preconditions.checkArgument(newPos != null, "New Position cannot be null!");
        Player player = (Player) playerRepository.findById(Id);
        player.setPosition(newPos);
    }
}
