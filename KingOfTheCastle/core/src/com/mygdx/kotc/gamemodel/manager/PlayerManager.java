package com.mygdx.kotc.gamemodel.manager;

import com.google.common.base.Preconditions;
import com.mygdx.kotc.gamemodel.entities.Modifier;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.Vec2d;
import com.mygdx.kotc.gamemodel.interfaces.PlayerI;
import com.mygdx.kotc.gamemodel.repositories.PlayerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerManager implements PlayerI {
    private List<Player> playerList = new ArrayList<>();

    @Override
    public void extendModifiers(Modifier modifier, List<Modifier> modifiers) {
        Preconditions.checkArgument(modifier != null, "Modifier cannot be null!");
        modifiers.add(modifier);
    }

    //not used
    @Override
    public void updatePosition(Player player, Vec2d newPos) {
        Preconditions.checkArgument(player != null, "Player Id cannot be null!");
        Preconditions.checkArgument(newPos != null, "New Position cannot be null!");
        player.setPosition(newPos);
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public Player getPlayerById(int playerId){
        Optional<Player> playerOptional = playerList.stream().filter(p -> p.getPlayerId() == playerId).findFirst();
        if (playerOptional.isPresent()){
            return playerOptional.get();
        } else {
            throw new IllegalArgumentException("No Player with the given PlayerID was found");
        }
    }
}
