package cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers;

import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

import java.io.IOException;

public interface StatesPlayer {

    default void rollDice() throws IOException{}
    default void option0() throws IOException{}
    default void option1() throws IOException{}
    default void option2() throws IOException{}
    default void option3() throws IOException{}
    default void option4() throws IOException{}
    default void option5() throws IOException{}
    default void option6() throws IOException{}
    default void option7() throws IOException{}
    default void option8() throws IOException{}
    default void option9() throws IOException{}
    default void activeState(){}
    default void setUnitPlayers(UnitsPlayer unitPlayers){}
}