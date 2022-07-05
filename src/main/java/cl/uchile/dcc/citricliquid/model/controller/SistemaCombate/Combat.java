package cl.uchile.dcc.citricliquid.model.controller.SistemaCombate;

import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Units;

public class Combat {


    Units unit1;
    Units unit2;

    public Combat(Units unit1, Units unit2) {
        this.unit1 = unit1;
        this.unit2 = unit2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Combat combat = (Combat) o;

        if (unit1 != null ? !unit1.equals(combat.unit1) : combat.unit1 != null) return false;
        return unit2 != null ? unit2.equals(combat.unit2) : combat.unit2 == null;
    }

    public void starter(){
        unit1.initio_combat();
        unit2.initio_combat();
        try {
            //Ponemos a "Dormir" el programa durante los ms que queremos
            Thread.sleep(2*1000);
        } catch (Exception e) {
            System.out.println(e);
        }
        while (unit1.deadUnit() && unit2.deadUnit()){
            System.out.printf("\n"+unit1.getId() + " atacara!!!!\n");
            unit1.attack(unit2);
            System.out.printf("vida actual de j1: " + unit1.getHpActual() +" |vida actual de j2: "  + unit2.getHpActual()+"\n");
            if (unit2.deadUnit()) {
                System.out.printf("\n" + unit2.getId() + " atacara!!!!\n");
                unit2.attack(unit1);
                System.out.printf("vida actual de j1: " + unit1.getHpActual() + " |vida actual de j2: " + unit2.getHpActual() + "\n");
            }
        }


        if (!(unit1.deadUnit())){
            System.out.printf(unit1.getId()+ "a caido!!\n");
            unit2.victory(unit1.defeat());
        }
        else{
            System.out.printf(unit2.getId()+ "a caido!!\n");
            unit1.victory(unit2.defeat());
        }
    }//FUNCION NO TESTEABLE//
}
