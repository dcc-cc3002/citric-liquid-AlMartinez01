package cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.StatesCombat;

import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.Combat;
import cl.uchile.dcc.citricliquid.model.paneles.Panel;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Units;

public class SelectionDecisionPlayer implements OptionsCombat{
    public Combat combat;
    public UnitsPlayer u1;

    public SelectionDecisionPlayer(Combat combat, UnitsPlayer u1) {
        this.combat = combat;
        this.u1 = u1;
    }

    @Override
    public void option0() {
        u1.option0();
        combat.setState(new AutomaticState());
    }

    @Override
    public void option1() {
        u1.option1();
        combat.setState(new AutomaticState());
    }

    @Override
    public void setCombat(Combat combat) {
        this.combat = combat;
    }

    @Override
    public Combat getCombat() {
        return this.combat;
    }
}
