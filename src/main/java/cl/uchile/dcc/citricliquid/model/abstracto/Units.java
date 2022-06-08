package cl.uchile.dcc.citricliquid.model.abstracto;

import java.util.Objects;
import java.util.Random;

public abstract class Units {
    private final Random random;
    private final String id;//Para diferenciar a las unidades (funciona como nombre)
    private final int hpMax;
    private int hpActual;
    private final int atk;
    private final int def;
    private final int evd;



    public Units(String id, int hpMax, int atk, int def, int evd) {
        random = new Random();
        this.id = id;
        this.hpMax = hpActual = hpMax;
        this.atk = atk;
        this.def = def;
        this.evd = evd;
    }
    /**##########################SETTERS AND GETTER######################*/
    public String getId() {
        return id;
    }
    public int getHpMax() {
        return hpMax;
    }
    public int getHpActual() {
        return hpActual;
    }
    public void setHpActual(int hpActual) {
        this.hpActual = hpActual;
    }
    public int getAtk() {
        return atk;
    }
    public int getDef() {
        return def;
    }
    public int getEvd() {
        return evd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Units units = (Units) o;

        if (hpMax != units.hpMax) return false;
        if (hpActual != units.hpActual) return false;
        if (atk != units.atk) return false;
        if (def != units.def) return false;
        if (evd != units.evd) return false;
        return Objects.equals(id, units.id);
    }

    public void setSeed(final long seed) {
        random.setSeed(seed);
    }

    /**
     * entrega un lanzamiento de dado
     */
    public int roll() {
        return random.nextInt(6) + 1;
    }

    /**
     * determina si una unidad esta muerta o no.
     */
    /**
     * dependera del tipo de unidad el botin a entregar
     */
    public int loot(){return 0;}
    /**
     * funcion que devuelve el ataque del agresor
     */
    public int attack() {
        return this.roll() + this.getAtk();
    }

    /**
     * funcion que recibe un ataque y actua "defendiendose"
     */
    public void defense(int damage) {
        int i = damage - (this.roll() + this.getDef());
        if (i < 1){i = 1;}
        this.setHpActual(this.getHpActual()-i);
        System.out.print("daño recibido: " + i + "\n");
    }

    /**
     * funcion que recibe un ataque y trata de esquivar
     */
    public void dodge(int damage) {
        int i = this.roll() + this.getEvd();
        if (i < damage){
            this.setHpActual(this.getHpActual()-i);
            System.out.print("DODGE fallido, se recibio un daño de: " + damage + "\n");
        }
        else {
            System.out.print("DODGE" + "\n");
        }
    }// SE LE DIO AL BOSS Y WILD LA CAPACIDAD DE DODGEAR PERO NO LO HARA.
    public void combat(Units enemy){
           this.initio_combat();
           enemy.initio_combat();
    }

    public void initio_combat(){
        System.out.print("personaje no valido para inicio de combate\n");
    }

    @Override
    public String toString() {
        return "Units{" +
                "id='" + id + '\'' +
                '}';
    }
}
