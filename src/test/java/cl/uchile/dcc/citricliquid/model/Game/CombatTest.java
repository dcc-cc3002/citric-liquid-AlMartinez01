package cl.uchile.dcc.citricliquid.model.Game;

import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.CombatEnemy;
import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.CombatPlayers;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.KO_StatePlayer;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Receive_damage_mode_player;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Standby_mode_Player;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts_ejm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CombatTest {
    CombatEnemy combat;
    CombatPlayers combat2;
    UnitsPlayer unitsPlayer;
    UnitsPlayer unitsPlayer2;
    UnitsPlayer unitsPlayer3;
    UnitsEnemy unitsEnemy;
    Carts carts;
    int hp = 100; int def = 2; int evd = 1; int atk = 3;
    int starts = 0; int wins = 0; int norma = 1;
    String name_carts = "name_cart";

    @BeforeEach
    void setUp(){
        carts = new Carts_ejm(name_carts, name_carts);
        unitsPlayer = new UnitsPlayer("suguri",hp,atk,def,evd,new Carts[] {carts, carts},null,starts,wins,norma);
        unitsEnemy = new UnitsEnemy("chicken", 100, -1, -1, 1, false, 0);
        unitsPlayer2 = new UnitsPlayer("suguri2", hp, atk, def, evd, new Carts[]{carts, carts}, null, starts, wins, norma);unitsPlayer2 = new UnitsPlayer("suguri2", hp, atk, def, evd, new Carts[]{carts, carts}, null, starts, wins, norma);
        unitsPlayer3 = new UnitsPlayer("suguri3", hp, atk, def, evd, new Carts[]{carts, carts}, null, starts, wins, norma);
        combat = new CombatEnemy();
        combat2 = new CombatPlayers();
    }
    @Test
    void constructorTest(){
        var expectedCombat = new CombatEnemy();
        assertEquals(expectedCombat,combat);
    }

    @Test
    void init_attack1() {
        combat.setCombat(unitsPlayer,unitsEnemy,null);
        combat.starter();
        assertEquals(Receive_damage_mode_player.class,unitsPlayer.getStatesPlayer().getClass());
        assertEquals(combat,unitsPlayer.getObserverEvent());
    }
    @Test
    void staterTest() throws IOException {
        //COMBATE ENTRE UN JUGADOR Y UNA COMPUTADORA
        combat.setCombat(unitsPlayer,unitsEnemy,null);
        combat.starter();

        unitsPlayer.option0();
        assertEquals(Standby_mode_Player.class,unitsPlayer.getStatesPlayer().getClass());
        assertNull(unitsPlayer.getObserverEvent());

        //COMBATE ENTRE JUGADORES
        combat2.setCombat(unitsPlayer2,unitsPlayer3,null);
        combat2.starter();

        assertEquals(Receive_damage_mode_player.class,unitsPlayer3.getStatesPlayer().getClass());
        assertEquals(Standby_mode_Player.class,unitsPlayer2.getStatesPlayer().getClass());
        assertEquals(combat2,unitsPlayer3.getObserverEvent());
        assertEquals(combat2,unitsPlayer2.getObserverEvent());
        unitsPlayer3.option1();
        assertEquals(Standby_mode_Player.class,unitsPlayer3.getStatesPlayer().getClass());
        assertEquals(Receive_damage_mode_player.class,unitsPlayer2.getStatesPlayer().getClass());
        unitsPlayer2.option0();
        assertEquals(Standby_mode_Player.class,unitsPlayer3.getStatesPlayer().getClass());
        assertEquals(Standby_mode_Player.class,unitsPlayer2.getStatesPlayer().getClass());

        //assertNull(unitsPlayer2.getObserverEvent());
    }

    @RepeatedTest(100)
    void combatToDeath() throws IOException {
        unitsPlayer.setHpActual(3);
        unitsEnemy.setHpActual(3);
        combat.setCombat(unitsPlayer,unitsEnemy,null);
        combat.starter();

        if (unitsEnemy.deadUnit()){return;}

        assertEquals(Receive_damage_mode_player.class,unitsPlayer.getStatesPlayer().getClass());

        int i = new Random().nextInt(2);

        switch (i){
            case 0 -> unitsPlayer.option0();
            case 1 -> unitsPlayer.option1();
        }

        if (unitsPlayer.deadUnit()){
            assertEquals(KO_StatePlayer.class,unitsPlayer.getStatesPlayer().getClass());
        }

    }
}
