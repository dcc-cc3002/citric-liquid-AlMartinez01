package cl.uchile.dcc.citricliquid.model.Game;

import cl.uchile.dcc.citricliquid.model.controller.GameController;
import cl.uchile.dcc.citricliquid.model.paneles.Panel;
import cl.uchile.dcc.citricliquid.model.paneles.PanelDrop;
import cl.uchile.dcc.citricliquid.model.paneles.Panelboss;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Play_mode_player;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Standby_mode_Player;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameControllerTest {
    GameController gameController;
    Panel panel1;
    Panel panel2;
    Panel panel3;
    Panel[] tablero;
    UnitsPlayer p1;
    UnitsPlayer p2;
    int hp = 6; int def = 2; int evd = 1; int atk = 3;
    int starts = 0; int wins = 0; int norma = 1;

    @BeforeEach
    void setUp(){
        gameController = new GameController();
        panel1 = new Panel(null,null,null);
        panel2 = new Panel(null,null,null);
        panel3 = new Panel(null,null,null);
        tablero = new Panel[]{panel1,panel2,panel3};
        p1 = new UnitsPlayer("p1",hp,atk,def,evd,new Carts[] {},null,starts,wins,norma);
        p2 = new UnitsPlayer("p2",hp,atk,def,evd,new Carts[] {},null,starts,wins,norma);
        panel1.addNextPanel(panel2);
        panel2.addNextPanel(panel3);
        panel3.addNextPanel(panel1);
    }

    @Test
    void constructorTest(){
        var expectedGameController = new GameController();
        assertEquals(expectedGameController,gameController);
    }

    @Test
    void addPanelTest(){
        gameController.addTablero(tablero);
        assertEquals(tablero,gameController.tablero);
    }

    @Test
    void addPlayerTest(){
        gameController.addPlayer(p1);
        var expected1 =new UnitsPlayer[]{p1};
        assertEquals(expected1[0], gameController.players[0]);
        gameController.addPlayer(p2);
        var expected2 = new UnitsPlayer[]{p1,p2};
        assertEquals(expected2[0], gameController.players[0]);
        assertEquals(expected2[1], gameController.players[1]);
    }

    @Test
    void initTurn() throws IOException {
        gameController.addTablero(tablero);
        gameController.addPlayer(p1);
        gameController.addPlayer(p2);
        panel1.unitPlayer(p1);
        panel2.unitPlayer(p2);


        assertEquals(Standby_mode_Player.class,p1.getStatesPlayer().getClass());
        assertEquals(Standby_mode_Player.class,p2.getStatesPlayer().getClass());

        gameController.init_turn();
        assertEquals(Play_mode_player.class,p1.getStatesPlayer().getClass());
        assertEquals(Standby_mode_Player.class,p2.getStatesPlayer().getClass());

        p1.rollDice();
        assertEquals(Standby_mode_Player.class,p1.getStatesPlayer().getClass());
        assertEquals(Play_mode_player.class,p2.getStatesPlayer().getClass());

        p2.rollDice();
        assertEquals(Play_mode_player.class,p1.getStatesPlayer().getClass());
        assertEquals(Standby_mode_Player.class,p2.getStatesPlayer().getClass());
    }
}
