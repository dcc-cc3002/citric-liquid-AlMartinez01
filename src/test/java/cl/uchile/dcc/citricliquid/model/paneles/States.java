package cl.uchile.dcc.citricliquid.model.paneles;

import cl.uchile.dcc.citricliquid.model.paneles.StatesPanelHome.Standly_mode_panel;
import cl.uchile.dcc.citricliquid.model.paneles.StatesWithPlayers.Select_player_Panel;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Receive_damage_mode_player;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class States {
    Panel panel;
    UnitsPlayer player;
    UnitsPlayer player2;
    UnitsPlayer player3;
    private final static int BASE_HP = 40;
    private final static int BASE_ATK = 1;
    private final static int BASE_DEF = -1;
    private final static int BASE_EVD = 2;

    @BeforeEach
    void setUp() {
        panel = new Panel(null,null,null);
        player = new UnitsPlayer("suguri",BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD, null, null, 0, 0, 1);
        player2 = new UnitsPlayer("suguri2",BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD, null, null, 0, 0, 1);
        player3 = new UnitsPlayer("suguri3",BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD, null, null, 0, 0, 1);
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
}
