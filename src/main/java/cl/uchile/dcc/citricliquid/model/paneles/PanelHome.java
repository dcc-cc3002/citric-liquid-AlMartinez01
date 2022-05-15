package cl.uchile.dcc.citricliquid.model.paneles;


import cl.uchile.dcc.citricliquid.model.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class PanelHome extends Panel {
    UnitsPlayer home;

    public PanelHome(UnitsPlayer[] units, Panel[] nexts, Carts carta, UnitsPlayer home) {
        super(units, nexts, carta);
        this.home = home;
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
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + home.hashCode();
        return result;
    }

    @Override
    public Panel avanzar(@NotNull UnitsPlayer u1, @NotNull int i) throws IOException {
        if (u1.equals(home)){
            System.out.print("deternerse (1) o seguir (0):");
            int read = System.in.read();
            while (read != 0 && read != 1){
                System.out.print(" \n error reintentar. \n deternerse (1) o seguir (0):");
            }
            System.out.print("\n");
            if (read ==1){
                this.activator(u1);
            }
        }
        return super.avanzar(u1, i);
    }

    @Override
    public void activator(@NotNull UnitsPlayer u1) {
        u1.setHpActual(u1.getHpActual() + 1);

        super.activator(u1);
    }
}
