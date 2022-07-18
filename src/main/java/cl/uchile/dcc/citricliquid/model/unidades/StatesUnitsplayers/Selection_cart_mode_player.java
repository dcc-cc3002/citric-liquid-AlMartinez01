package cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers;

import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

import java.io.IOException;

public class Selection_cart_mode_player implements StatesPlayer{
    UnitsPlayer player;
    @Override
    public void rollDice() throws IOException {
        System.out.printf("no se selecciono carta");
        player.setStatesPlayer(new Standby_mode_Player());
    }
    @Override
    public void option0() {
        if(player.selectCart(0)){
            player.setStatesPlayer(new Standby_mode_Player());
        }
    }

    @Override
    public void option1() {
        if(player.selectCart(1)){
            player.setStatesPlayer(new Standby_mode_Player());
        }
    }

    @Override
    public void option2() {
        if(player.selectCart(2)){
            player.setStatesPlayer(new Standby_mode_Player());
        }
    }

    @Override
    public void option3() {
        if(player.selectCart(3)){
            player.setStatesPlayer(new Standby_mode_Player());
        }
    }

    @Override
    public void option4() {
        if(player.selectCart(4)){
            player.setStatesPlayer(new Standby_mode_Player());
        }
    }

    @Override
    public void option5() {
        if(player.selectCart(5)){
            player.setStatesPlayer(new Standby_mode_Player());
        }
    }

    @Override
    public void option6() {
        if(player.selectCart(6)){
            player.setStatesPlayer(new Standby_mode_Player());
        }
    }

    @Override
    public void option7() {
        if(player.selectCart(7)){
            player.setStatesPlayer(new Standby_mode_Player());
        }
    }

    @Override
    public void option8() {
        if(player.selectCart(8)){
            player.setStatesPlayer(new Standby_mode_Player());
        }
    }

    @Override
    public void option9() {
        if(player.selectCart(9)){
            player.setStatesPlayer(new Standby_mode_Player());
        }
    }

    @Override
    public void activeState() {

    }

    @Override
    public void setUnitPlayers(UnitsPlayer unitPlayers) {
        this.player = unitPlayers;
    }
}
