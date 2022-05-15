package cl.uchile.dcc.citricliquid.model.paneles;

import cl.uchile.dcc.citricliquid.model.Player;
import cl.uchile.dcc.citricliquid.model.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.abstracto.Units;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
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
    public boolean unitExist(@NotNull Units u1){
        for (Units i : this.units){
            if (u1.equals(i)){return true;}
        }
        return false;
    }

    /**
     * encuentra la ubicacion de un player
     * @param u1
     * @return
     */
    public int unitUbi(@NotNull Units u1){
        if (!this.unitExist(u1)){return -1;}
        int i = 0;
        for (Units u : this.units){
            if (u1.equals(u)){return i;}
            i++;
        }
        return -1;
    }
    public int cantUnits(){
        int i = 0;
        for (UnitsPlayer J : this.units){i++;}
        return i;
    }
    public void agrePlayer(@NotNull UnitsPlayer u1){
        int i = this.cantUnits();
        UnitsPlayer[] newUnits;
        newUnits = new UnitsPlayer[i+1];
        int t = 0;
        for (UnitsPlayer players : this.units){
            newUnits[t] = this.units[t];
            t++;
        }
        newUnits[t] = u1;
        this.units = newUnits;
    }

    public void activator(@NotNull UnitsPlayer u1){
        this.agrePlayer(u1);
        return;
    }

    public int cant_next(){
        if (this.nexts == null){return 0;}
        int j = 0;
        for (Panel i : this.nexts){
            j++;
        }
        return j;
    }
    public void printAvanzar(){
        int i = 0;
        System.out.print("Tiene las siguiente opciones: \n");
        for (Panel j : this.nexts){
            System.out.print(j + " " + i + "| \n");
        }
    }
    public Panel avanzar(@NotNull UnitsPlayer u1,@NotNull int i) throws IOException {
        if (i == 0){
            this.activator(u1);
            return this;
        }
        if (this.cant_next() != 1){
            printAvanzar();
            int read = System.in.read();
            return (this.nexts[read]).avanzar(u1,i-1);
        }
        return this.nexts[0].avanzar(u1,i-1);
    }

    @Override
    public String toString() {
        return "Panel{" +
                "units=" + Arrays.toString(units) +
                ", nexts=" + Arrays.toString(nexts) +
                '}';
    }
}
