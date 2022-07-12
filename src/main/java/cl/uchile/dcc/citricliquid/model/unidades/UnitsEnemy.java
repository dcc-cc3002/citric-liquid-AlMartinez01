package cl.uchile.dcc.citricliquid.model.unidades;

import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.Attackable;
import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.Attacker;
import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.Initio_combat;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Units;
import org.jetbrains.annotations.NotNull;

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
    public void attack(@NotNull Attackable target) {
        target.receiveDamagePlayer(this.attack());
    }

    @Override
    public void receiveDamagePlayer(int damage) {

        int i = this.getRandom().nextInt(2);

        if (i == 1) {
            System.out.print("El ah decidido bloquear el ataque!!");
            this.defense(damage);
        }
        else{
            System.out.println("El ah esquivado!!");
            this.dodge(damage);
        }
    }

    @Override
    public void initio_combat() {
        System.out.printf(this.getId()+" esta preparado para la batalla");
    }

    @Override
    public void victory(int[] recompense) {
        this.setBag(getBag()+ recompense[0]);
    }

    @Override
    public int[] defeat() {
        int i = 1;
        if (boss){ i = 3;}
        return (new int[]{this.loot(), i});
    }
}
