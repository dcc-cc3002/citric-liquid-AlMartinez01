package cl.uchile.dcc.citricliquid.model.controller.SistemaCombate;

import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.StatesCombat.AutomaticState;
import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.StatesCombat.OptionsCombat;
import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.StatesCombat.SelectionDecisionPlayer;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Units;

public class Combat {


    public Units unit1;
    public Units unit2;
    private OptionsCombat state;

    public Combat(UnitsPlayer unit1, UnitsEnemy unit2) {
        this.unit1 = unit1;
        this.unit2 = unit2;
        this.state = new AutomaticState();
        state.setCombat(this);
    }
    //*********GETTERS AND SETTERS
    public OptionsCombat getState() {
        return state;
    }
    public void setState(OptionsCombat state) {
        this.state = state;
        state.setCombat(this);
    }
    //*********GETTERS AND SETTERS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Combat combat = (Combat) o;

        if (unit1 != null ? !unit1.equals(combat.unit1) : combat.unit1 != null) return false;
        return unit2 != null ? unit2.equals(combat.unit2) : combat.unit2 == null;
    }

    public void starter(UnitsPlayer p1,UnitsEnemy E1){
        p1.attack(E1); //ACCION AUTOMATICA

        E1.attack(p1); //Con esto el player entrara en eleccion de accion
        init_attack(p1);
    }
    public void init_attack(UnitsPlayer u1){
        this.state = new SelectionDecisionPlayer(this,u1);
    }
    public void option0(){
        state.option0();
    }
    public void option1(){
        state.option1();
    }



}
