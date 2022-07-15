package cl.uchile.dcc.citricliquid.model.paneles;


import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;

public class PanelDrawn extends Panel {
    public PanelDrawn(UnitsPlayer[] units, Panel nexts, Carts carta) {
        super(units, nexts, carta);
    }

    @Override
    public void activator(@NotNull UnitsPlayer u1) {
        this.unitPlayer(u1);
        super.activator(u1);
    }
}
