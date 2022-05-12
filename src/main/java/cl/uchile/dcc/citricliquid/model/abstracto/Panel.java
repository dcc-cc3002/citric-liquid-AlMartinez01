package cl.uchile.dcc.citricliquid.model.abstracto;

import java.io.IOException;
import java.util.Arrays;

public abstract class Panel {
    private int[] ubic;//Ubicacion del panel
    private Units[] units;//Unidades en el panel
    private int cantUnit;
    private Panel[] nexts;//Los Paneles con los que esta unido o se puede continuar
    private Carts carta;//Carta en el panel


    public Panel(int[] ubic, Units[] units, int cantUnit, Panel[] nexts, Carts carta) {
        this.ubic = ubic;
        this.units = units;
        this.cantUnit = cantUnit;
        this.nexts = nexts;
        this.carta = carta;
    }


    public int ubic(Units u1){//retorna la ubicacion de una unidad
        int i = 0;
        for(Units unit:this.units){
            if (unit.equals(u1)){
                return i;
            }
            i++;
        }
        return -1;
    }

    public void elim(Units u1){//elimina una unidad de la lista
        int i = this.ubic(u1);

        if (i == -1){
            return; //no se puede eliminar algo que no esta en la list
        }

        Units[] units1;
        units1 = new Units[this.cantUnit-1];
        if (i >= 0) System.arraycopy(this.units, 0, units1, 0, i);
        if (this.units.length - (i + 1) >= 0)
            System.arraycopy(this.units, i + 1, units1, i + 1 - 1, this.units.length - (i + 1));
        this.units = units1;
        this.cantUnit = cantUnit - 1;
    }

    public Panel avanzar(int i) throws IOException {
        if (i!=0){
            if (1 == this.nexts.length){
                return avanzar(i-1);
            }
            System.out.print(this.toStringPanels());
            System.out.print("seleccione cual camino seguir");
            int read = System.in.read();
            return this.nexts[read].avanzar(i - 1);
        }
        return this;
    }

    public void activar(Units u1){

    }

    public String toStringPanels() {
        String strre;
        strre = new String("disp");
        for (int i = 0; i < this.cantUnit ; i++ ){
            strre =  strre + this.nexts[i] + (i+1);
        }
        return "Paneles disponibles" + Arrays.toString(nexts);
    }
}
