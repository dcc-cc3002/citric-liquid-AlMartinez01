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
    @Override
    public int loot(){
        int i = this.getStars()/2;
        this.setStars(-i);
        return i;
    }

    /**
     * regresa la cantidad de cartas que tiene un Player
     * @return
     */
    public int cant_carts(){
        int i = 0;
        for (Carts j : this.mano){
            i++;
        }
        return i;
    }

    /**
     * muestra las cartas disponibles
     */
    public void view_carts(){
        String text = ""; int i = 0;
        for (Carts j :this.mano){
            text = text + " " + j + " " + i +"| \n";
        }
        System.out.print("Cartas disponibles: \n"
                        + text);
    }

    /**
     * elimina la carta establecida de la mano
     * @param i
     */
    public void deleteCart(int i){
        Carts[] manoNew = new Carts[this.cant_carts()-1];
        int t = 0; //contador//
        for (Carts j : this.mano){
            if (t != i){
                manoNew[t] = j;
                t++;
            }
        }
        this.setMano(manoNew);
    }

    @Override
    public void initio_combat() {
        System.out.print("seleccion de cartas jugador: " + this.getId() + " \n");
        boolean b = !(this.cant_carts() == 0);
        if (b){
            this.view_carts();
        }
        else {
            System.out.print("Sin cartas disponibles\n");
        }
    }
}
