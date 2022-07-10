package cl.uchile.dcc.citricliquid.model.board.Elections;

import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts_ejm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElectionReceiveDamageTest {
    private final String NamePla = "player 1";
    private UnitsPlayer sugur;
    private Carts_ejm cart;
    String name_cart = "cart ejm";

    @BeforeEach
    public void setUp() {
        cart = new Carts_ejm(name_cart, name_cart);
        sugur = new UnitsPlayer(NamePla, 6, 2, 3, 4, new Carts[]{cart, cart}, null, 2, 0, 1);

    }
    @RepeatedTest(100)
    void receiveDefenseTest(){
        final long testSeed = new Random().nextLong();
        sugur.setSeed(testSeed);
        int dam = new Random().nextInt(1,8);
        int simRoll = new Random(testSeed).nextInt(6) + 1 + sugur.getDef();
        int vida_original = sugur.getHpActual();

        sugur.receiveDamagePlayer(dam);
        sugur.option0();

        if(dam-simRoll < 1) {
            assertEquals(vida_original - 1, sugur.getHpActual());
        }
        else{
            assertEquals(Math.max(0,vida_original-(dam-simRoll)),sugur.getHpActual());
        }
    }

    @RepeatedTest(100)
    void receiveDodgeTest(){
        final long testSeed = new Random().nextLong();
        sugur.setSeed(testSeed);
        int dam = new Random().nextInt(1,8);
        int simRoll = new Random(testSeed).nextInt(6) + 1 + sugur.getEvd();
        int vida_original = sugur.getHpActual();

        sugur.receiveDamagePlayer(dam);
        sugur. option1();

        if (simRoll < dam){
            assertEquals(Math.max(0,vida_original-dam),sugur.getHpActual());
        }
        else {assertEquals(vida_original,sugur.getHpActual());}
    }


}
