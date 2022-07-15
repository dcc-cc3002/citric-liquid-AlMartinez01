package cl.uchile.dcc.citricliquid.model.paneles;


import cl.uchile.dcc.citricliquid.model.controller.GameController;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.Observable;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.Observer;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanelHome.Select_player_PanelHome;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanelHome.Standly_mode_panelHome;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanelHome.StatesPanelHome;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Scanner;

public class PanelHome extends Panel implements Observable {
    UnitsPlayer home;
    Observer observer;

    StatesPanelHome statesPanelHome;

    public PanelHome(UnitsPlayer[] units, Panel nexts, Carts carta, UnitsPlayer home) {
        super(units, nexts, carta);
        this.home = home;
        this.statesPanelHome = new Standly_mode_panelHome();
    }

    private void normaCheck(@NotNull UnitsPlayer u1) {
        if (u1.getLvlNorma() == 1) {
            if (u1.getStars() >= 10) {
                u1.setLvlNorma(2);
            }
        } else if (u1.getLvlNorma() == 2) {
            if (u1.getStars() >= 30 && u1.getWins() >= 2) {
                u1.setLvlNorma(3);
            }
        } else if (u1.getLvlNorma() == 3) {
            if (u1.getStars() >= 70 && u1.getWins() >= 5) {
                u1.setLvlNorma(4);
            }
        } else if (u1.getLvlNorma() == 4) {
            if (u1.getStars() >= 120 && u1.getWins() >= 9) {
                u1.setLvlNorma(5);
            }
        } else if (u1.getLvlNorma() == 5) {
            if (u1.getStars() >= 200 && u1.getWins() >= 14) {
                u1.setLvlNorma(6);
            }
        }
    }
    ////////////////CONTROLLER//////////////////
    public void rollDice() throws IOException{statesPanelHome.rollDice();}
    public void option0() throws IOException {statesPanelHome.option0();}
    public void option1(){statesPanelHome.option1();}
    public void option2(){statesPanelHome.option2();}
    public void option3(){statesPanelHome.option3();}
    public void option4(){statesPanelHome.option4();}
    public void option5(){statesPanelHome.option5();}
    public void option6(){statesPanelHome.option6();}
    public void option7(){statesPanelHome.option7();}
    public void option8(){statesPanelHome.option8();}
    public void option9(){statesPanelHome.option9();}

    ////////////////CONTROLLER//////////////////


    public StatesPanelHome getStatesPanelHome() {
        return statesPanelHome;
    }

    public void setStatesPanelHome(StatesPanelHome statesPanelHome) {
        this.statesPanelHome = statesPanelHome;
        statesPanelHome.addPanelHome(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PanelHome panelHome = (PanelHome) o;

        return home.equals(panelHome.home);
    }


    @Override
    public void avanzar(@NotNull UnitsPlayer u1, int i) throws IOException {
        if (home == u1){
            this.statesPanelHome = new Select_player_PanelHome(this,u1,i);
        }
        else{super.avanzar(u1, i);}
    }

    @Override
    public void activator(@NotNull UnitsPlayer u1) {
        this.unitPlayer(u1);
        super.activator(u1);
        int i = u1.getLvlNorma();
        this.normaCheck(u1);
        if (i < u1.getLvlNorma()){this.notifier();}
        u1.setHpActual(u1.getHpActual() + 1);

    }

    @Override
    public void attach(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void notifier() {
        observer.update();
    }
}
