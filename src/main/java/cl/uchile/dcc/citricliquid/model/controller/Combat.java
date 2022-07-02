package cl.uchile.dcc.citricliquid.model.controller;

import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Units;


public class Combat {
    Units unit1;
    Units unit2;

    public Combat(Units unit1, Units unit2) {
        this.unit1 = unit1;
        this.unit2 = unit2;
    }
    public class ClearConsoleScreen {
        public static void main(String[] args){
            System.out.print("Everything on the console will cleared");
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    public void starter(){
        unit1.initio_combat();
        unit2.initio_combat();

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
            unit2.addStars(unit1.loot());
        }
        else{
            System.out.printf(unit2.getId()+ "a caido!!\n");
            unit1.addStars(unit2.loot());
        }
    }
}
