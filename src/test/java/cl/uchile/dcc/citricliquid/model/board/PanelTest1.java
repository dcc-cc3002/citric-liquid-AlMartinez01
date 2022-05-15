package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.paneles.Panel;
import cl.uchile.dcc.citricliquid.model.paneles.PanelBonus;
import cl.uchile.dcc.citricliquid.model.paneles.PanelDrop;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PanelTest1 {
    private final static String PLAYER_NAME = "Suguri";
    private final static int BASE_HP = 4;
    private final static int BASE_ATK = 1;
    private final static int BASE_DEF = -1;
    private final static int BASE_EVD = 2;

    UnitsPlayer suguri;
    UnitsPlayer suguri2;
    UnitsPlayer suguri3;

    long testSeed;

    Panel panel1;
    Panel panel2;
    PanelBonus panelBonus;
    PanelDrop panelTestDrop;

    @BeforeEach
    public void setUp(){
        suguri = new UnitsPlayer("suguri", BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD,null,panel2,0,0,1);
        suguri2 = new UnitsPlayer("suguri1", BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD,null,panel1,0,0,1);
        suguri3 = new UnitsPlayer("suguri2", BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD,null,panel1,0,0,1);
        panel2 = new Panel(new UnitsPlayer[]{suguri},null,null);
        panel1 =new Panel(new UnitsPlayer[]{suguri2,suguri3}, new Panel[]{panel2},null);
        panelBonus = new PanelBonus(new UnitsPlayer[]{suguri}, new Panel[]{},null);
        panelTestDrop = new PanelDrop(new UnitsPlayer[]{suguri}, new Panel[]{},null);
        testSeed = new Random().nextLong();
    }
    @Test
    public void buildTest(){
        var  expectPanel = new Panel(new UnitsPlayer[]{suguri},null,null);
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
        int i = panel1.unitUbi(suguri3);
        int n = panel1.unitUbi(suguri2);
        int m = panel1.unitUbi(suguri);
        Assertions.assertEquals(1,i);
        Assertions.assertEquals(0,n);
        Assertions.assertEquals(-1,m); //si no esta, retornara -1
    }
    @RepeatedTest(100)
    public void BonusPanelConsistencyTest() {
        int expectedStars = 30;
        suguri.incrementStars(30);
        Assertions.assertEquals(expectedStars, suguri.getStars());
        final var testRandom = new Random(testSeed);
        suguri.setSeed(testSeed);
        int i = 1;
        for (int normaLvl = 1; normaLvl < 3; normaLvl++) {
            final int roll = (testRandom.nextInt(6) + 1);
            panelBonus.activator(suguri);
            //System.out.print("ROLL : " + roll +" vuelta "+ i +"\n strellas suguri: " +suguri.getStars() +" \n strellas spected: " + (expectedStars + roll * normaLvl) + "\n" );
            expectedStars = Math.max(expectedStars + (roll * normaLvl), 0);
            Assertions.assertEquals(expectedStars, suguri.getStars(),
                    "Test failed with seed: " + testSeed + "\n vuelta: " + i);
            suguri.setLvlNorma(suguri.getLvlNorma() + 1);
            i++;

        }
        for (int normaLvl = 3; normaLvl <= 6; normaLvl++) {
            final int roll = (testRandom.nextInt(6) + 1);
            panelBonus.activator(suguri);
            ///////////////
            //System.out.print("ROLL : " + roll +" vuelta "+ i +"\n strellas suguri: " +suguri.getStars() +" \n strellas spected: " + (expectedStars + roll * normaLvl) + "\n" );
            ///////////////
            expectedStars = Math.max(expectedStars + roll * 3, 0);
            Assertions.assertEquals(expectedStars, suguri.getStars(),
                    "Test failed with seed: " + testSeed+ "\n vuelta: " + i);
            suguri.setLvlNorma(suguri.getLvlNorma() + 1);
            i++;
        }
    }

    @RepeatedTest(100)
    public void droopPanelTest(){
        int expectedStars = 30;
        suguri.incrementStars(30);
        Assertions.assertEquals(expectedStars, suguri.getStars());
        final var testRandom = new Random(testSeed);
        suguri.setSeed(testSeed);
        int i = 1;
        for (int normaLvl = 1; normaLvl < 6; normaLvl++) {
            final int roll = (testRandom.nextInt(6) + 1);
            panelTestDrop.activator(suguri);
            expectedStars = Math.max(expectedStars - (roll * normaLvl), 0);
            //System.out.print("ROLL : " + roll +" vuelta "+ i +"\n strellas suguri: " +suguri.getStars() +" \n strellas spected: " + expectedStars + "\n" );
            Assertions.assertEquals(expectedStars, suguri.getStars(),
                    "Test failed with seed: " + testSeed + "\n vuelta: " + i);
            suguri.setLvlNorma(suguri.getLvlNorma() + 1);
            i++;

        }
    }

    @Test
    public void cantPanelTest(){
        var testpanel = new PanelBonus(null,new Panel[]{panel1,panel2},null);
        Assertions.assertEquals(1,panel1.cant_next());
        Assertions.assertEquals(0,panel2.cant_next());
        Assertions.assertEquals(2,testpanel.cant_next());
    }
    @Test
    public void agrePlayerTest(){
        var expectPanel = new Panel(new UnitsPlayer[]{suguri,suguri2},null,null);
        panel2.agrePlayer(suguri2);
        Assertions.assertEquals(expectPanel,panel2);
    } //TODOS LOS ACTIVADORES AGREGAN AL PLAYER A LAS UNIDADES EN ESE PANEL

    @Test
    public void avanzarTest() throws IOException {
        Assertions.assertEquals(panel2,panel1.avanzar(suguri2,1));
        var expected = new Panel(new UnitsPlayer[]{suguri},new Panel[]{panel1},null);
        var avaPanel =new Panel(new UnitsPlayer[]{},new Panel[]{panel1},null);
        var prueb = new Panel(new UnitsPlayer[]{suguri},new Panel[]{avaPanel},null);
        Panel ret = prueb.avanzar(suguri,1);
        Assertions.assertEquals(expected,ret);
    }

}
