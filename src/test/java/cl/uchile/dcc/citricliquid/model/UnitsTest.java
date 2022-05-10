package cl.uchile.dcc.citricliquid.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnitsTest {
    private UnitsPlayer suguri;

    @BeforeEach
    public void setUp() {
        suguri = new UnitsPlayer("alvaro", 4, 1, -1, 2,1,0,0);
    }

    @Test
    void constructores() {
        final var expected = new UnitsPlayer("alvaro",4,1,-1,2,1,0,0);
        Assertions.assertEquals(expected,suguri);
    }

    @Test
    public void constructorTest() {
        final var expectedSuguri = new UnitsPlayer("alvaro", 4, 1, -1, 2,1,0,0);
        System.out.print(suguri.toString() + "\n");
        System.out.print(expectedSuguri.toString() +"\n");
        Assertions.assertEquals(expectedSuguri, suguri);
    }


}
