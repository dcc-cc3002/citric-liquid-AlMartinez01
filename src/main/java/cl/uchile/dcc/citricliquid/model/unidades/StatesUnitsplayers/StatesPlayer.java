package cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers;

import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

import java.io.IOException;

public interface StatesPlayer {

    void rollDice() throws IOException;

    void option0();

    void option1();

    void option2();

    void option3();
    void option4();
    void option5();
    void option6();
    void option7();
    void option8();
    void option9();

    void setUnitPlayers(UnitsPlayer unitPlayers);
}