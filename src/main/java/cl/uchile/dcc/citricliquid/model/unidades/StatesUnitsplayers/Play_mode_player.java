package cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers;

import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

import java.io.IOException;

public class Play_mode_player implements StatesPlayer {
    public UnitsPlayer player;

    @Override
    public void rollDice() throws IOException {
        player.setStatesPlayer(new Standby_mode_Player());
        player.play();
        /*
        player.play();
        player.setStatesPlayer(new Standby_mode_Player());
        */
    }

    @Override
    public void option0() {    }

    @Override
    public void option1() {    }

    @Override
    public void option2() {    }

    @Override
    public void option3() {    }

    @Override
    public void option4() {}

    @Override
    public void option5() {    }

    @Override
    public void option6() {    }

    @Override
    public void option7() {    }

    @Override
    public void option8() {    }

    @Override
    public void option9() {    }

    @Override
    public void activeState() {

    }

    @Override
    public void setUnitPlayers(UnitsPlayer unitPlayers) {
        this.player = unitPlayers;
    }
}
