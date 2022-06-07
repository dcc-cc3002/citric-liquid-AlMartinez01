package cl.uchile.dcc.citricliquid.model.unidades;

import cl.uchile.dcc.citricliquid.model.abstracto.Units;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnitWildsTest {
    private UnitsEnemy will;
    private UnitsEnemy boss;

    @BeforeEach
    public void setUp(){
        will = new UnitsEnemy("pollo",3 ,-1, -1, +1,false,3);
        boss = new UnitsEnemy("store Manager",8,+3, +2, -1,true,0);
    }
    @Test
    public void isBossTest(){
        assertTrue(boss.isBoss());
        assertFalse(will.isBoss());
    }
    @Test
    public void getBagTest(){
        assertEquals(3,will.getBag());
        assertNotEquals(3,boss.getBag());
    }
    @Test
    public void setBagTest(){
        will.setBag(4);
        boss.setBag(6);
        assertEquals(4,will.getBag());
        assertEquals(6,boss.getBag());
    }
}
