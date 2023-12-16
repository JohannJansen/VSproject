import com.mygdx.kotc.gamemodel.entities.Equipment;
import com.mygdx.kotc.gamemodel.entities.Map;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.Vec2d;
import com.mygdx.kotc.gamemodel.factories.MapFactory;
import com.mygdx.kotc.gamemodel.factories.PlayerFactory;
import com.mygdx.kotc.gamemodel.manager.CombatManager;
import com.mygdx.kotc.gamemodel.manager.MapManager;
import com.mygdx.kotc.gamemodel.manager.PlayerManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MapManagerTest {
    private Map map;
    private Player player;
    private Player player2;
    private final MapManager mapManager = new MapManager();
    private final CombatManager combatManager = new CombatManager();
    @BeforeEach
    public void setUp(){
        map = MapFactory.createMap(16, 16);
        player = PlayerFactory.createFighter();
        player2 = PlayerFactory.createFighter();
    }

    @Test
    public void movePlayerToNotTraversableShouldNotWork(){
        //Spieler sollte sich auf der Map nicht bewegt haben
        mapManager.movePlayer(player, new Vec2d(8, 9));
        Assertions.assertEquals(player.getPosition(), new Vec2d(8, 9));
    }

    @Test
    public void movePlayerTest(){
        //Spieler sollte nach dem Test gewisse Position habe
        mapManager.movePlayer(player, new Vec2d(8, 9));
        Assertions.assertEquals(player.getPosition(), new Vec2d(8, 8));
    }

    @Test
    public void spawnPlayerTest(){
        //Spieler sollte sich in der Liste der Map befinden oder so
        mapManager.spawnPlayer(player,map,new Vec2d(1,1), new Vec2d(1,1));
        Assertions.assertEquals(new Vec2d(1,1),player.getPosition());
    }

    @Test
    public void setPlayerPos(){
        //Spieler sollte danach eine andere Position auf der Map haben
        player.setPosition(new Vec2d(8, 8));
        Assertions.assertEquals(player.getPosition(), new Vec2d(8, 8));
    }

    @Test
    public void initiateCombatTest() throws Exception {
        //Zwei Spieler sollten sich danach in einem Kampf befinden und...
        player.setPosition(new Vec2d(1, 1));
        player2.setPosition(new Vec2d(1, 2));
        mapManager.initiateCombat(player, player2, 1);
        Assertions.assertTrue(player2.getPlayerInCombat());
    }
}
