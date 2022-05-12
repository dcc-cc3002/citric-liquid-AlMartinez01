package cl.uchile.dcc.citricliquid.model;

import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnitsTest {

    private String NamePla = "ahhh";
    private UnitsPlayer sugur;

    @BeforeEach
    public void setUp() {
        sugur = new UnitsPlayer(NamePla, 1,1,2,3,4,null,2,0,1 );
    }

    @Test
    public void builders(){
        var expected = new UnitsPlayer(NamePla, 1,1,2,3,4,null,2,0,1 );
        Assertions.assertEquals(expected,sugur);
    }

}
