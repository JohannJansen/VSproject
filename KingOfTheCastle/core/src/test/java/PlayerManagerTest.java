import com.mygdx.kotc.gamemodel.entities.Modifier;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.Vec2d;
import com.mygdx.kotc.gamemodel.factories.PlayerFactory;
import com.mygdx.kotc.gamemodel.manager.PlayerManager;
import com.mygdx.kotc.gamemodel.repositories.RepositoryI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PlayerManagerTest {
    private Player player;
    private Player player2;
    PlayerManager playerManager;

    @BeforeEach
    public void setUp(){
        player = PlayerFactory.createTestPlayer();
        player2 = PlayerFactory.createTestPlayer();
        playerManager = new PlayerManager();
    }

    @Test
    public void extendModifiersTest(){
        Modifier modifier = new Modifier();
        List<Modifier> modifierList = new ArrayList<>();
        playerManager.extendModifiers(modifier, modifierList);
        Assertions.assertTrue(modifierList.size() == 1);
    }

    @Test
    public void updatePositionTest(){
        Vec2d vec2d = new Vec2d(9, 4);
        playerManager.updatePosition(player, vec2d);
        Assertions.assertTrue(player.getPosition() == vec2d);
    }
}
