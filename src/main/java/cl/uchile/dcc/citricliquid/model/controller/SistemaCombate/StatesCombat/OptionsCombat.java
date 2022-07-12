package cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.StatesCombat;

import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.Combat;

public interface OptionsCombat {
    void option0();
    void option1();
    void setCombat(Combat combat);
    Combat getCombat();
}
