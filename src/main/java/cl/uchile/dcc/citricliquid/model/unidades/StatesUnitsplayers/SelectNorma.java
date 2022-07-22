package cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers;

import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

import java.io.IOException;

public class SelectNorma implements StatesPlayer{
    private UnitsPlayer player;
    public SelectNorma() {
    }
    @Override
    public void option0() throws IOException {
        player.setPrefNorma(true);
        player.setStatesPlayer(new Standby_mode_Player());
        player.getUbi().notifierEvent();
    }

    @Override
    public void option1() throws IOException {
        player.setPrefNorma(false);
        player.setStatesPlayer(new Standby_mode_Player());
        player.getUbi().notifierEvent();
    }

    @Override
    public void setUnitPlayers(UnitsPlayer unitPlayers) {
        player = unitPlayers;
        System.out.println(player.getId()+" quiere recolectar estrellas (1) o victorias (0) ?");
    }
}
