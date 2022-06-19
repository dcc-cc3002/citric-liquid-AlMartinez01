package cl.uchile.dcc.citricliquid.model;

import cl.uchile.dcc.citricliquid.model.paneles.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.paneles.abstracto.Carts_ejm;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class UnitsTest {

    private final String NamePla = "player 1";
    private final String name_wild = "chicken";
    private final String name_boss = "Store Manager";
    private UnitsPlayer sugur;
    private UnitsPlayer sinCartSugur;
    private UnitsEnemy wild;
    private UnitsEnemy boss;
    private Carts_ejm cart;
    private Carts_ejm cart2;

    @BeforeEach
    public void setUp() {
        String name_cart = "cart ejm";
        cart = new Carts_ejm(name_cart, name_cart);
        String name_cart2 = "cart2";
        cart2 = new Carts_ejm(name_cart2, name_cart2);
        sugur = new UnitsPlayer(NamePla, 6, 2, 3, 4, new Carts[]{cart, cart}, null, 2, 0, 1);
        sinCartSugur = new UnitsPlayer(NamePla, 6, 2, 3, 4, null, null, 2, 0, 1);
        wild = new UnitsEnemy(name_wild, 3, -1, -1, 1, false, 0);
        boss = new UnitsEnemy(name_boss, 8, 3, 2, -1, true, 0);

    }

    @Test
    public void builders() {
        var expected = new UnitsPlayer(NamePla, 6, 2, 3, 4, new Carts[]{cart, cart}, null, 2, 0, 1);
        assertEquals(expected, sugur);

        var expected_wild = new UnitsEnemy(name_wild, 3, -1, -1, 1, false, 0);
        assertEquals(expected_wild, wild);


        var expected_boss = new UnitsEnemy(name_boss, 8, 3, 2, -1, true, 0);
        assertEquals(expected_boss, boss);
    }

    @Test
    public void equalTest() {
        final var o = new Object();
        assertNotEquals(sugur, o);
        assertEquals(sugur, sugur);
        final var expectedSuguri = new UnitsPlayer(NamePla, 6, 2, 3, 4, new Carts[]{cart, cart}, null, 2, 0, 1);
        assertEquals(expectedSuguri, sugur);

        assertNotEquals(wild, o);
        assertEquals(wild, wild);
        var expected_wild = new UnitsEnemy(name_wild, 3, -1, -1, 1, false, 0);
        assertEquals(expected_wild, wild);

        assertNotEquals(boss, o);
        assertEquals(boss, boss);
        var expected_boss = new UnitsEnemy(name_boss, 8, 3, 2, -1, true, 0);
        assertEquals(expected_boss, boss);
    }

    @Test
    public void combatTest() {
        sugur.setSeed(12); // se planto una semilla en especifico esperando que se repita correctamente **/
        int dam = sugur.attack();
        assertTrue(dam >= 1); //  el daño siempre deberia ser mayor o igual a 1 */
        assertEquals(3, dam);

        System.out.print("prueba de defensa: \n");
        int hp = sugur.getHpActual();
        sugur.defense(dam);
        assertNotEquals(hp, sugur.getHpActual());

        System.out.print("prueba de dodge: \n");
        hp = sugur.getHpActual();
        sugur.dodge(dam); // ESQUIVA EN ESTA SEMILLA EN ESTE TURNO **/
        assertEquals(hp, sugur.getHpActual());
    }

    @RepeatedTest(100)
    public void dodgeTest(){
        final long testSeed = new Random().nextLong();
        sugur.setSeed(testSeed);
        int dam = new Random().nextInt(1,8);
        int simRoll = new Random(testSeed).nextInt(6) + 1 + sugur.getEvd();
        int vida_original = sugur.getHpActual();
        sugur.dodge(dam);

        if (simRoll < dam){
            assertEquals(Math.max(0,vida_original-dam),sugur.getHpActual());
        }
        else {assertEquals(vida_original,sugur.getHpActual());}
    }
    @RepeatedTest(100)
    public void atack(){
        final long testSeed = new Random().nextLong();
        sugur.setSeed(testSeed);

        int simRoll = new Random(testSeed).nextInt(6) + 1 + sugur.getAtk();
        assertEquals(simRoll,sugur.attack());
    }
    @RepeatedTest(100)
    public void defenseTest(){
        final long testSeed = new Random().nextLong();
        sugur.setSeed(testSeed);
        int dam = new Random().nextInt(1,8);
        int simRoll = new Random(testSeed).nextInt(6) + 1 + sugur.getDef();
        int vida_original = sugur.getHpActual();
        sugur.defense(dam);

        if(dam-simRoll < 1) {
            assertEquals(vida_original - 1, sugur.getHpActual());
        }
        else{
            assertEquals(Math.max(0,vida_original-(dam-simRoll)),sugur.getHpActual());
        }
    }

    @Test
    public void cant_carts() {
        final var expectedSuguri = new UnitsPlayer(NamePla, 6, 2, 3, 4, new Carts[]{cart, cart}, null, 2, 0, 1);
        assertEquals(expectedSuguri.cant_carts(), sugur.cant_carts());
        assertNotEquals(3, sugur.cant_carts());
        assertEquals(2, sugur.cant_carts());
    }

    @Test
    public void select_carts() {
        sugur.initio_combat();
        sinCartSugur.initio_combat();
        final var Simunl = new UnitsPlayer(NamePla, 6, 2, 3, 4, new Carts[]{}, null, 2, 0, 1);
        sinCartSugur.initio_combat();
    }

    @Test
    public void deleteCartTest() {
        final var expectedSuguri = new UnitsPlayer(NamePla, 6, 2, 3, 4, new Carts[]{cart, cart}, null, 2, 0, 1);
        sugur.deleteCart(1);
        assertNotEquals(expectedSuguri.getMano(), sugur.getMano());


        sugur = new UnitsPlayer(NamePla, 6, 2, 3, 4, new Carts[]{cart, cart2}, null, 2, 0, 1);
        sugur.deleteCart(1);
        final var expectedMano = new UnitsPlayer(NamePla, 6, 2, 3, 4, new Carts[]{cart}, null, 2, 0, 1);
        assertEquals(expectedMano, sugur);

        sugur = new UnitsPlayer(NamePla, 6, 2, 3, 4, new Carts[]{cart, cart2}, null, 2, 0, 1);
        sugur.deleteCart(0);
        final var expectedMano2 = new UnitsPlayer(NamePla, 6, 2, 3, 4, new Carts[]{cart2}, null, 2, 0, 1);
        assertEquals(expectedMano2, sugur);
    }

    @Test
    public void incrementedTest() {
        var notExpectSugur = new UnitsPlayer(NamePla, 6, 2, 3, 4, new Carts[]{cart, cart}, null, 2, 0, 1);
        sugur.incrementStars(30);
        assertNotEquals(notExpectSugur, sugur);
        var expectSugur = new UnitsPlayer(NamePla, 6, 2, 3, 4, new Carts[]{cart, cart}, null, 32, 0, 1);
        assertEquals(expectSugur, sugur);

        sugur.incrementStars(-30);
        assertEquals(notExpectSugur, sugur);

        expectSugur = new UnitsPlayer(NamePla, 6, 2, 3, 4, new Carts[]{cart, cart}, null, 0, 0, 1);
        sugur.incrementStars(-30);
        assertEquals(expectSugur, sugur);
    }

    @Test
    void isBoss() {
        assertFalse(wild.isBoss());
        assertTrue(boss.isBoss());
    }
    @Test
    void getBagTest(){
        final var expected = new UnitsEnemy(name_wild, 3, -1, -1, 1, false, 0);
        assertEquals(0,wild.getBag());
    }
    @Test
    void setBagTest(){
        wild.setBag(4);
        assertEquals(4,wild.getBag());
    }
}
