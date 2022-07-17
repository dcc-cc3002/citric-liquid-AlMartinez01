package cl.uchile.dcc.citricliquid.model.paneles;

import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Receive_damage_mode_player;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Standby_mode_Player;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PanelEncounterTest {
    PanelEncounter panelEncounter;
    UnitsEnemy chicken;
    UnitsEnemy roboBall;
    UnitsEnemy Store_Manager;

    UnitsPlayer unitsPlayer;
    private final static String PLAYER_NAME = "player";
    private final static int BASE_HP = 4;
    private final static int BASE_ATK = 1;
    private final static int BASE_DEF = -1;
    private final static int BASE_EVD = 2;

    @BeforeEach
    void setUp() {

        chicken = new UnitsEnemy("chicken", 3, -1, -1, +1,false,0);
        roboBall = new UnitsEnemy("Robo Ball", 3, -1, +1, -1,false,0);
        Store_Manager = new UnitsEnemy("Store Manager", 8,+3, +2, -1, true,0);
        panelEncounter = new PanelEncounter(null,null,null,chicken);
        unitsPlayer = new UnitsPlayer(PLAYER_NAME,BASE_HP,BASE_ATK,BASE_DEF,BASE_EVD,null,null,0,0,1);
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
    void bossActualTest() {
        final var expected = new UnitsEnemy("chicken", 3, -1, -1, +1,false,0);
        assertEquals(expected,panelEncounter.getEnemy_actual());
    }

    @Test
    void setEnemy_ActualTest(){
        panelEncounter.setEnemy_actual(roboBall);
        assertEquals(roboBall,panelEncounter.getEnemy_actual());
    }

    @Test
    void ActivatorTest() {
        panelEncounter.activator(unitsPlayer);

        assertTrue(panelEncounter.unitExist(unitsPlayer));
        if (chicken.deadUnit()){return;}
        assertEquals(Receive_damage_mode_player.class,unitsPlayer.getStatesPlayer().getClass());

        unitsPlayer.option0();
        assertEquals(Standby_mode_Player.class,unitsPlayer.getStatesPlayer().getClass());
    }

    @RepeatedTest(100)
    void restoreWild() {
        long entero = new Random().nextLong();
        Random random = new Random(entero);
        int i = random.nextInt(3);
        UnitsEnemy expectedEnemy = null;
        switch (i){
            case 0 -> expectedEnemy = new UnitsEnemy("Chicken",3,-1,-1,1,false,0);
            case 1 -> expectedEnemy = new UnitsEnemy("Robo Ball",3,-1,1,-1,false,0);
            case 2 -> expectedEnemy = new UnitsEnemy("Seagull",3,1,-1,-1,false,0);
        }
        panelEncounter.setSeed(entero);
        panelEncounter.setEnemy_actual(null);
        panelEncounter.restoredWild();
        assertEquals(expectedEnemy,panelEncounter.getEnemy_actual());
    }
}