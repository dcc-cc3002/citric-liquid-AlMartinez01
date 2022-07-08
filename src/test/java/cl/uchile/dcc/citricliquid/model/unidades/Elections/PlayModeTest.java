package cl.uchile.dcc.citricliquid.model.board.Elections;

import cl.uchile.dcc.citricliquid.model.paneles.Panel;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Play_mode_player;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PlayModeTest {
    private final static String PLAYER_NAME = "player";
    private final static int BASE_HP = 4;
    private final static int BASE_ATK = 1;
    private final static int BASE_DEF = -1;
    private final static int BASE_EVD = 2;
    //PLAYER//
    private UnitsPlayer player;
    //PANELES//
    private Panel panel1;
    private Panel panel2;
    private Panel panel3;
    private Panel panel4;
    private Panel panel5;
    private Panel panel6;
    @BeforeEach
    public void setUp(){
        player = new UnitsPlayer(PLAYER_NAME,BASE_HP,BASE_ATK,BASE_DEF,BASE_EVD,null,null,0,0,1);

        panel1 = new Panel(null,null,null);
        panel2 = new Panel(null,null,null);
        panel3 = new Panel(null,null,null);
        panel4 = new Panel(null,null,null);
        panel5 = new Panel(null,null,null);
        panel6 = new Panel(null,null,null);

        //SE CREA UN CIRCUITO DE LOS PANELES
        panel1.addNextPanel(panel2);
        panel2.addNextPanel(panel3);
        panel3.addNextPanel(panel4);
        panel4.addNextPanel(panel5);
        panel5.addNextPanel(panel6);
        panel6.addNextPanel(panel1);

        //SE UNE UN PANEL CON UN PLAYER
        panel1.unitPlayer(player);
    }
    @RepeatedTest(10)
    void playPlayer_In_StanbyMode() throws IOException {
        player.rollDice();
        assertEquals(panel1,player.getUbi());
    }

    @RepeatedTest(100)
    void playPlayer_In_PlayMode() throws IOException {
        player.initTurn();

        final long testSeed = new Random().nextLong();
        player.setSeed(testSeed);
        int i = new Random(testSeed).nextInt(6) + 1;

        player.rollDice();

        if (i == 1){
            assertTrue(panel2.unitExist(player));
            assertEquals(panel2,player.getUbi());
            assertFalse(panel1.unitExist(player));
            assertFalse(panel3.unitExist(player));
            assertFalse(panel4.unitExist(player));
            assertFalse(panel5.unitExist(player));
            assertFalse(panel6.unitExist(player));
        }
        else if (i == 2){
            assertTrue(panel3.unitExist(player));
            assertEquals(panel3,player.getUbi());
            assertFalse(panel1.unitExist(player));
            assertFalse(panel2.unitExist(player));
            assertFalse(panel4.unitExist(player));
            assertFalse(panel5.unitExist(player));
            assertFalse(panel6.unitExist(player));
        }
        else if (i == 3){
            assertTrue(panel4.unitExist(player));
            assertEquals(panel4,player.getUbi());
            assertFalse(panel1.unitExist(player));
            assertFalse(panel2.unitExist(player));
            assertFalse(panel3.unitExist(player));
            assertFalse(panel5.unitExist(player));
            assertFalse(panel6.unitExist(player));
        }
        else if (i == 4){
            assertTrue(panel5.unitExist(player));
            assertEquals(panel5,player.getUbi());
            assertFalse(panel1.unitExist(player));
            assertFalse(panel2.unitExist(player));
            assertFalse(panel3.unitExist(player));
            assertFalse(panel4.unitExist(player));
            assertFalse(panel6.unitExist(player));
        }
        else if (i == 5){
            assertTrue(panel6.unitExist(player));
            assertEquals(panel6,player.getUbi());
            assertFalse(panel1.unitExist(player));
            assertFalse(panel2.unitExist(player));
            assertFalse(panel3.unitExist(player));
            assertFalse(panel4.unitExist(player));
            assertFalse(panel5.unitExist(player));
        }
        else {
            assertTrue(panel1.unitExist(player));
            assertEquals(panel1,player.getUbi());
            assertFalse(panel6.unitExist(player));
            assertFalse(panel2.unitExist(player));
            assertFalse(panel3.unitExist(player));
            assertFalse(panel4.unitExist(player));
            assertFalse(panel5.unitExist(player));
        }
    }
}
