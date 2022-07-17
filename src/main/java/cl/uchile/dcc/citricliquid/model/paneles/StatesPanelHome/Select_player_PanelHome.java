package cl.uchile.dcc.citricliquid.model.paneles.StatesPanelHome;

import cl.uchile.dcc.citricliquid.model.paneles.Panel;
import cl.uchile.dcc.citricliquid.model.paneles.PanelHome;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Select_player_PanelHome implements StatesPanel {
    public Panel panelHome;
    public UnitsPlayer player;

    int count;
    public Select_player_PanelHome(Panel panelHome, @NotNull UnitsPlayer player, int count) {
        this.panelHome = panelHome;
        this.player = player;
        this.count = count;
        System.out.printf("jugador: " + player.getId() + " desea quedarse en en panelHome? (si[1] / no[0])");
    }



    @Override
    public void rollDice() throws IOException {
        panelHome.option0();
    }

    @Override
    public void option0() throws IOException {
        panelHome.getNexts().avanzar(player,count-1);
        panelHome.setStatesPanel(new Standly_mode_panel());
    }

    @Override
    public void option1() {
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