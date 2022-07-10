package cl.uchile.dcc.citricliquid.model.unidades;

import cl.uchile.dcc.citricliquid.model.paneles.Panel;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Standby_mode_Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatesPlayers {
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
    @Test
    void testState(){
        assertEquals(Standby_mode_Player.class,player.getStatesPlayer().getClass());
    }
}
