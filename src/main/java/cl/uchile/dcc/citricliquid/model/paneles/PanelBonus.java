package cl.uchile.dcc.citricliquid.model.paneles;


import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;


public class PanelBonus extends Panel {
    public PanelBonus(UnitsPlayer[] units, Panel[] nexts, Carts carta) {
        super(units, nexts, carta);
    }
    private int bonus(UnitsPlayer u1){
        return(u1.roll() * Math.min(u1.getLvlNorma(), 3));
    }
    @Override
    public void activator(@NotNull UnitsPlayer u1) {
        u1.incrementStars(bonus(u1));
        super.activator(u1);
    }
}
