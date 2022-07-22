package cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers;

import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

import java.io.IOException;

public class Play_mode_player implements StatesPlayer {
    public UnitsPlayer player;

    @Override
    public void rollDice() throws IOException {
        player.setStatesPlayer(new Standby_mode_Player());
        player.play();
    }
    @Override
    public void setUnitPlayers(UnitsPlayer unitPlayers) {
        this.player = unitPlayers;
    }
}
