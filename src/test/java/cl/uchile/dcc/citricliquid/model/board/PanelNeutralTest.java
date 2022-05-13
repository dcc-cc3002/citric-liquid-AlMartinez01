package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.paneles.Panel;
import cl.uchile.dcc.citricliquid.model.paneles.PanelNeutral;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PanelNeutralTest {
    private final static String PLAYER_NAME = "Suguri";
    private final static int BASE_HP = 4;
    private final static int BASE_ATK = 1;
    private final static int BASE_DEF = -1;
    private final static int BASE_EVD = 2;

    UnitsPlayer suguri;
    UnitsPlayer suguri2;
    UnitsPlayer suguri3;

    PanelNeutral panel1;
    PanelNeutral panel2;

    @BeforeEach
    public void setUp(){
        suguri = new UnitsPlayer("suguri", BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD,null,0,0,1);
        suguri2 = new UnitsPlayer("suguri1", BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD,null,0,0,1);
        suguri3 = new UnitsPlayer("suguri2", BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD,null,0,0,1);
        panel2 = new Panel(new UnitsPlayer[]{suguri},null,null);
        panel1 =new Panel(new UnitsPlayer[]{suguri2,suguri3}, new Panel[]{panel2},null);
    }
    @Test
    public void buildTest(){
        var  expectPanel = new PanelNeutral(new UnitsPlayer[]{suguri},null,null);
        Assertions.assertEquals(expectPanel,panel2);
        Assertions.assertNotEquals(expectPanel,panel1);
    }
    @Test
    public void existTest(){
        Assertions.assertTrue(panel1.unitExist(suguri2));
        Assertions.assertFalse(panel1.unitExist(suguri));
    }

    @Test
    public void ubicTest(){
        int i = panel1.unitUbi(suguri2);
        Assertions.assertEquals(1,i);
    }
}
