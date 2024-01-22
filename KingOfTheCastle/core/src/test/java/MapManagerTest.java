import com.mygdx.kotc.gamemodel.entities.Map;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.entities.Tile;
import com.mygdx.kotc.gamemodel.entities.Vec2d;
import com.mygdx.kotc.gamemodel.exceptions.TileNotReachableException;
import com.mygdx.kotc.gamemodel.factories.MapFactory;
import com.mygdx.kotc.gamemodel.factories.PlayerFactory;
import com.mygdx.kotc.gamemodel.manager.CombatManager;
import com.mygdx.kotc.gamemodel.manager.MapManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MapManagerTest {
    private Player player;
    private Player player2;
    private final MapManager mapManager = new MapManager();
    private final CombatManager combatManager = new CombatManager();
    @BeforeEach
    public void setUp(){
        player = PlayerFactory.createTestPlayer();
        player2 = PlayerFactory.createTestPlayer();
        mapManager.setPlayerPosOnTile(new Vec2d(8,9),player);
    }

    @Test
    public void testFindNearbyPlayers() {
        // Erstellen Sie einige Testspieler und fügen Sie sie der Karte hinzu
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setPosition(new Vec2d(5, 5));
        player2.setPosition(new Vec2d(6, 5));
        mapManager.getMap().getTiles()[5][5].setOccupiedBy(player1);
        mapManager.getMap().getTiles()[6][5].setOccupiedBy(player2);

        // Rufen Sie die Methode findNearbyPlayers auf und überprüfen Sie das Ergebnis
        mapManager.findNearbyPlayers(player1,combatManager);
        Assertions.assertEquals(1, combatManager.getActiveCombats().size());
}

    @Test
    public void movePlayerToNotTraversableShouldNotWork() throws TileNotReachableException {
        //Spieler sollte sich auf der Map nicht bewegt haben
        Assertions.assertThrows(TileNotReachableException.class, () -> mapManager.movePlayer(player, new Vec2d(0,-1)));
    }

    @Test
    public void movePlayerTest() throws TileNotReachableException{
        //Spieler sollte nach dem Test gewisse Position haben
        mapManager.movePlayer(player, new Vec2d(0, 1));
        Assertions.assertEquals(8,player.getPosition().getPosX());
        Assertions.assertEquals(10,player.getPosition().getPosY());

    }

    @Test
    public void spawnPlayerTest(){
        boolean playerSpawned = false;
        //Spieler sollte sich in der Liste der Map befinden oder so
        mapManager.spawnPlayer(player,new Vec2d(1,1), new Vec2d(5,5));
       for (int i = 0; i < mapManager.getMap().getHeight();i++){
           for (int j = 0; j < mapManager.getMap().getWidth();j++) {
               if (mapManager.getMap().getTiles()[i][j].getOccupiedBy() != null
                       && mapManager.getMap().getTiles()[i][j].getOccupiedBy() == player) {
                   playerSpawned = true;
                   break;
               }
           }
       }
       Assertions.assertTrue(playerSpawned);
    }

    @Test
    public void setPlayerPos(){
        //Spieler sollte danach eine andere Position auf der Map haben
        player.setPosition(new Vec2d(8, 8));
        Assertions.assertEquals(player.getPosition().getPosX(), 8);
        Assertions.assertEquals(player.getPosition().getPosY(), 8);
    }

    @Test
    public void initiateCombatTest() throws Exception {
        //Zwei Spieler sollten sich danach in einem Kampf befinden und...
        player.setPosition(new Vec2d(1, 1));
        player2.setPosition(new Vec2d(1, 2));
        mapManager.initiateCombat(player, player2, 1);
        Assertions.assertTrue(player.getPlayerInCombat());
        Assertions.assertTrue(player2.getPlayerInCombat());
    }


}
