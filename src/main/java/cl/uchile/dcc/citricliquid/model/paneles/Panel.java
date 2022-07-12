package cl.uchile.dcc.citricliquid.model.paneles;

import cl.uchile.dcc.citricliquid.model.controller.GameController;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Units;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class Panel {
    private UnitsPlayer[] units;//Unidades en el panel
    private Panel nexts;//Los Paneles con los que esta unido o se puede continuar
    private Carts carta;//Carta en el panel

    public Panel(UnitsPlayer[] units, Panel nexts, Carts carta) {
        this.units = units;
        this.nexts = nexts;
        this.carta = carta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Panel panel = (Panel) o;
        return Arrays.equals(units, panel.units) && Objects.equals(nexts, panel.nexts) && Objects.equals(carta, panel.carta);
    }

    public Panel getNexts() {
        return nexts;
    }

    public UnitsPlayer[] getUnits() {
        return units;
    }

    /**
     * dice si una unidad existe o no
     * @return boolean
     */
    public boolean unitExist(@NotNull Units u1){
        if (this.units == null){return false;}
        for (Units i : this.units){
            if (u1.equals(i)){return true;}
        }
        return false;
    }

    /**
     * encuentra la ubicacion de un player
     * @return int
     */
    public int unitUbi(@NotNull Units u1){
        if (!this.unitExist(u1)){return -1;}
        int i = 0;
        for (Units u : this.units){
            if (u1.equals(u)){return i;}
            i++;
        }
        return i;
    }
    public int cantUnits(){
        if (units == null){return 0;}
        int i = 0;
        for (UnitsPlayer ignored : this.units){i++;}
        return i;
    }

    /**
     * agrega un player a la lista del panel
     * @param u1
     */
    public void agrePlayer(@NotNull UnitsPlayer u1){
        if (units == null){this.units = new UnitsPlayer[]{u1};}
        else {
            UnitsPlayer[] newUnits;
            newUnits = new UnitsPlayer[this.cantUnits() + 1];
            int t = 0;
            for (UnitsPlayer players : this.units) {
                newUnits[t] = players;
                t++;
            }
            newUnits[t] = u1;
            this.units = newUnits;
        }
    }

    public void deletedPlayer(UnitsPlayer player){
        if (!(this.unitExist(player))){return;}
        int r = this.cantUnits()-1;
        if (r == 0){this.units = null; return;}
        UnitsPlayer[] players = new UnitsPlayer[this.cantUnits()-1];
        if (this.unitUbi(player) >= 0) System.arraycopy(this.units, 0, players, 0, this.unitUbi(player));
        if (this.cantUnits() - (this.unitUbi(player) + 1) >= 0)
            System.arraycopy(this.units, this.unitUbi(player) + 1, players, this.unitUbi(player) + 1 - 1, this.cantUnits() - (this.unitUbi(player) + 1));
        this.units = players;
    }
    /**
     * se agregan mutuamente
     */
    public void unitPlayer(@NotNull UnitsPlayer u1){
        this.agrePlayer(u1);
        u1.setUbi(this);
    }
    /**
     * activa el panel
     */
    public void activator(@NotNull UnitsPlayer u1){
        this.unitPlayer(u1);
    }

    public void avanzar(@NotNull UnitsPlayer u1, int i) throws IOException {
        if (nexts == null || i == 0){this.activator(u1);}
        else{
            this.nexts.avanzar(u1,i-1);
        }
    }

    public void addNextPanel(final Panel panel) {
        nexts = panel;
    }
}
