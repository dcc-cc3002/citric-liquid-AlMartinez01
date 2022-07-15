package cl.uchile.dcc.citricliquid.model.unidades;

import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts_ejm;
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
    public void attack(){
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
        System.out.printf(sugur.toString());
        assertTrue(expectedMano2.equals(sugur));
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
        assertTrue(expectSugur.equals(sugur));
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

    @Test
    void deadTest(){
        assertFalse(sugur.deadUnit());
        sugur.setHpActual(0);
        assertTrue(sugur.deadUnit());
    }

    @Test
    void  defeatTest(){
        wild.setBag(10);
        int[] lootWildexpected = new int[]{wild.getBag(),1};
        int[] lootWild = wild.defeat();
        assertEquals(lootWildexpected[0],lootWild[0]);
        assertEquals(lootWildexpected[1],lootWild[1]);

        boss.setBag(10);
        int[] lootBossexpected = new int[]{boss.getBag(),3};
        int[] lootBoss = boss.defeat();
        assertEquals(lootBossexpected[0],lootBoss[0]);
        assertEquals(lootBossexpected[1],lootBoss[1]);

        sugur.setStars(50);
        int[] lootplayerexpected = new int[]{sugur.getStars()/2,2};
        int[] lootplayer = sugur.defeat();
        assertEquals(lootplayerexpected[0],lootplayer[0]);
        assertEquals(lootplayerexpected[1],lootplayer[1]);
    }

    @Test
    void victoryTest(){
        int[] recompensa = new int[] {4,2};
        sugur.setStars(0);
        sugur.victory(recompensa);
        assertEquals(4,sugur.getStars());
        assertEquals(2,sugur.getWins());
    }

    @Test
    public void selectCartTest(){
        assertFalse(sugur.selectCart(2));
        assertEquals(2,sugur.cant_carts());
        assertTrue(sugur.selectCart(1));
        assertEquals(1,sugur.cant_carts());
    }
}
