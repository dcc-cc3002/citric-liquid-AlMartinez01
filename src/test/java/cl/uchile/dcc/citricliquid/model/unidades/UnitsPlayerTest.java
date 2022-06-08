package cl.uchile.dcc.citricliquid.model.unidades;

import cl.uchile.dcc.citricliquid.model.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.abstracto.Carts_ejm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnitsPlayerTest {

    private UnitsPlayer player;
    int hp = 6; int def = 2; int evd = 1; int atk = 3;
    int starts = 0; int wins = 0; int norma = 1;

    @BeforeEach
    public void setUp(){
        String name_carts = "name_cart";
        Carts carts = new Carts_ejm(name_carts, name_carts);
        String name = "name_pruebas";
        player = new UnitsPlayer(name,hp,atk,def,evd,new Carts[] {carts, carts},null,starts,wins,norma);
    }

    @Test
    void gets() {
        Assertions.assertEquals(hp,player.getHpMax());
        Assertions.assertEquals(def,player.getDef());
        Assertions.assertEquals(evd,player.getEvd());
        Assertions.assertEquals(atk,player.getAtk());
        Assertions.assertEquals(starts,player.getStars());
        Assertions.assertEquals(wins,player.getWins());
        Assertions.assertEquals(norma,player.getLvlNorma());
    }

    @Test
    void lootTest() {
        player.setStars(100);
        final var expected = 50;
        Assertions.assertEquals(expected,player.loot());
        Assertions.assertEquals(expected,player.getStars());
    }

    @Test
    void cant_cartsTest() {
        var cart = new Carts_ejm(null,null);
        player.setMano(new Carts[]{cart,cart,cart});
        Assertions.assertEquals(3,player.cant_carts());
    }

    @Test
    void view_cartsTest() {
        var cart = new Carts_ejm(null,null);
        player.setMano(new Carts[]{cart,cart,cart});
        player.view_carts();

        /**
         * Cartas disponibles:
         *  Carts{name='null', Characters='null'} 0|
         *  Carts{name='null', Characters='null'} 1|
         *  Carts{name='null', Characters='null'} 2|
         */
    }

    @Test
    void deletedCartTest() {
        var cart = new Carts_ejm(null,null);
        var cart2 = new Carts_ejm("null",null);
        player.setMano(new Carts[]{cart,cart,cart});
        player.deleteCart(3);
        Assertions.assertEquals(3,player.cant_carts());
        player.deleteCart(2);
        Assertions.assertEquals(2,player.cant_carts());
    }
}