package cl.uchile.dcc.citricliquid.model.unidades.abstracto;

import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.Attackable;
import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.Attacker;
import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.Initio_combat;

import java.util.Objects;
import java.util.Random;

public abstract class Units implements Initio_combat, Attacker, Attackable {
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

    public Random getRandom() {
        return random;
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

    public void addStars(int stars){
    }

    /**
     * entrega un lanzamiento de dado
     */
    public int roll() {return random.nextInt(6) + 1;
    }

    /**
     * determina si una unidad esta muerta o no.
     */
    /**
     * funcion que devuelve el ataque del agresor
     */
    public int attack() {
        int dado = this.roll() + this.getAtk();
        System.out.printf("salio: " +dado +"\n");
        return dado;
    }
    public int  loot(){
        return 0;
    }

    /**
     * funcion que recibe un ataque y actua "defendiendose"
     */
    public void defense(int damage) {
        int i = Math.max(damage - (this.roll() + this.getDef()),1);
        this.setHpActual(Math.max(0,this.getHpActual()-i));
        System.out.print("daño recibido: " + i + "\n");
    }
    /**
     * funcion que recibe un ataque y trata de esquivar
     */
    public void dodge(int damage) {
        int i = this.roll() + this.getEvd();
        if (i < damage){
            this.setHpActual(Math.max(0,this.getHpActual()-damage));
            System.out.print("DODGE fallido, se recibio un daño de: " + damage + "\n");
        }
        else {
            System.out.print("DODGE" + "\n");
        }
    }

    public boolean deadUnit(){
        return (this.getHpActual() == 0);
    }

    @Override
    public String toString() {
        return "Units{" +
                "random=" + random +
                ", id='" + id + '\'' +
                ", hpMax=" + hpMax +
                ", hpActual=" + hpActual +
                ", atk=" + atk +
                ", def=" + def +
                ", evd=" + evd +
                '}';
    }
}
