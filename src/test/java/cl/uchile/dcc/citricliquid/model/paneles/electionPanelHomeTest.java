package cl.uchile.dcc.citricliquid.model.paneles;

import cl.uchile.dcc.citricliquid.model.controller.GameController;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        panel1.agrePlayer(suguri);
        panel1.agrePlayer(notSuguri);
    }

    @Test
    void avanzar()
}
