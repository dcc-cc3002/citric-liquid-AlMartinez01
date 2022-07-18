package cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers;

import cl.uchile.dcc.citricliquid.model.Player;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

import java.io.IOException;

public class Receive_damage_mode_player implements StatesPlayer{
    int dam;
    UnitsPlayer player;

    public Receive_damage_mode_player(int dam, UnitsPlayer player) {
        System.out.printf("jugador: " + player.getId() +" desea defender(0) o esquivar(1)? ");
        this.dam = dam;
        this.player = player;
    }

    @Override
    public void rollDice() throws IOException {
        System.out.printf("Valor erroneo, desea defender(0) o esquivar(1)?");
    }

    @Override
    public void option0() {
        System.out.println("a defendido");
        player.defense(dam);
        player.setStatesPlayer(new Standby_mode_Player());
        player.notifierEvent();
    }

    @Override
    public void option1() {
        System.out.println("a esquivado");
        player.dodge(dam);
        player.setStatesPlayer(new Standby_mode_Player());
        player.notifierEvent();
    }

    @Override
    public void option2() {
        System.out.printf("\n Valor erroneo, desea defender(0) o esquivar(1)?");
    }

    @Override
    public void option3() {
        System.out.printf("\n Valor erroneo, desea defender(0) o esquivar(1)?");
    }

    @Override
    public void option4() {
        System.out.printf("\n Valor erroneo, desea defender(0) o esquivar(1)?");
    }

    @Override
    public void option5() {
        System.out.printf("\n Valor erroneo, desea defender(0) o esquivar(1)?");
    }

    @Override
    public void option6() {
        System.out.printf("\n Valor erroneo, desea defender(0) o esquivar(1)?");
    }

    @Override
    public void option7() {
        System.out.printf("\n Valor erroneo, desea defender(0) o esquivar(1)?");
    }

    @Override
    public void option8() {
        System.out.printf("\n Valor erroneo, desea defender(0) o esquivar(1)?");
    }

    @Override
    public void option9() {
        System.out.printf("\n Valor erroneo, desea defender(0) o esquivar(1)?");
    }

    @Override
    public void activeState() {

    }

    @Override
    public void setUnitPlayers(UnitsPlayer unitPlayers) {
        this.player = unitPlayers;
    }

}
