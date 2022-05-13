package cl.uchile.dcc.citricliquid.model.paneles;


import cl.uchile.dcc.citricliquid.model.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

public class PanelEncounter extends Panel {
    private UnitsEnemy enemy_default;
    private UnitsEnemy enemy_actual;
    public PanelEncounter(UnitsPlayer[] units, Panel[] nexts, Carts carta, UnitsEnemy enemy_default) {
        super(units, nexts, carta);
        this.enemy_default=enemy_actual = enemy_default;
    }

    public void respawn_wild(){ //Restaura el enemigo de la celda
        this.enemy_actual = this.enemy_default;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PanelEncounter that = (PanelEncounter) o;

        if (!enemy_default.equals(that.enemy_default)) return false;
        return enemy_actual != null ? enemy_actual.equals(that.enemy_actual) : that.enemy_actual == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + enemy_default.hashCode();
        result = 31 * result + (enemy_actual != null ? enemy_actual.hashCode() : 0);
        return result;
    }
}
