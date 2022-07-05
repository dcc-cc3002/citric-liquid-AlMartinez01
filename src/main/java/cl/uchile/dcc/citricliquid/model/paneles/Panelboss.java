package cl.uchile.dcc.citricliquid.model.paneles;


import cl.uchile.dcc.citricliquid.model.controller.Transferencia.Observable;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.Observer;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;

public class Panelboss extends PanelEncounter implements Observer {
    private UnitsEnemy boss_actual;
    private boolean boss = false;

    public Panelboss(UnitsPlayer[] units, Panel[] nexts, Carts carta, UnitsEnemy enemy_default, UnitsEnemy boss_default) {
        super(units, nexts, carta, enemy_default);
        this.boss_actual = boss_default;
    }
    public void agregarBoss(@NotNull UnitsEnemy boss){
        if (boss.isBoss()){
            this.boss_actual = boss;
        }
    }

    public UnitsEnemy getBoss_actual() {
        return boss_actual;
    }

    public void setBoss_actual(UnitsEnemy boss_actual) {
        this.boss_actual = boss_actual;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Panelboss panelboss = (Panelboss) o;

        return boss_actual != null ? boss_actual.equals(panelboss.boss_actual) : panelboss.boss_actual == null;
    }

    @Override
    public void update() {
        this.boss = true;
    }

    public boolean getBoss() {
        return boss;
    }
}
