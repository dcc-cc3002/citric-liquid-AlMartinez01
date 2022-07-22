package cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers;

import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

import java.io.IOException;

public class StandbyPanel implements StatesPlayer{
    UnitsPlayer player;

    public StandbyPanel() {
    }
    @Override
    public void option0() throws IOException {
        player.getUbi().statesPanel.option0();
    }

    @Override
    public void option1() throws IOException {
        player.getUbi().statesPanel.option1();
    }

    @Override
    public void option2() throws IOException {
        player.getUbi().statesPanel.option2();
    }

    @Override
    public void option3() throws IOException {
        player.getUbi().statesPanel.option3();
    }

    @Override
    public void option4() throws IOException {
        player.getUbi().statesPanel.option4();
    }

    @Override
    public void option5() throws IOException {
        player.getUbi().statesPanel.option5();
    }

    @Override
    public void option6() throws IOException {
        player.getUbi().statesPanel.option6();
    }

    @Override
    public void option7() throws IOException {
        player.getUbi().statesPanel.option7();
    }

    @Override
    public void option8() throws IOException {
        player.getUbi().statesPanel.option8();
    }

    @Override
    public void option9() throws IOException {
        player.getUbi().statesPanel.option9();
    }

    @Override
    public void activeState() {

    }

    @Override
    public void setUnitPlayers(UnitsPlayer unitPlayers) {
        player = unitPlayers;
    }
}
