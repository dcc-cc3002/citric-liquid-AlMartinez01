package cl.uchile.dcc.citricliquid;

import cl.uchile.dcc.citricliquid.model.controller.CreatePlayers;
import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.Combat;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts_ejm;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

public class Main {



    public static void main(String[] args) {
        int hp = 6; int def = 2; int evd = 1; int atk = 3;
        int starts = 50; int wins = 0; int norma = 1;


        /*
        Carts_ejm cart2 = new Carts_ejm("carta pruebas", "carta pruebas");
        UnitsPlayer unitsPlayer1 = new UnitsPlayer("Robert",hp,atk,def,evd,new Carts[] {cart2, cart2},null,starts,wins,norma);
        UnitsEnemy boss = new UnitsEnemy("Miguelito", 8, 3, 2, -1, true, 0);

        Combat combat = new Combat(unitsPlayer1,boss);

        combat.starter();


         */
        new CreatePlayers();
    }
}
