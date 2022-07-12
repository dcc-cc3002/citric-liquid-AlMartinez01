package cl.uchile.dcc.citricliquid.model.unidades;

import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Receive_damage_mode_player;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitEnemyTest {
    UnitsEnemy enemy;
    UnitsPlayer player;

    @BeforeEach
    void setUp(){
        enemy = new UnitsEnemy("chicken", 7, -1, -1, 1, false, 0);
        player = new UnitsPlayer("suguri", 6, 2, 3, 4, new Carts[]{}, null, 2, 0, 1);
    }

    @RepeatedTest(100)
    public void addStartsTest(){
        int random = new Random().nextInt(10);
        enemy.addStars(random);
        assertEquals(random,enemy.getBag());
    }
    @Test
    public void attackTest() {
        enemy.attack(player);
        assertEquals(Receive_damage_mode_player.class,player.getStatesPlayer().getClass());
    }

    @Test
    public void initio_combar(){
        enemy.initio_combat();
        //enemy no tiene un inicio de combate pero por ser capaz de pelear posee el metodo
    }

    @RepeatedTest(10)
    public void Victory(){
        int random = new Random().nextInt(15);
        enemy.victory(new int[]{random,2});
        assertEquals(random,enemy.getBag());
    }

    @RepeatedTest(100)
    public void receiveDamageTest(){
        final long testSeed = new Random().nextLong();
        Random random = new Random(testSeed);
        enemy.setSeed(testSeed);
        int hp_last = enemy.getHpActual();
        int damage = new Random().nextInt(1,5);
        int rollEnemyExpected = random.nextInt(2) ;
        enemy.receiveDamagePlayer(damage);
        if (rollEnemyExpected == 1){
            int i = Math.max(damage - (random.nextInt(6)+1  + enemy.getDef()),1);
            assertEquals(Math.max(0,hp_last-i),enemy.getHpActual());
        }
        else{
            int i = random.nextInt(6)+1  + enemy.getEvd();
            if (damage > i){
                assertEquals(Math.max(0,hp_last-damage),enemy.getHpActual());
            }
            else {
                assertEquals(hp_last,enemy.getHpActual());
            }
        }
        //enemy.receiveDamagePlayer(damage);

    }

}