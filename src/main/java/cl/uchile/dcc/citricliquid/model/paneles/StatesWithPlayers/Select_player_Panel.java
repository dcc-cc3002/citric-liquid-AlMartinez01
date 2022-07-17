package cl.uchile.dcc.citricliquid.model.paneles.StatesWithPlayers;

import cl.uchile.dcc.citricliquid.model.paneles.Panel;
import cl.uchile.dcc.citricliquid.model.paneles.PanelHome;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanelHome.StatesPanel;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Select_player_Panel implements StatesPanel {
    public Panel panel;
    UnitsPlayer player;
    int count;
    public Select_player_Panel(@NotNull Panel panel, @NotNull UnitsPlayer player, int count) {
        this.panel = panel;
        this.player = player;
        this.count = count;
        System.out.println("deseas pelear contra:\n");
        int i = 0;
        for (UnitsPlayer players: panel.getUnits()) {
            System.out.println(players.getId() + " (" + i + ")\n");
            i++;
        }
    }



    @Override
    public void rollDice() throws IOException {
    }

    @Override
    public void option0() throws IOException {

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
    public void addPanel(Panel panel) {
        this.panel = panel;
    }

}