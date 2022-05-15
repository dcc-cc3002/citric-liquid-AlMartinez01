package cl.uchile.dcc.citricliquid.model;

import cl.uchile.dcc.citricliquid.model.abstracto.Carts_ejm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Carts_ejmTest {
    Carts_ejm cart1;
    Carts_ejm cart2;
    String name_cart1 = "Carta1";
    String name_cart2 = "Carta2";

    @BeforeEach
    public void setUp(){
        cart1 = new Carts_ejm(name_cart1,name_cart1);
        cart2 = new Carts_ejm(name_cart2,name_cart2);
    }

    @Test
    public void constructTest(){
        var expectCart = new Carts_ejm(name_cart1,name_cart1);
        Assertions.assertEquals(expectCart,cart1);
        Assertions.assertNotEquals(expectCart,cart2);
    }

    @Test
    public void equalTest(){
        var expectCart = new Carts_ejm(name_cart1,name_cart1);
        Assertions.assertTrue(cart1.equals(expectCart));
        Assertions.assertFalse(cart2.equals(expectCart));
    }
}