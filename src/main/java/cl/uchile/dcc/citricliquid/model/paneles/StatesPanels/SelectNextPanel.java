package cl.uchile.dcc.citricliquid.model.paneles.StatesPanels;

import cl.uchile.dcc.citricliquid.model.paneles.Panel;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.StandbyPanel;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Standby_mode_Player;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

import java.io.IOException;

public class SelectNextPanel implements StatesPanel {
    Panel panel;
    int restante;
    UnitsPlayer player;

    public SelectNextPanel(Panel panel, int restante, UnitsPlayer player) {
        this.panel = panel;
        this.restante = restante;
        this.player = player;
        player.setStatesPlayer(new StandbyPanel());

        System.out.println(player.getId()+ " tiene las siguientes opciones para seguir: \n");
        int i = 0;
        for (Panel panel1: panel.getNexts()){
            System.out.println(panel1.toString() + "("+i+")\n");
            i++;
        }
    }

    @Override
    public void rollDice() throws IOException {

    }

    @Override
    public void option0() throws IOException {
        panel.setStatesPanel(new Standly_mode_panel());
        player.setStatesPlayer(new Standby_mode_Player());
        panel.deletedPlayer(player);
        panel.getNexts()[0].avanzar(player,restante-1);

    }

    @Override
    public void option1() throws IOException {
        panel.setStatesPanel(new Standly_mode_panel());
        player.setStatesPlayer(new Standby_mode_Player());
        panel.deletedPlayer(player);
        panel.getNexts()[1].avanzar(player,restante-1);
    }

    @Override
    public void option2() throws IOException {
        if (2 > panel.cantNexts()){
            System.out.println("Este panel no existe\n");
            return;
        }
        panel.setStatesPanel(new Standly_mode_panel());
        player.setStatesPlayer(new Standby_mode_Player());
        panel.deletedPlayer(player);
        panel.getNexts()[2].avanzar(player,restante-1);
    }

    @Override
    public void option3() throws IOException {
        if (3 > panel.cantNexts()){
            System.out.println("Este panel no existe\n");
            return;
        }
        panel.setStatesPanel(new Standly_mode_panel());
        player.setStatesPlayer(new Standby_mode_Player());
        panel.deletedPlayer(player);
        panel.getNexts()[3].avanzar(player,restante-1);
    }

    @Override
    public void option4() throws IOException {
        if (4 > panel.cantNexts()){
            System.out.println("Este panel no existe\n");
            return;
        }
        panel.setStatesPanel(new Standly_mode_panel());
        player.setStatesPlayer(new Standby_mode_Player());
        panel.deletedPlayer(player);
        panel.getNexts()[4].avanzar(player,restante-1);
    }

    @Override
    public void option5() throws IOException {
        if (5 > panel.cantNexts()){
            System.out.println("Este panel no existe\n");
            return;
        }
        panel.setStatesPanel(new Standly_mode_panel());
        player.setStatesPlayer(new Standby_mode_Player());
        panel.deletedPlayer(player);
        panel.getNexts()[5].avanzar(player,restante-1);
    }

    @Override
    public void option6() throws IOException {
        if (6 > panel.cantNexts()){
            System.out.println("Este panel no existe\n");
            return;
        }
        panel.setStatesPanel(new Standly_mode_panel());
        player.setStatesPlayer(new Standby_mode_Player());
        panel.deletedPlayer(player);
        panel.getNexts()[6].avanzar(player,restante-1);
    }

    @Override
    public void option7() throws IOException {
        if (7 > panel.cantNexts()){
            System.out.println("Este panel no existe\n");
            return;
        }
        panel.setStatesPanel(new Standly_mode_panel());
        player.setStatesPlayer(new Standby_mode_Player());
        panel.deletedPlayer(player);
        panel.getNexts()[7].avanzar(player,restante-1);
    }

    @Override
    public void option8() throws IOException {
        if (8 > panel.cantNexts()){
            System.out.println("Este panel no existe\n");
            return;
        }
        panel.setStatesPanel(new Standly_mode_panel());
        player.setStatesPlayer(new Standby_mode_Player());
        panel.deletedPlayer(player);
        panel.getNexts()[8].avanzar(player,restante-1);
    }

    @Override
    public void option9() throws IOException {
        if (9 > panel.cantNexts()){
            System.out.println("Este panel no existe\n");
            return;
        }
        panel.setStatesPanel(new Standly_mode_panel());
        player.setStatesPlayer(new Standby_mode_Player());
        panel.deletedPlayer(player);
        panel.getNexts()[9].avanzar(player,restante-1);
    }

    @Override
    public void addPanel(Panel panel) {
        this.panel = panel;
    }
}
