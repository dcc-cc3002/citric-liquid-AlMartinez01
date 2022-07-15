package cl.uchile.dcc.citricliquid.model.controller.SistemaCombate;

import cl.uchile.dcc.citricliquid.model.controller.Transferencia.FinishedEvent.ObservableEvent;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.FinishedEvent.ObserverEvent;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

import java.util.Objects;

public class CombatPlayers implements ObservableEvent, ObserverEvent{


    public UnitsPlayer unit1;
    public UnitsPlayer unit2;
    private ObserverEvent observerEvent;

    private boolean ata1 = false;

    public CombatPlayers(UnitsPlayer unit1, UnitsPlayer unit2, ObserverEvent observerEvent) {
        System.out.println("iniciara el combate entre: "+unit1.getId()+" vs!! "+unit2.getId()+"\n");
        this.unit1 = unit1;
        this.unit2 = unit2;
        attachEvent(observerEvent);
    }

    public CombatPlayers(UnitsPlayer unit1, UnitsPlayer unit2) {
        System.out.println("iniciara el combate entre: "+unit1.getId()+" vs!! "+unit2.getId()+"\n");
        this.unit1 = unit1;
        this.unit2 = unit2;
        attachEvent(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CombatPlayers that = (CombatPlayers) o;
        return ata1 == that.ata1 && Objects.equals(unit1, that.unit1) && Objects.equals(unit2, that.unit2) && Objects.equals(observerEvent, that.observerEvent);
    }

    public void starter(){
        unit2.attachEvent(this);
        unit1.attachEvent(this);
        unit1.attack(unit2);
    }

    public void finished(){
        if (unit1.deadUnit()){
            System.out.println("\n" +  unit1.getId() + " a caido\n");
            unit2.victory(unit1.defeat());
        }
        else if (unit2.deadUnit()){
            System.out.println("\n" +unit2.getId() + " a caido\n");
            unit1.victory(unit2.defeat());
        }
        else{
            System.out.println("\n conflicto terminado sin muertes\n");
        }
        notifierEvent(); //EL EVENTO EN ESTE CASO SERIA TERMINAR EL COMBATE
        unit1.attachEvent(null);
        unit2.attachEvent(null);
    }


    @Override
    public void attachEvent(ObserverEvent observer) {
        this.observerEvent = observer;
    }

    @Override
    public void notifierEvent() {
        if (this.observerEvent == null){return;}
        this.observerEvent.updateEvent();
    }

    @Override
    public void updateEvent() {
        if (unit2.deadUnit() || unit1.deadUnit()){this.finished();}
        else if (!ata1){
            unit2.attack(unit1);
            ata1 = true;
        }
        else{finished();}
    }
}
