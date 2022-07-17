package cl.uchile.dcc.citricliquid.model.Game;

import cl.uchile.dcc.citricliquid.model.controller.GameController;
import cl.uchile.dcc.citricliquid.model.paneles.*;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanelHome.Select_player_PanelHome;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Play_mode_player;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Receive_damage_mode_player;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Standby_mode_Player;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameControllerTest {
    GameController gameController;
    Panel panel1;
    Panel panel2;
    Panel panel3;
    Panel panel4;
    Panel panel5;
    Panel panel6;
    Panel panel7;
    Panel panel8;
    Panel panel9;
    PanelHome panelHome;
    PanelEncounter panelEncounter;
    Panel[] tablero;
    UnitsPlayer p1;
    UnitsPlayer p2;
    Panelboss panelBoss;
    int hp = 6; int def = 2; int evd = 1; int atk = 3;
    int starts = 0; int wins = 0; int norma = 1;

    @BeforeEach
    void setUp(){
        gameController = new GameController();
        panel1 = new Panel(null,null,null);
        panel2 = new Panel(null,null,null);
        panel3 = new Panel(null,null,null);
        panel4 = new Panel(null,null,null);
        panel5 = new Panel(null,null,null);
        panel6 = new Panel(null,null,null);
        panel7 = new Panel(null,null,null);
        panel8 = new Panel(null,null,null);
        panel9 = new Panel(null,null,null);
        tablero = new Panel[]{panel1,panel2,panel3};
        p1 = new UnitsPlayer("p1",hp,atk,def,evd,new Carts[] {},null,starts,wins,norma);
        p2 = new UnitsPlayer("p2",hp,atk,def,evd,new Carts[] {},null,starts,wins,norma);
        panelHome = new PanelHome(null,null,null,p1);
        panelBoss = new Panelboss(null,null,null,null,null);
        panelEncounter = new PanelEncounter(null,null,null,null);
    }

    @Test
    void constructorTest(){
        var expectedGameController = new GameController();
        assertEquals(expectedGameController,gameController);
    }

    @Test
    void addPanelTest(){
        tablero = new Panel[] {panelEncounter,panelBoss,panel3,panel2,panel1,panelHome};
        gameController.addTablero(tablero);
        assertEquals(tablero,gameController.tablero);
        for (Panel panel:tablero){
            assertEquals(gameController,panel.getObserverEvent());
            if (panel.getClass() == PanelHome.class){
                assertEquals(gameController,((PanelHome) panel).getObserver());
            }
        }
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
        tablero = new Panel[]{panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8,panel9};
        gameController.addTablero(tablero);
        gameController.addPlayer(p1);
        gameController.addPlayer(p2);
        panel1.unitPlayer(p1);
        panel8.unitPlayer(p2);
        panel1.addNextPanel(panel2);
        panel2.addNextPanel(panel3);
        panel3.addNextPanel(panel4);
        panel4.addNextPanel(panel5);
        panel5.addNextPanel(panel6);
        panel6.addNextPanel(panel7);
        panel7.addNextPanel(panel8);
        panel8.addNextPanel(panel9);
        panel9.addNextPanel(panel1);        assertEquals(Standby_mode_Player.class,p1.getStatesPlayer().getClass());
        assertEquals(Standby_mode_Player.class,p2.getStatesPlayer().getClass());

        gameController.init_turn();
        assertEquals(Play_mode_player.class,p1.getStatesPlayer().getClass());
        assertEquals(Standby_mode_Player.class,p2.getStatesPlayer().getClass());

        p1.rollDice();
        assertEquals(Standby_mode_Player.class,p1.getStatesPlayer().getClass());
        assertEquals(Play_mode_player.class,p2.getStatesPlayer().getClass());

    }

    @Test
    void initTurnWhitPanelHome() throws IOException {
        tablero = new Panel[]{panel1,panel2,panel3,panelHome};
        gameController.addTablero(tablero);
        gameController.addPlayer(p1);
        gameController.addPlayer(p2);
        panel1.unitPlayer(p1);
        panel2.unitPlayer(p2);
        panel1.addNextPanel(panelHome);
        panelHome.addNextPanel(panel2);
        panel2.addNextPanel(panel3);
        panel3.addNextPanel(panel1);

        gameController.init_turn(); // El jugador 1 debería entrar en consulta si tirar dado

        p1.rollDice(); // El panel ahora debería esperar respuesta
        assertEquals(Standby_mode_Player.class,p1.getStatesPlayer().getClass());
        assertEquals(Standby_mode_Player.class,p2.getStatesPlayer().getClass());
        assertEquals(Select_player_PanelHome.class,panelHome.getStatesPanelHome().getClass());

        panelHome.option1();
        assertEquals(Standby_mode_Player.class,p1.getStatesPlayer().getClass());
        assertEquals(Play_mode_player.class,p2.getStatesPlayer().getClass());

        p2.rollDice();
        assertEquals(Play_mode_player.class,p1.getStatesPlayer().getClass());
        assertEquals(Standby_mode_Player.class,p2.getStatesPlayer().getClass());
    }

    @Test
    void winnerTest() throws IOException {
        tablero = new Panel[]{panel1,panel2,panel3,panelHome};
        gameController.addTablero(tablero);
        gameController.addPlayer(p1);
        gameController.addPlayer(p2);
        panel1.unitPlayer(p1);
        panel2.unitPlayer(p2);
        panel1.addNextPanel(panelHome);
        panelHome.addNextPanel(panel2);
        panel2.addNextPanel(panel3);
        panel3.addNextPanel(panel1);
        p1.setLvlNorma(4);
        p1.setStars(1000);
        p1.setWins(1000);

        gameController.init_turn();
        p1.rollDice(); // El panel ahora debería esperar respuesta

        panelHome.option1();//El jugador debería ganar! y por ende todos deberian estar en modo espera
        assertTrue(gameController.winner);
        assertEquals(Standby_mode_Player.class,p1.getStatesPlayer().getClass());
        assertEquals(Standby_mode_Player.class,p2.getStatesPlayer().getClass());
    }

    @RepeatedTest(100)
    void bossGame() {
        int random = new Random().nextInt();
        gameController = new GameController(random);
        int i = new Random(random).nextInt(3);
        UnitsEnemy bossGame = null;
        int[] boss0=new int[]{8,3,2,-1};
        int[] boss1=new int[]{7,2,3,-2};
        int[] boss2=new int[]{10,2,1,-3};
        switch (i){
            case 0 -> bossGame = new UnitsEnemy("Store Manager",boss0[0],boss0[1],boss0[2],boss0[3],true,0);
            case 1 -> bossGame = new UnitsEnemy("Shifu Robot",boss1[0],boss1[1],boss1[2],boss1[3],true,0);
            case 2 -> bossGame = new UnitsEnemy("Flying Castle",boss2[0],boss2[1],boss2[2],boss2[3],true,0);
        }
        assertEquals(bossGame,gameController.bossGame);

        //Se puede ver que el panelBoss tiene de Boss al de la partida en general
        tablero = new Panel[]{panelHome,panel1,panel2,panelBoss};
        gameController.addTablero(tablero);
        assertEquals(bossGame,panelBoss.getBoss_actual());
        gameController.bossGame.setHpActual(1);
        assertEquals(gameController.bossGame,panelBoss.getBoss_actual());

    }

    @RepeatedTest(100)
    void EncounterPanel() throws IOException {
        tablero = new Panel[]{panel1,panelEncounter};
        gameController.addTablero(tablero);
        gameController.addPlayer(p1);
        panel1.unitPlayer(p1);
        panel1.addNextPanel(panelEncounter);
        panelEncounter.addNextPanel(panel1);
        panelEncounter.setEnemy_actual(new UnitsEnemy("chicken", 300, -1, -1, +1,false,0));
        assertEquals(UnitsEnemy.class,panelEncounter.getEnemy_actual().getClass());
        assertEquals(gameController,panelEncounter.getObserverEvent());

        final long seedPlayer = new Random().nextLong();

        Random random = new Random(seedPlayer);
        int expectedRoll = random.nextInt(6)+1;
        boolean b = !(expectedRoll == 1 || expectedRoll == 3 || expectedRoll == 5);
        if (b) { //aseguramos que el jugador caiga en encounterPanel
            return;
        }
        p1.setSeed(seedPlayer);

        gameController.init_turn();
        assertEquals(Play_mode_player.class,p1.getStatesPlayer().getClass());
        p1.rollDice();
        assertEquals(Receive_damage_mode_player.class,p1.getStatesPlayer().getClass()); //ASEGURAMOS QUE SE INICIO CORRECTAMENTE ESTO
        p1.option0();//CON ESTO EL COMBATE TERMINA
        assertEquals(Play_mode_player.class,p1.getStatesPlayer().getClass());//COMO ES EL UNICO JUGADOR VOLVERA AL MODO DE PLAY MODE
    }

    @RepeatedTest(100)
    void BossPanel() throws IOException {
        tablero = new Panel[]{panel1,panelBoss};
        gameController.addTablero(tablero);
        gameController.addPlayer(p1);
        panel1.unitPlayer(p1);
        panel1.addNextPanel(panelBoss);
        panelBoss.addNextPanel(panel1);
        panelBoss.setEnemy_actual(new UnitsEnemy("chicken", 300, -1, -1, +1,false,0));
        assertEquals(UnitsEnemy.class,panelBoss.getEnemy_actual().getClass());
        assertEquals(gameController,panelBoss.getObserverEvent());

        final long seedPlayer = new Random().nextLong();

        Random random = new Random(seedPlayer);
        int expectedRoll = random.nextInt(6)+1;
        boolean b = !(expectedRoll == 1 || expectedRoll == 3 || expectedRoll == 5);
        if (b) { //aseguramos que el jugador caiga en encounterPanel
            return;
        }
        p1.setSeed(seedPlayer);

        gameController.init_turn();
        assertEquals(Play_mode_player.class,p1.getStatesPlayer().getClass());
        p1.rollDice();
        assertEquals(Receive_damage_mode_player.class,p1.getStatesPlayer().getClass()); //ASEGURAMOS QUE SE INICIO CORRECTAMENTE ESTO
        p1.option0();//CON ESTO EL COMBATE TERMINA
        assertEquals(Play_mode_player.class,p1.getStatesPlayer().getClass());//COMO ES EL UNICO JUGADOR VOLVERA AL MODO DE PLAY MODE
    }
}
