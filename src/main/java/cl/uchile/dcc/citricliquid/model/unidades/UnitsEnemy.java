package cl.uchile.dcc.citricliquid.model.unidades;

import cl.uchile.dcc.citricliquid.model.abstracto.Units;

public class UnitsEnemy extends Units {
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

}
