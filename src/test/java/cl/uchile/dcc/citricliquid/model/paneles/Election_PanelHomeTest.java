package cl.uchile.dcc.citricliquid.model.paneles;

import cl.uchile.dcc.citricliquid.model.controller.GameController;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanelHome.Select_player_PanelHome;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanelHome.Standly_mode_panelHome;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Election_PanelHomeTest {
    GameController gameController; // SOLO SERA PARA QUE PASE LOS TEST
    PanelHome panelHome;
    UnitsPlayer suguri;
    UnitsPlayer notSuguri;
    int hp = 6; int def = 2; int evd = 1; int atk = 3;
    int starts = 0; int wins = 0; int norma = 1;

    //PANELES//
    private Panel panel1;
    private Panel panel2;
    private Panel panel3;
    private Panel panel4;
    private Panel panel5;
    private Panel panel6;

    @BeforeEach
    void setUp() {
        suguri = new UnitsPlayer("suguri",hp,atk,def,evd,null,null,starts,wins,norma);
        notSuguri = new UnitsPlayer("ramon",hp,atk,def,evd,null,null,starts,wins,norma);
        panelHome = new PanelHome(null,null,null,suguri);
        gameController = new GameController();
        panelHome.attach(gameController);

        panel1 = new Panel(null,null,null);
        panel2 = new Panel(null,null,null);
        panel3 = new Panel(null,null,null);
        panel4 = new Panel(null,null,null);
        panel5 = new Panel(null,null,null);
        panel6 = new Panel(null,null,null);

        panel1.addNextPanel(panel2);
        panel2.addNextPanel(panelHome);
        panelHome.addNextPanel(panel3);
        panel3.addNextPanel(panel4);
        panel4.addNextPanel(panel5);
        panel5.addNextPanel(panel6);
        panel6.addNextPanel(panel1);

        panel1.unitPlayer(suguri);
        panel1.unitPlayer(notSuguri);
    }
    @RepeatedTest(100)
    void moveTestOption0() throws IOException {
        assertEquals(Standly_mode_panelHome.class,panelHome.getStatesPanelHome().getClass());
        final long testSeed = new Random().nextLong();
        suguri.setSeed(testSeed);
        int i = new Random(testSeed).nextInt(6) + 1;
        suguri.play();

        if (i > 1){
            assertEquals(Select_player_PanelHome.class,panelHome.getStatesPanelHome().getClass());/// SE ACTIVO EL PANEL
            panelHome.rollDice();
            assertEquals(Standly_mode_panelHome.class,panelHome.getStatesPanelHome().getClass());/// SE ACTIVO EL PANEL
            if (i == 3){
                assertTrue(panel3.unitExist(suguri));
                assertEquals(panel3,suguri.getUbi());
                assertFalse(panel1.unitExist(suguri));
                assertFalse(panel2.unitExist(suguri));
                assertFalse(panel4.unitExist(suguri));
                assertFalse(panel5.unitExist(suguri));
                assertFalse(panel6.unitExist(suguri));
            }
            else if (i == 4){
                assertTrue(panel4.unitExist(suguri));
                assertEquals(panel4,suguri.getUbi());
                assertFalse(panel1.unitExist(suguri));
                assertFalse(panel2.unitExist(suguri));
                assertFalse(panel3.unitExist(suguri));
                assertFalse(panel5.unitExist(suguri));
                assertFalse(panel6.unitExist(suguri));
            }
            else if (i == 5){
                assertTrue(panel5.unitExist(suguri));
                assertEquals(panel5,suguri.getUbi());
                assertFalse(panel1.unitExist(suguri));
                assertFalse(panel2.unitExist(suguri));
                assertFalse(panel3.unitExist(suguri));
                assertFalse(panel4.unitExist(suguri));
                assertFalse(panel6.unitExist(suguri));
            }
            else if (i == 6){
                assertTrue(panel6.unitExist(suguri));
                assertEquals(panel6,suguri.getUbi());
                assertFalse(panel1.unitExist(suguri));
                assertFalse(panel2.unitExist(suguri));
                assertFalse(panel3.unitExist(suguri));
                assertFalse(panel4.unitExist(suguri));
                assertFalse(panel5.unitExist(suguri));
            }
        }
        else{
            assertEquals(Standly_mode_panelHome.class,panelHome.getStatesPanelHome().getClass());
            panelHome.option0();
            panelHome.option1();
            panelHome.option2();
            panelHome.option3();
            panelHome.option4();
            panelHome.option5();
            panelHome.option6();
            panelHome.option7();
            panelHome.option8();
            panelHome.option9();
            panelHome.rollDice();
        }
    }
    @RepeatedTest(100)
    void moveTestOption1() throws IOException {
        assertEquals(Standly_mode_panelHome.class,panelHome.getStatesPanelHome().getClass());
        final long testSeed = new Random().nextLong();
        suguri.setSeed(testSeed);
        int i = new Random(testSeed).nextInt(6) + 1;
        suguri.play();

        if (i > 1){
            //OPCIONES QUE NO HACEN NADA//
            panelHome.option2();
            panelHome.option3();
            panelHome.option4();
            panelHome.option5();
            panelHome.option6();
            panelHome.option7();
            panelHome.option8();
            panelHome.option9();

            assertEquals(Select_player_PanelHome.class,panelHome.getStatesPanelHome().getClass());/// SE ACTIVO EL PANEL
            panelHome.option1();
            assertEquals(Standly_mode_panelHome.class,panelHome.getStatesPanelHome().getClass());/// SE ACTIVO EL PANEL
            assertTrue(panelHome.unitExist(suguri));
        }
        else{
            assertEquals(Standly_mode_panelHome.class,panelHome.getStatesPanelHome().getClass());
            panelHome.option0();
            panelHome.option1();
            panelHome.option2();
            panelHome.option3();
            panelHome.option4();
            panelHome.option5();
            panelHome.option6();
            panelHome.option7();
            panelHome.option8();
            panelHome.option9();
            panelHome.rollDice();
        }
    }
    @Test
    void moveNotSuguriTest() throws IOException {
        assertEquals(Standly_mode_panelHome.class,panelHome.getStatesPanelHome().getClass());
        final long testSeed = new Random().nextLong();
        notSuguri.setSeed(testSeed);
        int i = new Random(testSeed).nextInt(6) + 1;
        notSuguri.play();
        if (i>1){
            assertNotEquals(Select_player_PanelHome.class,panelHome.getStatesPanelHome().getClass());
        }
    }
}
