package cl.uchile.dcc.citricliquid.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnitsTest {
    private final static String PLAYER_NAME = "Suguri";
    private UnitsPlayer suguri;

    @BeforeEach
    public void setUp() {
        suguri = new UnitsPlayer(PLAYER_NAME, 4, 1, -1, 2,1,0,0);
    }

    @Test
    public void constructorTest() {
        final var expectedSuguri = new UnitsPlayer(PLAYER_NAME, 4, 1, -1, 2,1,0,0);
        Assertions.assertEquals(expectedSuguri, suguri);
    }
}
