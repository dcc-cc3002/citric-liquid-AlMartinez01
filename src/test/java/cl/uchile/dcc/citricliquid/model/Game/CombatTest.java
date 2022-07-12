package cl.uchile.dcc.citricliquid.model.Game;

import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.Combat;
import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.StatesCombat.AutomaticState;
import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.StatesCombat.SelectionDecisionPlayer;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Receive_damage_mode_player;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Standby_mode_Player;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts_ejm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CombatTest {
    Combat combat;
    UnitsPlayer unitsPlayer;
    UnitsEnemy unitsEnemy;
    Carts carts;
    int hp = 6; int def = 2; int evd = 1; int atk = 3;
    int starts = 0; int wins = 0; int norma = 1;
    String name_carts = "name_cart";

    @BeforeEach
    void setUp(){
        carts = new Carts_ejm(name_carts, name_carts);
        unitsPlayer = new UnitsPlayer("suguri",hp,atk,def,evd,new Carts[] {carts, carts},null,starts,wins,norma);
        unitsEnemy = new UnitsEnemy("chicken", 3, -1, -1, 1, false, 0);
        combat = new Combat(unitsPlayer,unitsEnemy);
    }
    @Test
    void constructorTest(){
        var expectedCombat = new Combat(unitsPlayer,unitsEnemy);
        assertEquals(expectedCombat,combat);
        assertEquals(AutomaticState.class,combat.getState().getClass());
        assertEquals(combat,combat.getState().getCombat());
    }

    @Test
    void init_attack1() {
        combat.init_attack(unitsPlayer);
        assertEquals(SelectionDecisionPlayer.class,combat.getState().getClass());
        assertEquals(combat,combat.getState().getCombat());
    }
    @Test
    void staterTest(){
        combat.starter(unitsPlayer,unitsEnemy);
        assertEquals(SelectionDecisionPlayer.class,combat.getState().getClass());
        assertEquals(combat,combat.getState().getCombat());
        assertEquals(Receive_damage_mode_player.class,unitsPlayer.getStatesPlayer().getClass());
        combat.option0();
        assertEquals(Standby_mode_Player.class,unitsPlayer.getStatesPlayer().getClass());
        assertEquals(AutomaticState.class,combat.getState().getClass());
    }
}
