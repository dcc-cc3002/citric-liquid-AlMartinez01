package cl.uchile.dcc.citricliquid.model.paneles;


import cl.uchile.dcc.citricliquid.model.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

public class PanelNeutral extends Panel {
    public PanelNeutral(UnitsPlayer[] units, Panel[] nexts, Carts carta) {
        super(units, nexts, carta);
    }
}
