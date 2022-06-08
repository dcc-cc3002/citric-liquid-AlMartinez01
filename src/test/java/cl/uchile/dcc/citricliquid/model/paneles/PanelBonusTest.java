package cl.uchile.dcc.citricliquid.model.paneles;

import cl.uchile.dcc.citricliquid.model.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.abstracto.Units;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class PanelBonusTest {
    PanelBonus panelBonus;
    UnitsPlayer suguri;
    int hp = 6; int def = 2; int evd = 1; int atk = 3;
    int starts = 0; int wins = 0; int norma = 1;

    @BeforeEach
    void setUp() {
        panelBonus = new PanelBonus(null,null,null);
        suguri = new UnitsPlayer("suguri",hp,atk,def,evd,null,null,starts,wins,norma);
    }

    @Test
    void buildTest() {
        final var expected = new PanelBonus(null,null,null);
        assertEquals(expected,panelBonus);
    }

    @RepeatedTest(100)
    void bonusTest() {
        final long testSeed = new Random().nextLong();
        suguri.setSeed(testSeed);
        final int roll = new Random(testSeed).nextInt(6) + 1;
        final int expected = roll * Math.min(suguri.getLvlNorma(), 3);
        panelBonus.activator(suguri);
        assertEquals(expected,suguri.getStars());
    }
}