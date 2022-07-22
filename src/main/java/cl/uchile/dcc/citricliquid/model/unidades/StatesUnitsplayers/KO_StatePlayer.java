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
    public void activeState() {
        player.setStatesPlayer(new KO_StatePlayerRoll(player,r));
    }

    @Override
    public void setUnitPlayers(UnitsPlayer unitPlayers) {
        this.player = unitPlayers;
    }
}
