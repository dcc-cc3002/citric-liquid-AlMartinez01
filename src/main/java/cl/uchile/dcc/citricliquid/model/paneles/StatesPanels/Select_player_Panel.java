package cl.uchile.dcc.citricliquid.model.paneles.StatesPanels;

import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.CombatPlayers;

import cl.uchile.dcc.citricliquid.model.paneles.Panel;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.StandbyPanel;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Standby_mode_Player;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Select_player_Panel implements StatesPanel {
    public Panel panel;
    public UnitsPlayer player;
    public int count;
    public Select_player_Panel(@NotNull Panel panel, @NotNull UnitsPlayer player, int count) {
        this.panel = panel;
        this.player = player;
        this.count = count;
        System.out.println("deseas pelear contra:\n");
        player.setStatesPlayer(new StandbyPanel());
        player.setUbi(panel);
        int i = 0;
        for (UnitsPlayer players: panel.getUnits()) {
            System.out.println(players.getId() + " (" + i + ")\n");
            i++;
        }
    }
    @Override
    public void rollDice() throws IOException {
        if (panel.cantNexts() == 1) this.panel.getNexts()[0].avanzar(player,count-1);
    }

    @Override
    public void option0() throws IOException {
        player.setStatesPlayer(new Standby_mode_Player());
        panel.setStatesPanel(new Standly_mode_panel());
        CombatPlayers combatPlayers = new CombatPlayers(player,this.panel.getUnits()[0], panel);
        combatPlayers.starter();
        this.panel.unitPlayer(player);
    }

    @Override
    public void option1() {
        int i = 1;
        if (i > this.panel.cantUnits()-1){
            System.out.print("Seleccione un jugador \n");
            return;
        }
        panel.setStatesPanel(new Standly_mode_panel());
        player.setStatesPlayer(new Standby_mode_Player());
        CombatPlayers combatPlayers = new CombatPlayers(player,this.panel.getUnits()[i], panel);
        combatPlayers.starter();
        this.panel.unitPlayer(player);
    }

    @Override
    public void option2() {
        int i = 2;
        if (i > this.panel.cantUnits()-1){
            System.out.print("Seleccione un jugador \n");
            return;
        }
        panel.setStatesPanel(new Standly_mode_panel());
        player.setStatesPlayer(new Standby_mode_Player());
        CombatPlayers combatPlayers;
        combatPlayers = new CombatPlayers(player,this.panel.getUnits()[i], panel);
        combatPlayers.starter();
        this.panel.unitPlayer(player);
    }

    @Override
    public void option3() {
        int i = 3;
        if (i > this.panel.cantUnits()-1){
            System.out.print("Seleccione un jugador \n");
            return;
        }
        panel.setStatesPanel(new Standly_mode_panel());
        player.setStatesPlayer(new Standby_mode_Player());
        CombatPlayers combatPlayers = new CombatPlayers(player,this.panel.getUnits()[i], panel);
        combatPlayers.starter();
        this.panel.unitPlayer(player);
    }

    @Override
    public void option4() {
        int i = 4;
        if (i > (this.panel.cantUnits() - 1)){
            System.out.print("Seleccione un jugador \n");
            return;
        }
        this.panel.setStatesPanel(new Standly_mode_panel());
        player.setStatesPlayer(new Standby_mode_Player());
        CombatPlayers combatPlayers = new CombatPlayers(player,this.panel.getUnits()[i], this.panel);
        combatPlayers.starter();
        this.panel.unitPlayer(player);
    }

    @Override
    public void option5() {
        int i = 5;
        if (i > this.panel.cantUnits()-1){
            System.out.print("Seleccione un jugador \n");
            return;
        }
        panel.setStatesPanel(new Standly_mode_panel());
        player.setStatesPlayer(new Standby_mode_Player());
        CombatPlayers combatPlayers = new CombatPlayers(player,this.panel.getUnits()[i], panel);
        combatPlayers.starter();
        this.panel.unitPlayer(player);
    }

    @Override
    public void option6() {
        int i = 6;
        if (i > this.panel.cantUnits()-1){
            System.out.print("Seleccione un jugador \n");
            return;
        }
        panel.setStatesPanel(new Standly_mode_panel());
        player.setStatesPlayer(new Standby_mode_Player());
        CombatPlayers combatPlayers = new CombatPlayers(player,this.panel.getUnits()[i], panel);
        combatPlayers.starter();
        this.panel.unitPlayer(player);
    }

    @Override
    public void option7() {
        int i = 7;
        if (i > this.panel.cantUnits()-1){
            System.out.print("Seleccione un jugador \n");
            return;
        }
        panel.setStatesPanel(new Standly_mode_panel());
        player.setStatesPlayer(new Standby_mode_Player());
        CombatPlayers combatPlayers = new CombatPlayers(player,this.panel.getUnits()[i], panel);
        combatPlayers.starter();
        this.panel.unitPlayer(player);
    }

    @Override
    public void option8() {
        int i = 8;
        if (i > this.panel.cantUnits()-1){
            System.out.print("Seleccione un jugador \n");
            return;
        }
        panel.setStatesPanel(new Standly_mode_panel());
        player.setStatesPlayer(new Standby_mode_Player());
        CombatPlayers combatPlayers = new CombatPlayers(player,this.panel.getUnits()[i], panel);
        combatPlayers.starter();
        this.panel.unitPlayer(player);
    }

    @Override
    public void option9() {
        int i = 9;
        if (i > this.panel.cantUnits()-1){
            System.out.print("Seleccione un jugador \n");
            return;
        }
        panel.setStatesPanel(new Standly_mode_panel());
        player.setStatesPlayer(new Standby_mode_Player());
        CombatPlayers combatPlayers = new CombatPlayers(player,this.panel.getUnits()[i], panel);
        combatPlayers.starter();
        this.panel.unitPlayer(player);
    }

    @Override
    public void addPanel(Panel panel) {
        this.panel = panel;
    }

}