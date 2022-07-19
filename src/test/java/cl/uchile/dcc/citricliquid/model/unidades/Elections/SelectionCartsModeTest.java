package cl.uchile.dcc.citricliquid.model.unidades.Elections;

import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Selection_cart_mode_player;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Standby_mode_Player;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts_ejm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SelectionCartsModeTest {
    UnitsPlayer player;
    UnitsPlayer player2;
    Carts_ejm carts1;
    Carts_ejm carts2;
    Carts_ejm carts3;
    Carts_ejm carts4;
    Carts_ejm carts5;
    Carts_ejm carts6;
    Carts_ejm carts7;
    Carts_ejm carts8;
    Carts_ejm carts9;
    Carts_ejm carts10;
    int hp = 6; int def = 2; int evd = 1; int atk = 3;
    int starts = 0; int wins = 0; int norma = 1;

    @BeforeEach
    public void setUp(){
        carts1 = new Carts_ejm("1","1");
        carts2 = new Carts_ejm("2","1");
        carts3 = new Carts_ejm("3","1");
        carts4 = new Carts_ejm("4","1");
        carts5 = new Carts_ejm("5","1");
        carts6 = new Carts_ejm("6","1");
        carts7 = new Carts_ejm("7","1");
        carts8 = new Carts_ejm("8","1");
        carts9 = new Carts_ejm("9","1");
        carts10 = new Carts_ejm("10","1");
        player = new UnitsPlayer("jamon",hp,atk,def,evd,new Carts[] {carts1, carts2,carts3,carts4,carts5,carts6,carts7,carts8,carts9,carts10},null,starts,wins,norma);
        player2 = new UnitsPlayer("jamon",hp,atk,def,evd,new Carts[] {},null,starts,wins,norma);
    }

    @Test
    public void selectionCartModeTest() throws IOException {
        assertEquals(Standby_mode_Player.class,player.getStatesPlayer().getClass());
        player.initio_combat();
        assertEquals(Selection_cart_mode_player.class,player.getStatesPlayer().getClass());
        //VERIFICAMOS QUE PLAYER INICIA SU MODO DE SELECCION DE CARTA

        player2.initio_combat();
        assertEquals(Standby_mode_Player.class,player2.getStatesPlayer().getClass()); //COMO PLAYER 2 NO TIENE CARTAS NO INICIA EL MODO

        player.rollDice();
        assertEquals(Standby_mode_Player.class,player.getStatesPlayer().getClass());
        //CON ESTA OPCION EVITAMOS ELEGIR CARTA
        assertEquals(10,player.cant_carts());

        player.initio_combat();
        player.option0();
        //SACAMOS LA CARTA 0 (carts1)
        assertNotEquals(carts1,player.getMano()[0]);//AHORA CARTA 0 NO ESTA EN

        player.initio_combat();
        player.option1();

        player.setMano(new Carts[] {carts1, carts2,carts3,carts4,carts5,carts6,carts7,carts8,carts9,carts10});//LA RE AGREGAMOS Y VERIFICAMOS SE PODEMOS SACAR TODAS LAS CARTAS


        player.initio_combat();
        player.getStatesPlayer().activeState();
        player.option9();
        player.initio_combat();
        player.option8();
        player.initio_combat();
        player.option7();
        player.initio_combat();
        player.option6();
        player.initio_combat();
        player.option5();
        player.initio_combat();
        player.option4();
        player.initio_combat();
        player.option3();
        player.initio_combat();
        player.option2();
        player.initio_combat();
        player.option1();
        player.initio_combat();
        player.option0();

        assertEquals(0,player.cant_carts());

    }
}
