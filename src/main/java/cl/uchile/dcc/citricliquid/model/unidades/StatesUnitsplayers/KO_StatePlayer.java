package cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers;

import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

import java.io.IOException;

public class KO_StatePlayer implements StatesPlayer {
    UnitsPlayer player;
    int r;
    public KO_StatePlayer() {
        this.r = 6;
    }
    public KO_StatePlayer(UnitsPlayer player, int coolDown) {
        this.player = player;
        this.r = coolDown;
    }

    @Override
    public void rollDice() throws IOException {

    }

    @Override
    public void option0() {

    }

    @Override
    public void option1() {

    }

    @Override
    public void option2() {

    }

    @Override
    public void option3() {

    }

    @Override
    public void option4() {

    }

    @Override
    public void option5() {

    }

    @Override
    public void option6() {

    }

    @Override
    public void option7() {

    }

    @Override
    public void option8() {

    }

    @Override
    public void option9() {

    }

    @Override
    public void activeState() {
        player.setStatesPlayer(new KO_StatePlayerRoll(player,r));
    }

    @Override
    public void setUnitPlayers(UnitsPlayer unitPlayers) {
        this.player = unitPlayers;
    }
}
