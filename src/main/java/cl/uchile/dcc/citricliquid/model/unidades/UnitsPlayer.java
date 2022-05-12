package cl.uchile.dcc.citricliquid.model.unidades;

import cl.uchile.dcc.citricliquid.model.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.abstracto.Panel;
import cl.uchile.dcc.citricliquid.model.abstracto.Units;

import java.util.Arrays;

public class UnitsPlayer extends Units {
    Carts[] mano; //mano de cartas disponibles
    Panel ubicacion; //ubicacion del jugador

    //////////////////NORMA///////////////////
    int stars;
    int wins;
    int lvlNorma;
    //////////////////FIN NORMA///////////////

    public UnitsPlayer(String id, int hpMax, int hp, int atk, int def, int evd, Carts[] mano, int stars, int wins, int lvlNorma) {
        super(id, hpMax, hp, atk, def, evd);
        this.mano = mano;
        this.stars = stars;
        this.wins = wins;
        this.lvlNorma = lvlNorma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnitsPlayer that = (UnitsPlayer) o;

        if (stars != that.stars) return false;
        if (wins != that.wins) return false;
        if (lvlNorma != that.lvlNorma) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(mano, that.mano);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(mano);
        result = 31 * result + stars;
        result = 31 * result + wins;
        result = 31 * result + lvlNorma;
        return result;
    }
}
