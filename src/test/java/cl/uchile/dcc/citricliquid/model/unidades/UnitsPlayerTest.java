package cl.uchile.dcc.citricliquid.model.unidades;

import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts_ejm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        assertEquals(hp,player.getHpMax());
        assertEquals(def,player.getDef());
        assertEquals(evd,player.getEvd());
        assertEquals(atk,player.getAtk());
        assertEquals(starts,player.getStars());
        assertEquals(wins,player.getWins());
        assertEquals(norma,player.getLvlNorma());
    }

    @Test
    void lootTest() {
        player.setStars(100);
        final var expected = 50;
        assertEquals(expected,player.loot());
        assertEquals(expected,player.getStars());
    }

    @Test
    void cant_cartsTest() {
        var cart = new Carts_ejm(null,null);
        player.setMano(new Carts[]{cart,cart,cart});
        assertEquals(3,player.cant_carts());
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
        var cart2 = new Carts_ejm("maradona",null);
        player.setMano(new Carts[]{cart,cart2,cart});
        player.deleteCart(3);
        assertEquals(3,player.cant_carts());
        player.deleteCart(2);
        assertEquals(2,player.cant_carts());
        assertEquals(cart2,player.getMano()[1]);
        assertEquals(cart,player.getMano()[0]);
    }
    @Test
    void addCartTest(){
        var cart = new Carts_ejm(null,null);
        var cart2 = new Carts_ejm("maradona",null);
        player.setMano(null);
        player.add_cart(cart);
        assertEquals(1,player.cant_carts());
        assertEquals(cart,player.getMano()[0]);
        assertNotEquals(cart2,player.getMano()[0]);
        player.add_cart(cart2);
        assertEquals(2,player.cant_carts());
        assertEquals(cart,player.getMano()[0]);
        assertEquals(cart2,player.getMano()[1]);
    }
}