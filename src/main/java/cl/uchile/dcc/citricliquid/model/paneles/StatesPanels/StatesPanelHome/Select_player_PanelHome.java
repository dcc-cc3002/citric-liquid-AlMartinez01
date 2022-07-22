package cl.uchile.dcc.citricliquid.model.paneles.StatesPanels.StatesPanelHome;

import cl.uchile.dcc.citricliquid.model.paneles.Panel;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanels.Standly_mode_panel;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanels.StatesPanel;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.StandbyPanel;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Standby_mode_Player;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Select_player_PanelHome implements StatesPanel {
    public Panel panelHome;
    public UnitsPlayer player;

    int count;
    public Select_player_PanelHome(Panel panel, @NotNull UnitsPlayer player, int count) {
        this.panelHome = panel;
        this.player = player;
        this.count = count;
        player.setStatesPlayer(new StandbyPanel());
        player.setUbi(panel);
        System.out.printf("jugador: " + player.getId() + " desea quedarse en en panelHome? (si[1] / no[0])\n");
    }



    @Override
    public void rollDice() throws IOException {
        panelHome.option0();

    }

    @Override
    public void option0() throws IOException {
        if (panelHome.cantNexts() == 1) panelHome.getNexts()[0].avanzar(player,count-1);
        player.setStatesPlayer(new Standby_mode_Player());
        panelHome.setStatesPanel(new Standly_mode_panel());
    }

    @Override
    public void option1() {
        player.setStatesPlayer(new Standby_mode_Player());
        panelHome.activator(player);
        panelHome.setStatesPanel(new Standly_mode_panel());
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
    public void addPanel(Panel panel) {
        this.panelHome = panel;
    }
}