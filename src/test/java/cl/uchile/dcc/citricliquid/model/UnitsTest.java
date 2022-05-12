package cl.uchile.dcc.citricliquid.model;

import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class UnitsTest {

    private String NamePla = "ahhhhhhhhhhhhh";
    private String name_wild = "chicken";
    private String name_boss = "Store Manager";
    private UnitsPlayer sugur;
    private UnitsEnemy wild;
    private UnitsEnemy boss;

    @BeforeEach
    public void setUp() {
        sugur = new UnitsPlayer(NamePla, 6,2,3,4,null,2,0,1 );
        wild = new UnitsEnemy(name_wild,3,-1,-1,1,false,0);
        boss = new UnitsEnemy(name_boss,8,3,2,-1,true,0);
    }

    @Test
    public void builders(){
        var expected = new UnitsPlayer(NamePla, 6,2,3,4,null,2,0,1 );
        Assertions.assertEquals(expected,sugur);

        var expected_wild = new UnitsEnemy(name_wild,3,-1,-1,1,false,0);
        Assertions.assertEquals(expected_wild,wild);


        var expected_boss = new UnitsEnemy(name_boss,8,3,2,-1,true,0);
        Assertions.assertEquals(expected_boss,boss);
    }
    @Test
    public void equalTest(){
        final var o = new Object();
        Assertions.assertNotEquals(sugur, o);
        Assertions.assertEquals(sugur, sugur);
        final var expectedSuguri = new UnitsPlayer(NamePla, 6,2,3,4,null,2,0,1 );
        Assertions.assertEquals(expectedSuguri, sugur);

        Assertions.assertNotEquals(wild, o);
        Assertions.assertEquals(wild, wild);
        var expected_wild = new UnitsEnemy(name_wild,3,-1,-1,1,false,0);
        Assertions.assertEquals(expected_wild, wild);

        Assertions.assertNotEquals(boss, o);
        Assertions.assertEquals(boss, boss);
        var expected_boss = new UnitsEnemy(name_boss,8,3,2,-1,true,0);
        Assertions.assertEquals(expected_boss, boss);
    }
    @Test
    public void combatTest(){
        sugur.setSeed(12); /** se planto una semilla en especifico esperando que se repita correctamente **/
        int dam = sugur.attack();
        Assertions.assertTrue(dam >= 1); /**  el daño siempre deberia ser mayor o igual a 1 */
        Assertions.assertEquals(3,dam);

        System.out.print("prueba de defensa: \n");
        int hp = sugur.getHpActual();
        sugur.defense(dam);
        Assertions.assertNotEquals(hp,sugur.getHpActual());

        System.out.print("prueba de dodge: \n");
        hp = sugur.getHpActual();
        sugur.dodge(dam); /** ESQUIVA EN ESTA SEMILLA EN ESTA OPORTUNIDAD **/
        Assertions.assertEquals(hp,sugur.getHpActual());
    }


}
