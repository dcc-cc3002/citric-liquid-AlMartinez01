package cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.StatesCombat;

import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.Combat;

public class AutomaticState implements OptionsCombat{
    public Combat combat;

    @Override
    public void option0() {

    }

    @Override
    public void option1() {

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
