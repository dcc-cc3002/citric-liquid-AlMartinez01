package cl.uchile.dcc.citricliquid.model.paneles;

import cl.uchile.dcc.citricliquid.model.abstracto.Units;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PanelEncounterTest {
    PanelEncounter panelEncounter;
    UnitsEnemy chicken;
    UnitsEnemy roboBall;
    UnitsEnemy Store_Manager;

    @BeforeEach
    void setUp() {
        chicken = new UnitsEnemy("chicken", 3, -1, -1, +1,false,0);
        roboBall = new UnitsEnemy("Robo Ball", 3, -1, +1, -1,false,0);
        Store_Manager = new UnitsEnemy("Store Manager", 8,+3, +2, -1, true,0);
        panelEncounter = new PanelEncounter(null,null,null,chicken);
    }

    @Test
    void buildTest() {
        final var expected = new PanelEncounter(null,null,null,chicken);
        assertEquals(expected,panelEncounter);
    }

    @Test
    void equalTest(){
        final var expected = new PanelEncounter(null,null,null,chicken);
        final var notexpected = new PanelEncounter(null,null,null,null);
        assertTrue(panelEncounter.equals(expected));
        assertFalse(panelEncounter.equals(chicken));
        assertFalse(panelEncounter.equals(notexpected));
    }

    @Test
    void agregarwildTest() {
        panelEncounter.setEnemy_default(null);
        panelEncounter.agregarwild(Store_Manager);
        assertEquals(null,panelEncounter.getEnemy_default());
        final var expected = new UnitsEnemy("chicken", 3, -1, -1, +1,false,0);
        panelEncounter.agregarwild(chicken);
        assertEquals(expected,panelEncounter.getEnemy_actual());
    }

    @Test
    void bossActualTest() {
        final var expected = new UnitsEnemy("chicken", 3, -1, -1, +1,false,0);
        assertEquals(expected,panelEncounter.getEnemy_actual());
    }

    @Test
    void respawn_wild() {
        panelEncounter.setEnemy_actual(null);
        panelEncounter.respawn_wild();
        final var expected = new UnitsEnemy("chicken", 3, -1, -1, +1,false,0);
        assertEquals(expected,panelEncounter.getEnemy_actual());
    }



}