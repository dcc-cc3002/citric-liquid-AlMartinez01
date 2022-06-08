package cl.uchile.dcc.citricliquid.model.paneles;

import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class PanelDropTest {
    PanelDrop panelDrop;
    UnitsPlayer suguri;
    int hp = 6; int def = 2; int evd = 1; int atk = 3;
    int starts = 0; int wins = 0; int norma = 1;

    @BeforeEach
    void setUp() {
        panelDrop = new PanelDrop(null,null ,null);
        suguri = new UnitsPlayer("suguri",hp,atk,def,evd,null,null,starts,wins,norma);

    }

    @Test
    void buildTest() {
        final var expected = new PanelDrop(null,null,null);
        assertEquals(expected,panelDrop);
    }

    @RepeatedTest(100)
    void dropTest() {
        suguri.incrementStars(100);
        final long testSeed = new Random().nextLong();
        suguri.setSeed(testSeed);
        final int roll = new Random(testSeed).nextInt(6) + 1;
        final int expected =100- roll * Math.min(suguri.getLvlNorma(), 3);
        panelDrop.activator(suguri);
        assertEquals(expected,suguri.getStars());
    }

}