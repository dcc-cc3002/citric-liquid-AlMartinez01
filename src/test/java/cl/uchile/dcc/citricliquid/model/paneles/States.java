package cl.uchile.dcc.citricliquid.model.paneles;

import cl.uchile.dcc.citricliquid.model.paneles.StatesPanels.Select_player_Panel;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanels.Standly_mode_panel;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Receive_damage_mode_player;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class States {
    Panel panel;
    Panel panel2;
    Panel panel3;
    UnitsPlayer player;
    UnitsPlayer player2;
    UnitsPlayer player3;
    UnitsPlayer player4;
    UnitsPlayer player5;
    UnitsPlayer player6;
    UnitsPlayer player7;
    UnitsPlayer player8;
    UnitsPlayer player9;
    UnitsPlayer player10;
    UnitsPlayer player11;
    private final static int BASE_HP = 40;
    private final static int BASE_ATK = 1;
    private final static int BASE_DEF = -1;
    private final static int BASE_EVD = 2;

    @BeforeEach
    void setUp() {
        panel = new Panel(null,null,null);
        panel2 = new Panel();
        panel3 = new Panel();
        player = new UnitsPlayer("suguri",BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD, null, null, 0, 0, 1);
        player2 = new UnitsPlayer("suguri2",BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD, null, null, 0, 0, 1);
        player3 = new UnitsPlayer("suguri3",BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD, null, null, 0, 0, 1);
        player4 = new UnitsPlayer("suguri4",BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD, null, null, 0, 0, 1);
        player5 = new UnitsPlayer("suguri5",BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD, null, null, 0, 0, 1);
        player6 = new UnitsPlayer("suguri6",BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD, null, null, 0, 0, 1);
        player7 = new UnitsPlayer("suguri7",BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD, null, null, 0, 0, 1);
        player8 = new UnitsPlayer("suguri8",BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD, null, null, 0, 0, 1);
        player9 = new UnitsPlayer("suguri9",BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD, null, null, 0, 0, 1);
        player10 = new UnitsPlayer("suguri10",BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD, null, null, 0, 0, 1);
        player11 = new UnitsPlayer("suguri(10+1)",BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD, null, null, 0, 0, 1);
        panel.unitPlayer(player2);
        panel.unitPlayer(player3);
    }

    @Test
    void createTest() {
        panel.setStatesPanel(new Select_player_Panel(panel,player,2));
        assertEquals(Select_player_Panel.class,panel.statesPanel.getClass());
    }

    @Test
    void initCombatTest() throws IOException {
        panel.setStatesPanel(new Select_player_Panel(panel,player,2));
        panel.option0();
        assertEquals(Receive_damage_mode_player.class,player2.getStatesPlayer().getClass());
        player2.option0();
        assertEquals(Receive_damage_mode_player.class,player.getStatesPlayer().getClass());
    }

    @RepeatedTest(100)
    void greatChoice() throws IOException {
        panel.unitPlayer(player);

        panel2.unitPlayer(player2);
        panel2.unitPlayer(player3);
        panel2.unitPlayer(player4);
        panel2.unitPlayer(player5);
        panel2.unitPlayer(player6);
        panel2.unitPlayer(player7);
        panel2.unitPlayer(player8);
        panel2.unitPlayer(player9);
        panel2.unitPlayer(player10);
        panel2.unitPlayer(player11);

        panel.addNextPanel(panel2);
        panel2.addNextPanel(panel3);

        player.play();

        assertEquals(Select_player_Panel.class,panel2.statesPanel.getClass());

        int i = new Random().nextInt(10);
        switch (i){
            case 0 -> panel2.option0();
            case 1 -> panel2.option1();
            case 2 -> panel2.option2();
            case 3 -> panel2.option3();
            case 4 -> panel2.option4();
            case 5 -> panel2.option5();
            case 6 -> panel2.option6();
            case 7 -> panel2.option7();
            case 8 -> panel2.option8();
            case 9 -> panel2.option9();
        }
        assertEquals(Standly_mode_panel.class,panel2.statesPanel.getClass());
    }
}
