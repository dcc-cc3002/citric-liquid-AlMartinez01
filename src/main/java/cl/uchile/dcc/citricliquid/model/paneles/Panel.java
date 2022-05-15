package cl.uchile.dcc.citricliquid.model.paneles;

import cl.uchile.dcc.citricliquid.model.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.abstracto.Units;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

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
        return Objects.equals(carta, panel.carta);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(units);
        result = 31 * result + Arrays.hashCode(nexts);
        result = 31 * result + (carta != null ? carta.hashCode() : 0);
        return result;
    }

    public Panel[] getNexts() {
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
        return -1;
    }
    public int cantUnits(){
        if (units == null){return 0;}
        int i = 0;
        for (UnitsPlayer ignored : this.units){i++;}
        return i;
    }
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


    /**
     * se agregan mutuamente
     */
    public void unitPlayer(@NotNull UnitsPlayer u1){
        this.agrePlayer(u1);
    }
    /**
     * activa el panel
     */
    public void activator(@NotNull UnitsPlayer u1){
        this.agrePlayer(u1);
    }

    public int cant_next(){
        if (this.nexts == null){return 0;}
        int j = 0;
        for (Panel ignored : this.nexts){
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
    public Panel avanzar(@NotNull UnitsPlayer u1, int i) throws IOException {
        if (nexts == null){return this;}
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

    public void addNextPanel(final Panel panel) {
        if (nexts == null) {nexts = new Panel[]{panel};}
        else{
            Panel[] newPanel = new Panel[this.cant_next()+1];
            int j = 0;
            for (Panel panel1 : nexts){
                newPanel[j] = panel1;
                j++;
            }
            newPanel[j] = panel;
            this.nexts = newPanel;
        }
    }
}
