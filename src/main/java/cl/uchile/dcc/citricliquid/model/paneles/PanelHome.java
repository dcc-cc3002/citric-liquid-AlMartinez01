package cl.uchile.dcc.citricliquid.model.paneles;


import cl.uchile.dcc.citricliquid.model.controller.Transferencia.Observable;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.Observer;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanels.StatesPanelHome.Select_player_PanelHome;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanels.StatesPanel;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.SelectNorma;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

public class PanelHome extends Panel implements Observable {
    private UnitsPlayer home;
    private Observer observer;

    public PanelHome(UnitsPlayer[] units, Panel[] nexts, Carts carta, UnitsPlayer home) {
        super(units, nexts, carta);
        this.home = home;
    }
    public PanelHome(){
        super();
        this.home = null;
    }

    public UnitsPlayer getHome() {
        return home;
    }

    public void setHome(UnitsPlayer home) {
        this.home = home;
    }

    private void normaCheck(@NotNull UnitsPlayer u1){
        int lvlPlayer = u1.getLvlNorma();
        if (!u1.getPrefNorma()){
            switch (lvlPlayer){
                case 1 -> {if (u1.getStars() >= 10) {u1.setLvlNorma(2);}}
                case 2 -> {if (u1.getStars() >= 30) {u1.setLvlNorma(3);}}
                case 3 -> {if (u1.getStars() >= 70) {u1.setLvlNorma(4);}}
                case 4 -> {if (u1.getStars() >= 120) {u1.setLvlNorma(5);}}
                case 5 -> {if (u1.getStars() >= 200) {u1.setLvlNorma(6);}}
            }
        }
        else {
            switch (lvlPlayer){
                case 2 -> {if (u1.getWins() >= 2) {u1.setLvlNorma(3);}}
                case 3 -> {if (u1.getWins() >= 5) {u1.setLvlNorma(4);}}
                case 4 -> {if (u1.getWins() >= 9) {u1.setLvlNorma(5);}}
                case 5 -> {if (u1.getWins() >= 14) {u1.setLvlNorma(6);}}
            }
        }
    }

    public StatesPanel getStatesPanelHome() {
        return statesPanel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PanelHome panelHome = (PanelHome) o;

        if (!Objects.equals(home, panelHome.home)) return false;
        return Objects.equals(observer, panelHome.observer);
    }

    @Override
    public void avanzar(@NotNull UnitsPlayer u1, int i) throws IOException {
        if (home == u1){
            this.statesPanel = new Select_player_PanelHome(this,u1,i);
        }
        else{super.avanzar(u1, i);}
    }

    @Override
    public void activator(@NotNull UnitsPlayer u1) {
        int i = u1.getLvlNorma();
        this.unitPlayer(u1);
        this.normaCheck(u1);
        u1.setHpActual(u1.getHpActual() + 1);
        if (u1.getLvlNorma() == 6){notifier();}
        else if (i < u1.getLvlNorma()){
            notifier();
            u1.setStatesPlayer(new SelectNorma());
            return;
        }
        notifierEvent();
    }

    @Override
    public void attach(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void notifier() {
        if (observer==null){
            return;
        }
        observer.update();
    }

    public Observer getObserver() {
        return observer;
    }

    @Override
    public String toString() {
        return "PanelHome{" +
                "home=" + home +
                '}';
    }
}
