package cl.uchile.dcc.citricliquid.model.Game;

import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.Combat;
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
        combat = new Combat(unitsEnemy,unitsPlayer);
    }
    @Test
    void constructorTest(){
        var expectedCombat = new Combat(unitsEnemy,unitsPlayer);
        assertEquals(expectedCombat,combat);
    }
}
