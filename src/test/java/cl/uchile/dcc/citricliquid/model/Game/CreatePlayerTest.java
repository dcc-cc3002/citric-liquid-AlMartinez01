package cl.uchile.dcc.citricliquid.model.Game;

import cl.uchile.dcc.citricliquid.model.controller.CreatePlayers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreatePlayerTest {
    CreatePlayers createPlayers;

    @BeforeEach
    void setUp(){
        createPlayers = new CreatePlayers(0);
    }

    @Test
    void  ubicMaxTest(){
        int[] r = new int[] {1,2,4,7};
        int i = createPlayers.ubicMax(r);
        assertEquals(3,i);
        assertEquals(0,r[i]);
        i = createPlayers.ubicMax(new int[] {1,2,4,2});
        assertEquals(2,i);
        i = createPlayers.ubicMax(new int[] {1,4,4,2});
        assertEquals(2,i);
    }



}
