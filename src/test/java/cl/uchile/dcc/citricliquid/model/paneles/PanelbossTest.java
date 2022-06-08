package cl.uchile.dcc.citricliquid.model.paneles;

import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PanelbossTest {
    Panelboss panelboss;
    Panelboss panelboss2;
    UnitsEnemy chicken;
    UnitsEnemy Store_Manager;

    @BeforeEach
    void setUp() {
        chicken = new UnitsEnemy("chicken", 3, -1, -1, +1,false,0);
        Store_Manager = new UnitsEnemy("Store Manager", 8,+3, +2, -1, true,0);
        panelboss = new Panelboss(null,null,null,chicken,Store_Manager);
        panelboss2 =new Panelboss(null,null,null,null,null);


    }

    @Test
    void buildTest() {
        final var expected = new Panelboss(null,null,null,chicken,Store_Manager);
        assertEquals(expected,panelboss);
    }

    @Test
    void getBossTest() {
        assertEquals(Store_Manager,panelboss.getBoss_actual());
    }

    @Test
    void agregarBossTest() {
        panelboss.deletedBoss();
        assertNotEquals(Store_Manager,panelboss.getBoss_actual());
        panelboss.agregarBoss(chicken);
        assertNotEquals(chicken,panelboss.getBoss_actual());
        panelboss.agregarBoss(Store_Manager);
        assertEquals(Store_Manager,panelboss.getBoss_actual());
    }

    @Test
    void respawnTest() {
        panelboss.setBoss_actual(null);
        assertNotEquals(Store_Manager,panelboss.getBoss_actual());
        panelboss.respawn_boss();
        assertEquals(Store_Manager,panelboss.getBoss_actual());
    }

    @Test
    void equalTest() {
        final var expected = new Panelboss(null,null,null,chicken,Store_Manager);
        assertTrue(panelboss.equals(expected));
        assertFalse(panelboss2.equals(expected));
    }
}