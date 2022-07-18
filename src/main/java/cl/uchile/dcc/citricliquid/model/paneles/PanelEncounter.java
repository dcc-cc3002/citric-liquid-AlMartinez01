package cl.uchile.dcc.citricliquid.model.paneles;

import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.CombatEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public class PanelEncounter extends Panel {
    Random random;

    public void setSeed(long number) {
        random = new Random(number);
    }

    private UnitsEnemy enemy_actual;
    public PanelEncounter(UnitsPlayer[] units, Panel[] nexts, Carts carta, UnitsEnemy enemy_default) {
        super(units, nexts, carta);
        this.enemy_actual = enemy_default;
        random = new Random();
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

    @Override
    public void activator(@NotNull UnitsPlayer u1) {
        if (enemy_actual == null){restoredWild();}
        if (enemy_actual.deadUnit()){restoredWild();}
        this.unitPlayer(u1);
        CombatEnemy combat;
        combat = new CombatEnemy();
        combat.setCombat(u1,enemy_actual,this);
        combat.starter();
    }

    public void restoredWild(){
        int i = random.nextInt(3);
        switch (i){
            case 0 -> enemy_actual = new UnitsEnemy("Chicken",3,-1,-1,1,false,0);
            case 1 -> enemy_actual = new UnitsEnemy("Robo Ball",3,-1,1,-1,false,0);
            case 2 -> enemy_actual = new UnitsEnemy("Seagull",3,1,-1,-1,false,0);
        }
    }

}
