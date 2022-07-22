package cl.uchile.dcc.citricliquid.model.Game;

import cl.uchile.dcc.citricliquid.model.controller.GameController;
import cl.uchile.dcc.citricliquid.model.paneles.*;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanels.StatesPanelHome.Select_player_PanelHome;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.*;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
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
        assertEquals(StandbyPanel.class,p1.getStatesPlayer().getClass());
        assertEquals(Standby_mode_Player.class,p2.getStatesPlayer().getClass());
        assertEquals(Select_player_PanelHome.class,panelHome.getStatesPanelHome().getClass());

        panelHome.option1();
        assertEquals(Standby_mode_Player.class,p1.getStatesPlayer().getClass());
        assertEquals(Play_mode_player.class,p2.getStatesPlayer().getClass());

    }

    @Test
    void initTurnWhitPanelHomeC() throws IOException {
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
        assertEquals(StandbyPanel.class,p1.getStatesPlayer().getClass());
        assertEquals(Standby_mode_Player.class,p2.getStatesPlayer().getClass());
        assertEquals(Select_player_PanelHome.class,panelHome.getStatesPanelHome().getClass());

        p1.option1();
        assertEquals(Standby_mode_Player.class,p1.getStatesPlayer().getClass());
        assertEquals(Play_mode_player.class,p2.getStatesPlayer().getClass());
        // similar pero en este se ocupara al jugador para controlar al panel
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
        p1.setLvlNorma(5);
        p1.setStars(1000);
        p1.setWins(1000);

        gameController.init_turn();
        p1.rollDice(); // El panel ahora debería esperar respuesta
        assertEquals(panelHome,p1.getUbi());
        assertEquals(StandbyPanel.class,p1.getStatesPlayer().getClass());
        assertEquals(5,p1.getLvlNorma());

        p1.option1();//El jugador debería ganar! y por ende todos deberian estar en modo espera
        assertEquals(6,p1.getLvlNorma());
        assertEquals(6,gameController.norma_maxima);
        //assertTrue(gameController.winner);
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

    @RepeatedTest(100)
    void TurnWithPlayerDead() throws IOException {
        long r = new Random().nextLong();
        p1.setSeed(r);

        panel1.unitPlayer(p1);
        panel1.unitPlayer(p2);
        panel1.addNextPanel(panel2);
        panel2.addNextPanel(panel3);
        panel3.addNextPanel(panel4);
        panel4.addNextPanel(panel5);
        panel5.addNextPanel(panel6);
        panel6.addNextPanel(panel7);
        panel7.addNextPanel(panel8);
        panel8.addNextPanel(panel9);
        panel9.addNextPanel(panel1);
        tablero = new Panel[]{panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8,panel9};
        gameController.addTablero(tablero);
        gameController.addPlayer(p1);
        gameController.addPlayer(p2);
        p1.setHpActual(0);
        p1.defeat();

        gameController.init_turn();
        assertEquals(KO_StatePlayerRoll.class,p1.getStatesPlayer().getClass());
        p1.rollDice();

        if (new Random(r).nextInt(6)+1 == 6){
            //Se cumple la condicion para revivir
            assertEquals(Play_mode_player.class,p1.getStatesPlayer().getClass());
            p1.rollDice();
            assertEquals(Standby_mode_Player.class,p1.getStatesPlayer().getClass());
        }
        else {
            assertEquals(KO_StatePlayer.class,p1.getStatesPlayer().getClass());//El jugador no saldra de su modo
        }
        assertEquals(Play_mode_player.class,p2.getStatesPlayer().getClass());//El siguiente jugador deberia poder jugar
    }

    @RepeatedTest(100)
    void ko_StateTestMultiTurns() throws IOException {
        long r = new Random().nextLong();
        Random random = new Random(r);
        p1.setSeed(r);

        panel1.unitPlayer(p1);
        panel1.unitPlayer(p2);
        panel1.addNextPanel(panel2);

        tablero = new Panel[]{panel1,panel2};
        gameController.addTablero(tablero);
        gameController.addPlayer(p1);
        p1.setHpActual(0);
        p1.defeat();

        gameController.init_turn();
        assertEquals(KO_StatePlayerRoll.class,p1.getStatesPlayer().getClass());

        int i = 6;
        while (i > random.nextInt(6)+1){
            p1.rollDice();
            assertEquals(KO_StatePlayerRoll.class,p1.getStatesPlayer().getClass());
            i--;
        }
        p1.rollDice();
        assertEquals(Play_mode_player.class,p1.getStatesPlayer().getClass());
    }

    @Test
    void createPanel() {
        var panelExpected = new Panel();
        var panelExpected1 = new PanelHome();
        var panelExpected2 = new Panelboss();
        var panelExpected3 = new PanelBonus();
        var panelExpected4 = new PanelEncounter();
        var panelExpected5 = new PanelDrawn();
        var panelExpected6 = new PanelDrop();

        assertEquals(panelExpected,gameController.createPanel());
        assertEquals(panelExpected1,gameController.createPanelHome());
        assertEquals(panelExpected2,gameController.createPanelBoss());
        assertEquals(panelExpected3,gameController.createPanelBonus());
        assertEquals(panelExpected4,gameController.createPanelEncounter());
        assertEquals(panelExpected5,gameController.createPanelDrawn());
        assertEquals(panelExpected6,gameController.createPanelDrop());

        panelExpected1.setHome(p1);

        var panel = gameController.createPanelHome();
        gameController.setPlayerPanelHome(p1,panel);
        assertEquals(panelExpected1,panel);
    }

    @Test
    void getRonda() {
        assertEquals(1,gameController.getRonda());
    }

    @Test
    void createPlayerTest(){
        var expectedSuguri = new UnitsPlayer("suguri",10,10,10,10);
        var expectedSuguri2 = new UnitsPlayer("suguri2",10,10,10,10);
        gameController.createAndIncludedPlayer("suguri",10,10,10,10);
        assertEquals(expectedSuguri,gameController.players[0]);
        gameController.createAndIncludedPlayer("suguri2",10,10,10,10);
        assertEquals(expectedSuguri,gameController.players[0]);
        assertEquals(expectedSuguri2,gameController.players[1]); //PERFECT FUNCIONO ASKAJSJA
    }
    @Test
    void createPathTest(){
        var panel1 = gameController.createPanel();
        var panel2 = gameController.createPanel();
        gameController.createTablero();
        assertEquals(0,gameController.tablero.length);
        gameController.createAndIncludedPlayer("suguri",10,3,4,3);
        gameController.addToTablero(panel1);
        gameController.addToTablero(panel2);
        assertEquals(panel1,gameController.tablero[0]);
        assertEquals(panel2,gameController.tablero[1]);

        gameController.generateNext(panel1,panel2);
        assertEquals(gameController.tablero[1],gameController.tablero[0].getNexts()[0]);

        gameController.addToTablero(gameController.createPanelHome());
        assertEquals(PanelHome.class,gameController.tablero[2].getClass());
        gameController.addPanelHomeWithPlayer(gameController.players[0], (PanelHome) gameController.tablero[2]);
        assertEquals(gameController.players[0],((PanelHome) gameController.tablero[2]).getHome()); //El jugador ahora es el dueño de esta casilla

        gameController.addPlayerToPanel(gameController.players[0],gameController.tablero[1]);
        assertEquals(gameController.players[0],gameController.tablero[1].getUnits()[0]);
        assertEquals(gameController.tablero[1],gameController.players[0].getUbi());
    }
    @Test
    void turnPlayerTest() throws IOException {
        gameController.createAndIncludedPlayer("suguri",10,10,10,10);
        gameController.createAndIncludedPlayer("suguri2",10,10,10,10);
        gameController.createTablero();
        gameController.addToTablero(gameController.createPanel());//0
        gameController.addToTablero(gameController.createPanel());//1
        gameController.addToTablero(gameController.createPanel());//2
        gameController.addToTablero(gameController.createPanel());//3
        gameController.addToTablero(gameController.createPanel());//4
        gameController.addToTablero(gameController.createPanel());//5
        gameController.addToTablero(gameController.createPanel());//6
        gameController.addToTablero(gameController.createPanel());//7
        gameController.addToTablero(gameController.createPanel());//8
        gameController.addToTablero(gameController.createPanelHome());//9
        gameController.addToTablero(gameController.createPanelBoss());//10
        gameController.generateNext(gameController.tablero[0],gameController.tablero[1] );
        gameController.generateNext(gameController.tablero[1],gameController.tablero[2] );
        gameController.generateNext(gameController.tablero[2],gameController.tablero[3] );
        gameController.generateNext(gameController.tablero[3],gameController.tablero[4] );
        gameController.generateNext(gameController.tablero[4],gameController.tablero[5] );
        gameController.generateNext(gameController.tablero[5],gameController.tablero[6] );
        gameController.generateNext(gameController.tablero[6],gameController.tablero[7] );
        gameController.generateNext(gameController.tablero[7],gameController.tablero[8] );
        gameController.generateNext(gameController.tablero[8],gameController.tablero[9] );
        gameController.generateNext(gameController.tablero[9],gameController.tablero[10] );
        gameController.generateNext(gameController.tablero[10],gameController.tablero[1] );
        gameController.associateTablero();
        gameController.addPlayerToPanel(gameController.players[0],gameController.tablero[0]);
        gameController.addPlayerToPanel(gameController.players[1],gameController.tablero[0]);
        gameController.init_turn();

        assertEquals(gameController.players[0],gameController.getPlayerInTurner());
        assertEquals(Play_mode_player.class,gameController.players[0].getStatesPlayer().getClass());

        //como los comandos dependen del jugador estos en este momento no deberia afectar en el progreso (creo)
        gameController.option0();
        gameController.option1();
        gameController.option2();
        gameController.option3();
        gameController.option4();
        gameController.option5();
        gameController.option6();
        gameController.option7();
        gameController.option8();
        gameController.option9();

        gameController.optionPlayerInTurn();
        gameController.optionRonda();
        gameController.rollDice();
        assertEquals(gameController.players[1],gameController.getPlayerInTurner());
        gameController.rollDice();
        gameController.optionPlayerInTurn();
        gameController.optionRonda();

    }
}
