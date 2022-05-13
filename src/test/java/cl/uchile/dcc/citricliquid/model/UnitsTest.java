package cl.uchile.dcc.citricliquid.model;

import cl.uchile.dcc.citricliquid.model.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.abstracto.Carts_ejm;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnitsTest {

    private String NamePla = "player 1";
    private String name_wild = "chicken";
    private String name_boss = "Store Manager";
    private String name_cart = "cart ejm";
    private String name_cart2 = "cart2";
    private UnitsPlayer sugur;
    private UnitsEnemy wild;
    private UnitsEnemy boss;
    private Carts_ejm cart;
    private Carts_ejm cart2;

    @BeforeEach
    public void setUp() {
        cart = new Carts_ejm(name_cart,name_cart );
        cart2 = new Carts_ejm(name_cart2,name_cart2);
        sugur = new UnitsPlayer(NamePla, 6,2,3,4, new Carts[]{cart,cart},2,0,1 );
        wild = new UnitsEnemy(name_wild,3,-1,-1,1,false,0);
        boss = new UnitsEnemy(name_boss,8,3,2,-1,true,0);

    }

    @Test
    public void builders(){
        var expected = new UnitsPlayer(NamePla, 6,2,3,4, new Carts[]{cart,cart},2,0,1 );
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
        final var expectedSuguri = new UnitsPlayer(NamePla, 6,2,3,4, new Carts[]{cart,cart},2,0,1 );
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
        sugur.dodge(dam); /** ESQUIVA EN ESTA SEMILLA EN ESTE TURNO **/
        Assertions.assertEquals(hp,sugur.getHpActual());
    }
    @Test
    public void cant_carts(){
        final var expectedSuguri = new UnitsPlayer(NamePla, 6,2,3,4, new Carts[]{cart,cart},2,0,1 );
        Assertions.assertEquals(expectedSuguri.cant_carts(), sugur.cant_carts());
        Assertions.assertNotEquals(3,sugur.cant_carts());
        Assertions.assertEquals(2,sugur.cant_carts());
    }
    @Test
    public void select_carts(){
        sugur.initio_combat();
    }
    @Test
    public void deleteCartTest(){
        final var expectedSuguri = new UnitsPlayer(NamePla, 6,2,3,4, new Carts[]{cart,cart},2,0,1 );
        sugur.deleteCart(1);
        Assertions.assertNotEquals(expectedSuguri.getMano(),sugur.getMano());


        sugur = new UnitsPlayer(NamePla, 6,2,3,4, new Carts[]{cart,cart2},2,0,1 );
        sugur.deleteCart(1);
        System.out.print(sugur.getMano()[0].getCharacters() +"\n");
        final var expectedMano = new UnitsPlayer(NamePla, 6,2,3,4, new Carts[]{cart},2,0,1 );
        Assertions.assertEquals(expectedMano, sugur);

        sugur = new UnitsPlayer(NamePla, 6,2,3,4, new Carts[]{cart,cart2},2,0,1 );
        sugur.deleteCart(0);
        System.out.print(sugur.getMano()[0].getCharacters()+"\n");
        final var expectedMano2 = new UnitsPlayer(NamePla, 6,2,3,4, new Carts[]{cart2},2,0,1 );
        Assertions.assertEquals(expectedMano2, sugur);
    }



}
