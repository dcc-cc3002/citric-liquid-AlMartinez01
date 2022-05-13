package cl.uchile.dcc.citricliquid.model.paneles;

import cl.uchile.dcc.citricliquid.model.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.abstracto.Units;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

import java.util.Arrays;

public class Panel {
    private UnitsPlayer[] units;//Unidades en el panel
    private Panel[] nexts;//Los Paneles con los que esta unido o se puede continuar
    private Carts carta;//Carta en el panel


    public Panel(UnitsPlayer[] units, Panel[] nexts, Carts carta) {
        this.units = units;
        this.nexts = nexts;
        this.carta = carta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Panel panel = (Panel) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(units, panel.units)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(nexts, panel.nexts)) return false;
        return carta != null ? carta.equals(panel.carta) : panel.carta == null;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(units);
        result = 31 * result + Arrays.hashCode(nexts);
        result = 31 * result + (carta != null ? carta.hashCode() : 0);
        return result;
    }

    /**
     * dice si una unidad existe o no
     * @return
     */
    public boolean unitExist(Units u1){
        for (Units i : this.units){
            if (u1.equals(i)){return true;}
        }
        return false;
    }

    public int unitUbi(Units u1){
        if (!this.unitExist(u1)){return -1;}
        int i = 0;
        for (Units u : this.units){
            if (u1.equals(i)){return i;}
            i++;
        }
        return -1;
    }
}
