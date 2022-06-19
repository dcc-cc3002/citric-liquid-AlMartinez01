package cl.uchile.dcc.citricliquid.model.paneles;


import cl.uchile.dcc.citricliquid.model.paneles.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;

public class PanelEncounter extends Panel {
    private UnitsEnemy enemy_default;
    private UnitsEnemy enemy_actual;

    public void setEnemy_default(UnitsEnemy enemy_default) {
        this.enemy_default = enemy_default;
    }

    public PanelEncounter(UnitsPlayer[] units, Panel[] nexts, Carts carta, UnitsEnemy enemy_default) {
        super(units, nexts, carta);
        this.enemy_default = enemy_actual = enemy_default;
    }

    public UnitsEnemy getEnemy_actual() {
        return enemy_actual;
    }

    public void setEnemy_actual(UnitsEnemy enemy_actual) {
        this.enemy_actual = enemy_actual;
    }

    public UnitsEnemy getEnemy_default() {
        return enemy_default;
    }

    public void agregarwild(@NotNull UnitsEnemy wild){
        if (!(wild.isBoss())){
            this.enemy_default = wild;
            setEnemy_actual(wild);
        }
    }

    public void respawn_wild(){
        this.enemy_actual = this.enemy_default;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PanelEncounter that = (PanelEncounter) o;
        return enemy_default != null ? enemy_default.equals(that.enemy_default) : that.enemy_default == null;
    }


}
