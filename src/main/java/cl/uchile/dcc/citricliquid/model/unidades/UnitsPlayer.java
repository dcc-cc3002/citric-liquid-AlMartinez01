package cl.uchile.dcc.citricliquid.model.unidades;

import cl.uchile.dcc.citricliquid.model.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.abstracto.Units;
import cl.uchile.dcc.citricliquid.model.metodos.Combat;

import java.util.Arrays;

public class UnitsPlayer extends Units implements Combat {
    Carts[] mano; //mano de cartas disponibles

    //////////////////NORMA///////////////////
    int stars;
    int wins;
    int lvlNorma;
    //////////////////FIN NORMA///////////////

    public UnitsPlayer(String id, int hpMax, int atk, int def, int evd, Carts[] mano, int stars, int wins, int lvlNorma) {
        super(id, hpMax, atk, def, evd);
        this.mano = mano;
        this.stars = stars;
        this.wins = wins;
        this.lvlNorma = lvlNorma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UnitsPlayer that = (UnitsPlayer) o;

        if (stars != that.stars) return false;
        if (wins != that.wins) return false;
        if (lvlNorma != that.lvlNorma) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(mano, that.mano);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(mano);
        result = 31 * result + stars;
        result = 31 * result + wins;
        result = 31 * result + lvlNorma;
        return result;
    }
    /**#################SETTERS AND GETTERS#######################**/
    public Carts[] getMano() {
        return mano;
    }
    public void setMano(Carts[] mano) {
        this.mano = mano;
    }
    public int getStars() {
        return stars;
    }
    public void setStars(int stars) {
        this.stars = stars;
    }
    public int getWins() {
        return wins;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }
    public int getLvlNorma() {
        return lvlNorma;
    }
    public void setLvlNorma(int lvlNorma) {
        this.lvlNorma = lvlNorma;
    }
    /**#################SETTERS AND GETTERS#######################**/
    /**
     * funcion que devuelve el ataque del agresor
     * @return
     */
    @Override
    public int attack() {
        return this.roll() + this.getAtk();
    }

    /**
     * funcion que recibe un ataque y actua "defendiendose"
     * @param damage
     */
    @Override
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
    @Override
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


}
