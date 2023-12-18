import com.mygdx.kotc.gamemodel.entities.Map;
import com.mygdx.kotc.gamemodel.entities.Player;
import com.mygdx.kotc.gamemodel.factories.MapFactory;
import com.mygdx.kotc.gamemodel.factories.PlayerFactory;
import com.mygdx.kotc.gamemodel.manager.CombatManager;
import com.mygdx.kotc.gamemodel.manager.MapManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CombatManagerTest {
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
    public void calculateDamageTest(){
        //Damage Output müsste richtig berechnet werden
    }

    @Test
    public void actionInCombatTest(){
        //Eine Aktion müsste ausgeführt werden???
    }

    @Test
    public void chargeTest(){
        //Der Player
    }

    @Test
    public void attackTest(){

    }

    @Test
    public void blockTest(){

    }

    @Test
    public void fleeFromCombat(){

    }
}
