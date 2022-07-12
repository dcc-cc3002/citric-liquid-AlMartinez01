package cl.uchile.dcc.citricliquid.model.Carts;

import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts_ejm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartsTest {
    private Carts carts;

    @BeforeEach
    void setUp() {
        carts = new Carts_ejm("name","character");
    }

    @Test
    void toStringTestCarts() {
        var expectToString = "Carts{name='name\', Characters='character\'}";
        assertEquals(expectToString,carts.toString());
    }
}
