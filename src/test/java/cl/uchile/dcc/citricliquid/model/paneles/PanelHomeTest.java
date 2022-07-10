package cl.uchile.dcc.citricliquid.model.paneles;

import cl.uchile.dcc.citricliquid.model.controller.GameController;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanelHome.Select_player_PanelHome;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanelHome.Standly_mode_panelHome;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PanelHomeTest {
    PanelHome panelHome;
    UnitsPlayer suguri;
    int hp = 6; int def = 2; int evd = 1; int atk = 3;
    int starts = 0; int wins = 0; int norma = 1;
    GameController gameController;

    @BeforeEach
    void setUp() {
        suguri = new UnitsPlayer("suguri",hp,atk,def,evd,null,null,starts,wins,norma);
        panelHome = new PanelHome(null,null,null,suguri);
        gameController = new GameController();
        gameController.addPlayer(suguri);
        gameController.addTablero(new Panel[]{panelHome});
        panelHome.attach(gameController);
    }
    @Test
    void setStatesPanelHomeTest(){
        assertEquals(Standly_mode_panelHome.class,panelHome.getStatesPanelHome().getClass());
        panelHome.setStatesPanelHome(new Select_player_PanelHome(panelHome,suguri,0));
        assertEquals(Select_player_PanelHome.class,panelHome.getStatesPanelHome().getClass());
    }

    @Test
    void buildTest() {
        final var expected = new PanelHome(null,null,null,suguri);
        assertEquals(expected,panelHome);
    }

    @Test
    void equalTest() {
        final var expected = new PanelHome(null,null,null,suguri);
        final var expected2 = new PanelHome(null,null,null,null);
        assertTrue(panelHome.equals(expected));
        assertFalse(panelHome.equals(expected2));
        assertFalse(panelHome.equals(suguri));
    }

    @Test
    void activatorTest() {
        suguri.setHpActual(3);
        suguri.setStars(10);
        assertEquals(panelHome,gameController.tablero[0]);
        panelHome.activator(suguri);
        assertTrue(panelHome.unitExist(suguri));
        assertEquals(panelHome,suguri.getUbi());
        assertEquals(4,suguri.getHpActual());
        assertEquals(2,suguri.getLvlNorma());

        int[] winsreques = new int[]{2,5,9,14};
        int[] startReques =new int[]{30,70,120,200};
        int r= winsreques[0];
        int t= startReques[0];
        int i = 0;
        while( i <= 3){
            r = winsreques[i];
            t = startReques[i];
            suguri.setStars(t);
            suguri.setWins(r);
            panelHome.activator(suguri);
            assertEquals(i+3,suguri.getLvlNorma());
            i++;
        }
    }
}