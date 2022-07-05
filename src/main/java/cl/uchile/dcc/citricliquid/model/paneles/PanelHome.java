package cl.uchile.dcc.citricliquid.model.paneles;


import cl.uchile.dcc.citricliquid.model.controller.GameController;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.Observable;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.Observer;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Scanner;

public class PanelHome extends Panel implements Observable {
    UnitsPlayer home;
    Observer observer;

    public PanelHome(UnitsPlayer[] units, Panel[] nexts, Carts carta, UnitsPlayer home) {
        super(units, nexts, carta);
        this.home = home;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PanelHome panelHome = (PanelHome) o;

        return home.equals(panelHome.home);
    }


    @Override
    public Panel avanzar(@NotNull UnitsPlayer u1, int i) throws IOException {
        if (u1.equals(home)){
            Scanner entrada = new Scanner(System.in);
            System.out.println("hola "+home.getId()+ " desea quedarse en el panel? si(true)| no(false): ");
            System.out.println("\n");
            boolean quedarse = entrada.nextBoolean();
            if (quedarse){
                this.activator(u1);
                return this;
            }
        }
        return super.avanzar(u1, i);
    } /** NO PROBABLE PERDONENME!!!
    */

    @Override
    public void activator(@NotNull UnitsPlayer u1) {
        int i = u1.getLvlNorma();
        this.normaCheck(u1);
        if (i < u1.getLvlNorma()){notifier();}
        u1.setHpActual(u1.getHpActual() + 1);
        super.activator(u1);
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
