package cl.uchile.dcc.citricliquid.model.paneles;


import cl.uchile.dcc.citricliquid.model.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

import java.util.Objects;

public class Panelboss extends PanelEncounter {

    private final UnitsEnemy boss_default;
    private UnitsEnemy boss_actual;

    public Panelboss(UnitsPlayer[] units, Panel[] nexts, Carts carta, UnitsEnemy enemy_default, UnitsEnemy boss_default) {
        super(units, nexts, carta, enemy_default);
        this.boss_default = boss_actual = boss_default;
    }

    public void respawn_boss(){
        this.boss_actual = this.boss_default;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Panelboss panelboss = (Panelboss) o;

        if (!boss_default.equals(panelboss.boss_default)) return false;
        return Objects.equals(boss_actual, panelboss.boss_actual);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + boss_default.hashCode();
        result = 31 * result + (boss_actual != null ? boss_actual.hashCode() : 0);
        return result;
    }
}
