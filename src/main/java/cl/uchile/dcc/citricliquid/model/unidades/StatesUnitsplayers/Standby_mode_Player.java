package cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers;

import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

import java.io.IOException;

public class Standby_mode_Player implements StatesPlayer {
    UnitsPlayer unitsPlayer;
    @Override
    public void setUnitPlayers(UnitsPlayer unitPlayers) {
        this.unitsPlayer = unitPlayers;
    }
}
