import com.mygdx.kotc.gamemodel.entities.*;
import com.mygdx.kotc.gamemodel.exceptions.CombatNotInitiatableException;
import com.mygdx.kotc.gamemodel.exceptions.PlayerHasNoHealthExeception;
import com.mygdx.kotc.gamemodel.factories.ActionFactory;
import com.mygdx.kotc.gamemodel.factories.MapFactory;
import com.mygdx.kotc.gamemodel.factories.PlayerFactory;
import com.mygdx.kotc.gamemodel.manager.CombatManager;
import com.mygdx.kotc.gamemodel.manager.MapManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;


public class CombatManagerTest {
    private Map map;
    private Player player;
    private Player player2;
    private final MapManager mapManager = new MapManager();
    private final CombatManager combatManager = new CombatManager();

    @BeforeEach
    public void setUp(){
        map = MapFactory.createMap(16, 16);
        player = PlayerFactory.createTestPlayer();
        player2 = PlayerFactory.createTestPlayer();
        CombatManager combatManager = new CombatManager();
        Modifier modifier = new Modifier();
        modifier.setOperand(100);
        modifier.setOperator(Operator.ADDITION);
        player.getAttackModifiers().add(modifier);

        player2.setCurrentHealth(100);
        player.setCurrentHealth(100);

        player.setShield(new Equipment(EquipmentType.SHIELD, 30));
        player2.setShield(new Equipment(EquipmentType.SHIELD, 30));

        Modifier modifier1 = new Modifier();
        modifier1.setOperator(Operator.ADDITION);
        modifier1.setOperand(-30);
        player2.getDefenseModifiers().add(modifier1);

        player.setArmor(new Equipment(EquipmentType.ARMOR, 20));
        player.setWeapon(new Equipment(EquipmentType.SWORD, 8));

        player2.setArmor(new Equipment(EquipmentType.ARMOR, 20));
        player2.setWeapon(new Equipment(EquipmentType.SWORD, 8));
    }

    /**
     * pre:
     * Two Players have to be in reach of each other and are in a combat
     * post:
     */
    @Test
    public void calculateDamageTest(){
        //Damage Output müsste richtig berechnet werden
        int damage = combatManager.calculateDamage(player.getWeapon().getEquipmentValue(), player.getAttackModifiers(), player2.getDefenseModifiers());
        Assertions.assertEquals(78, damage);
    }

    @Test
    public void actionInCombatTest(){
        Combat combat = new Combat(player,player2);
        Action action = ActionFactory.createAttackAction(player);
        combatManager.actionInCombat(action, combat.getActionQueue());
        Assertions.assertEquals(1, combat.getActionQueue().size());
    }


    @Test
    public void chargeTest(){
        //Der Player
        combatManager.charge(player);
        Assertions.assertEquals(2, player.getAttackModifiers().size());
    }

    @Test
    public void attackTest(){
        System.out.println(player2.getCurrentHealth());
        combatManager.attack(player, player2);
        //calculated damage should be 78 and the health of the players 100
        Assertions.assertEquals(30, player2.getCurrentHealth());
    }

    @Test
    public void blockTest(){
        combatManager.block(player);
        Assertions.assertEquals(1, player.getDefenseModifiers().size());
    }

    @Test
    public void fleeFromCombat() throws PlayerHasNoHealthExeception {
        player.setPosition(new Vec2d(1, 1));
        player2.setPosition(new Vec2d(1, 2));

        try {
            mapManager.initiateCombat(player, player2, 1);
        } catch (CombatNotInitiatableException e) {
            throw new RuntimeException(e);
        }

        Combat combat = new Combat(player,player2);
        Assertions.assertTrue(player.getPlayerInCombat());
        Assertions.assertTrue(player2.getPlayerInCombat());
        combatManager.fleeFromCombat(player,combat);
        Assertions.assertFalse(player.getPlayerInCombat());
        Assertions.assertFalse(player2.getPlayerInCombat());
    }
}
