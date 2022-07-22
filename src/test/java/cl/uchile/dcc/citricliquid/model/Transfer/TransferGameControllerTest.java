package cl.uchile.dcc.citricliquid.model.Transfer;

import cl.uchile.dcc.citricliquid.model.controller.GameController;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.Observer;
import cl.uchile.dcc.citricliquid.model.paneles.Panel;
import cl.uchile.dcc.citricliquid.model.paneles.PanelBonus;
import cl.uchile.dcc.citricliquid.model.paneles.PanelHome;
import cl.uchile.dcc.citricliquid.model.paneles.Panelboss;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.StandbyPanel;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TransferGameControllerTest {
    Panelboss panel;
    PanelHome panelHome;
    Panel[] tablero;
    GameController gameController;

    private UnitsPlayer sugur;
    private UnitsPlayer sugur2;

    UnitsPlayer[] players;

    @BeforeEach
    void setUp(){
        gameController = new GameController();
        panel = new Panelboss(null,null,null,null,null);
        tablero = new Panel[]{panel};
        gameController.addTablero(tablero);
        sugur = new UnitsPlayer("sugurLvl1", 6, 2, 3, 4, new Carts[]{}, null, 10, 0, 1);
        sugur2 = new UnitsPlayer("sugurLvl2", 6, 2, 3, 4, new Carts[]{}, null, 30, 2, 2);
        panelHome = new PanelHome(new UnitsPlayer[]{sugur,sugur2},null,null,null);
        players = new UnitsPlayer[]{sugur,sugur2};
    }

    @Test
    void attachTestgameController(){
        gameController.attach(panel);
        gameController.notifier();
        assertTrue(panel.getBoss());
        var panelnew = new Panelboss(null,null,null,null,null);
        gameController.attach(panelnew);
        gameController.notifier();
        assertTrue(panel.getBoss());
        assertTrue(panelnew.getBoss());
    }

    @Test
    void transmisicionDeDatosSeguidosTest() throws IOException {
        panelHome.agrePlayer(sugur);
        panelHome.agrePlayer(sugur2);
        gameController.addPlayer(sugur);
        gameController.addPlayer(sugur2);
        gameController.attach(panel);
        panelHome.addNextPanel(panel);
        panelHome.attach(gameController);
        panelHome.activator(sugur);
        sugur.option0();
        assertEquals(2,sugur.getLvlNorma());//ALGUIEN SUBIO A NORMA2
        assertFalse(panel.getBoss());//PERO NO ES SUFICIENTE PARA ACTIVAR LOS PANELES BOSS
        assertEquals(2,gameController.norma_maxima);//SE DEBERIA GUARDAR ESTA NUEVA MAX EN GAMECONTROLLER
        panelHome.activator(sugur2);//ALGUIEN SUBIO A NORMA 3
        assertEquals(3,sugur2.getLvlNorma());
        assertEquals(3,gameController.norma_maxima);//SE DEBERIA GUARDAR ESTA NUEVA MAX EN GAMECONTROLLER
        assertTrue(panel.getBoss());//ES SUFICIENTE PARA CAMBIAR!!
    }




}
