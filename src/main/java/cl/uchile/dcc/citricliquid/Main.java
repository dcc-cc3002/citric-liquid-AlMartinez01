package cl.uchile.dcc.citricliquid;

import cl.uchile.dcc.citricliquid.model.controller.GameController;
import cl.uchile.dcc.citricliquid.model.paneles.Panel;
import cl.uchile.dcc.citricliquid.model.paneles.PanelBonus;
import cl.uchile.dcc.citricliquid.model.paneles.PanelHome;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts_ejm;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

import java.io.IOException;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) throws IOException {
        int hp = 6; int def = 2; int evd = 1; int atk = 3;
        int starts = 50; int wins = 0; int norma = 1;

        GameController gameController = new GameController();

        Carts_ejm cart2 = new Carts_ejm("carta pruebas", "carta pruebas");
        UnitsPlayer unitsPlayer1 = new UnitsPlayer("Robert",hp,atk,def,evd,new Carts[] {cart2, cart2},null,starts,wins,norma);

        /* prueba de que funciona el avanzar en home
        Panel panel1;
        PanelHome panel2;
        Panel panel3;
        Panel panel4;
        Panel panel5;
        Panel panel6;

        panel1 = new Panel(null,null,null);
        panel2 = new PanelHome(null,null,null,null);
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


        panel1.unitPlayer(unitsPlayer1);

        unitsPlayer1.play();



        CreatePlayers createPlayers;
        createPlayers = new CreatePlayers(3);
        createPlayers.desordenar();
        */

    }
}
