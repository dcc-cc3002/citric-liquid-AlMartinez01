package cl.uchile.dcc.citricliquid.model.abstracto;

import java.util.Random;

public abstract class Units {
    private final Random random;
    private String id;//Para diferenciar a las unidades (funciona como nombre)
    private int hpMax;
    private int hpActual;
    private int atk;
    private int def;
    private int evd;



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
    public void setId(String id) {
        this.id = id;
    }
    public int getHpMax() {
        return hpMax;
    }
    public void setHpMax(int hpMax) {
        this.hpMax = hpMax;
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
    public void setAtk(int atk) {
        this.atk = atk;
    }
    public int getDef() {
        return def;
    }
    public void setDef(int def) {
        this.def = def;
    }
    public int getEvd() {
        return evd;
    }
    public void setEvd(int evd) {
        this.evd = evd;
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
        return id != null ? id.equals(units.id) : units.id == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + hpMax;
        result = 31 * result + hpActual;
        result = 31 * result + atk;
        result = 31 * result + def;
        result = 31 * result + evd;
        return result;
    }
    public void setSeed(final long seed) {
        random.setSeed(seed);
    }

    /**
     * entrega un lanzamiento de dado
     * @return
     */
    public int roll() {
        return random.nextInt(6) + 1;
    }

    /**
     * determina si una unidad esta muerta o no.
     * @return
     */
    public boolean dead(){
        return (this.getHpActual() <= 0);
    }

    /**
     * dependera del tipo de unidad el botin a entregar
     * @return
     */
    public int loot(){return 0;}
    /**
     * funcion que devuelve el ataque del agresor
     * @return
     */
    public int attack() {
        return this.roll() + this.getAtk();
    }

    /**
     * funcion que recibe un ataque y actua "defendiendose"
     * @param damage
     */
    public void defense(int damage) {
        int i = damage - (this.roll() + this.getDef());
        if (i < 1){i = 1;}
        this.setHpActual(this.getHpActual()-i);
        System.out.print("daño recibido: " + i + "\n");
    }

    /**
     * funcion que recibe un ataque y trata de esquivar
     * @param damage
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
    }
    public void combat(Units enemy){
           this.initio_combat();
           enemy.initio_combat();
    }

    public void initio_combat(){
        System.out.print("personaje no valido para inicio de combate\n");
    }
}
