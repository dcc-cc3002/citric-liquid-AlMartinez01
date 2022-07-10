package cl.uchile.dcc.citricliquid.model.paneles;


import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PanelEncounter extends Panel {
    private UnitsEnemy enemy_actual;

    public PanelEncounter(UnitsPlayer[] units, Panel nexts, Carts carta, UnitsEnemy enemy_default) {
        super(units, nexts, carta);
        this.enemy_actual = enemy_default;
    }

    public UnitsEnemy getEnemy_actual() {
        return enemy_actual;
    }

    public void setEnemy_actual(UnitsEnemy enemy_actual) {
        this.enemy_actual = enemy_actual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PanelEncounter that = (PanelEncounter) o;

        return Objects.equals(enemy_actual, that.enemy_actual);
    }

}
