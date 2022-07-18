package cl.uchile.dcc.citricliquid.model.paneles;


import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.CombatEnemy;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.BossEvent.Informer;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.BossEvent.Solicitor;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.FinishedEvent.ObserverEvent;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.Observable;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.Observer;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Standby_mode_Player;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Panelboss extends PanelEncounter implements Observer, Solicitor {
    private UnitsEnemy boss_actual;
    private boolean boss = false;

    Informer informer;

    public Panelboss(UnitsPlayer[] units, Panel nexts, Carts carta, UnitsEnemy enemy_default, UnitsEnemy boss_default) {
        super(units, nexts, carta, enemy_default);
        this.boss_actual = boss_default;
        this.informer = null;
    }

    public void setInformer(Informer informer) {
        this.informer = informer;
    }

    public UnitsEnemy getBoss_actual() {
        if (informer == null)return boss_actual;
        return solicitar(informer);
    }

    public boolean isBoss() {
        return boss;
    }

    public Informer getInformer() {
        return informer;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
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

        return Objects.equals(boss_actual, panelboss.boss_actual);
    }

    @Override
    public void update() {
        this.boss = true;
    }

    public boolean getBoss() {
        return boss;
    }

    @Override
    public void activator(@NotNull UnitsPlayer u1) {
        if (informer==null && boss){
            this.unitPlayer(u1);
            CombatEnemy combatEnemy = new CombatEnemy(u1,getBoss_actual(),this);
            combatEnemy.starter();
        }
        else if (boss && !getBoss_actual().deadUnit()){
            this.unitPlayer(u1);
            CombatEnemy combatEnemy = new CombatEnemy(u1,solicitar(informer),this);
            combatEnemy.starter();
        }
        else {
            super.activator(u1);
        }
    }


    @Override
    public UnitsEnemy solicitar(Informer informer) {
        if (informer == null)return  boss_actual;
        return informer.send();
    }
}
