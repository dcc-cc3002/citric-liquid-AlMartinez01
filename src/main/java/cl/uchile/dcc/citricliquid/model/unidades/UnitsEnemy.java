package cl.uchile.dcc.citricliquid.model.unidades;

import cl.uchile.dcc.citricliquid.model.interfaces.Attackable;
import cl.uchile.dcc.citricliquid.model.interfaces.Attacker;
import cl.uchile.dcc.citricliquid.model.interfaces.Initio_combat;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Units;

import java.util.Random;


public class UnitsEnemy extends Units implements Initio_combat, Attacker, Attackable {
    boolean boss; //definira si este es un boss o no
    int bag; //cantidad de estrellas recolectadas


    public UnitsEnemy(String id, int hpMax, int atk, int def, int evd, boolean boss, int bag) {
        super(id, hpMax, atk, def, evd);
        this.boss = boss;
        this.bag = bag;
    }

    public boolean isBoss() {
        return boss;
    }

    public int getBag() {
        return bag;
    }
    public void setBag(int bag) {
        this.bag = bag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UnitsEnemy that = (UnitsEnemy) o;

        if (boss != that.boss) return false;
        return bag == that.bag;
    }

    @Override
    public void addStars(int stars) {
        System.out.println(this.getId() +": me quedare con estas 'stars' para mi: " + stars+ "\n");
        setBag(Math.max(this.getBag() + stars,0));
    }

    @Override
    public int loot() {
        return getBag();
    }

    @Override
    public void attack(Attackable target) {
        target.receiveDamagePlayer(this.attack());
    }

    @Override
    public void receiveDamagePlayer(int damage) {

        if (this.getHpActual() + this.getEvd() <= this.getHpMax()/3){
            System.out.println("Esta esquivando como loco!!\n");
            this.dodge(damage);
        }
        else if (this.getHpActual() + this.getDef() >= 3*this.getHpMax()/4){
            System.out.println("el enemigo muestra su rudeza recibiendo el daño\n");
            this.defense(damage);
        }
        else {
            int i = new Random().nextInt(2);

            if (i == 1) {
                System.out.print("El ah decidido bloquear el ataque!!");
                this.defense(damage);
            }
            else{
                System.out.println("El ah esquivado!!");
                this.dodge(damage);
            }
        }
        try {
            //Ponemos a "Dormir" el programa durante los ms que queremos
            Thread.sleep(5*1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void initio_combat() {
        System.out.printf(this.getId()+" esta preparado para la batalla");
    }
}
