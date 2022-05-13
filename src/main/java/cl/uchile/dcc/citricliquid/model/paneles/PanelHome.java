package cl.uchile.dcc.citricliquid.model.paneles;


import cl.uchile.dcc.citricliquid.model.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

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
}
