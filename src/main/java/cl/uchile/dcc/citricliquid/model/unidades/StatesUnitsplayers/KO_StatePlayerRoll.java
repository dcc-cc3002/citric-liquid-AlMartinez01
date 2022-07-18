package cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers;

import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

import java.io.IOException;

public class KO_StatePlayerRoll implements StatesPlayer {
    UnitsPlayer player;
    int r;
    public KO_StatePlayerRoll(UnitsPlayer player, int coolDown) {
        this.player = player;
        this.r = coolDown;
        System.out.printf("El valor a superar o igualar sera: "+ r+"\n");
    }

    @Override
    public void rollDice() throws IOException {
        int roll1 = player.roll();
        if (roll1 >= r) {
            System.out.printf(player.getId() + " se a recuperado!!!\n");
            player.setStatesPlayer(new Standby_mode_Player());
            player.setHpActual(player.getHpMax());
            player.initTurn();
            return;
        }
        System.out.print("Seguira en modo KO el jugador\n");
        player.setStatesPlayer(new KO_StatePlayer(player,r-1));
        if (player.getUbi() == null)return;
        player.getUbi().notifierEvent();
    }

    @Override
    public void option0() {

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
    public void activeState() {

    }

    @Override
    public void setUnitPlayers(UnitsPlayer unitPlayers) {
    }
}
