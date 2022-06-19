package cl.uchile.dcc.citricliquid.model.unidades;

import cl.uchile.dcc.citricliquid.model.interfaces.Combat;
import cl.uchile.dcc.citricliquid.model.paneles.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.paneles.abstracto.Units;
import cl.uchile.dcc.citricliquid.model.paneles.Panel;

import java.io.IOException;
import java.util.Arrays;

public class UnitsPlayer extends Units implements Combat {
    Carts[] mano; //mano de cartas disponibles
    Panel ubi;
    //////////////////NORMA///////////////////
    int stars;
    int wins;
    int lvlNorma;
    //////////////////FIN NORMA///////////////


    public UnitsPlayer(String id, int hpMax, int atk, int def, int evd, Carts[] mano, Panel ubi, int stars, int wins, int lvlNorma) {
        super(id, hpMax, atk, def, evd);
        this.mano = mano;
        this.ubi = ubi;
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




    /**#################SETTERS AND GETTERS#######################**/
    public void setLvlNorma(int lvlNorma) {
        this.lvlNorma = lvlNorma;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }
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
    public int getLvlNorma() {
        return lvlNorma;
    }
    public Panel getUbi() {
        return ubi;
    }
    public void setUbi(Panel ubi) {
        this.ubi = ubi;
    }

    /**#################SETTERS AND GETTERS#######################**/
    public int loot(){
        int i = this.getStars()/2;
        this.setStars(stars-i);
        return i;
    }

    /**
     * regresa la cantidad de cartas que tiene un Player
     */
    public int cant_carts(){
        int i = 0;
        for (Carts ignored : this.mano){
            i++;
        }
        return i;
    }

    /**
     * muestra las cartas disponibles
     */
    public void view_carts(){
        StringBuilder text = new StringBuilder(); int i = 0;
        for (Carts j :this.mano){
            text.append(" ").append(j).append(" ").append(i).append("| \n");
            i++;
        }
        System.out.print("Cartas disponibles: \n"
                        + text);
    }

    /**
     * elimina la carta establecida de la mano
     */
    public void deleteCart(int i){
        if (i > this.cant_carts()-1){return;}
        Carts[] manoNew = new Carts[this.cant_carts()-1];
        Carts cart = this.getMano()[i];
        int t = 0; //contador//
        for (Carts j : this.mano){
            if (!(cart.equals(j))){
                manoNew[t] = j;
                t++;
            }
        }
        this.setMano(manoNew);
    }
    public void initio_combat() {
        if (this.mano == null){return;}
        System.out.print("seleccion de cartas jugador: " + this.getId() + " \n");
        boolean b = !(this.cant_carts() == 0);
        if (b){
            this.view_carts();
        }
        else {
            System.out.print("Sin cartas disponibles\n");
        }
    }

    /**
     * recibe un parametro y se agrega a las starts del player (no permite bajar de 0)
     */
    public void incrementStars(int stars){
        this.setStars( this.getStars() + stars);
        if (this.getStars()<0) {
            this.setStars(0); //Impedira que se coloque en estrellas negativas
        }
    }

    /**
     * Play hace la funcion de "avanzar" al jugador, a la vez que elimina la informacion de su ubicacion anterior
     * (conlleva muchas cosas por lo que tendra test propio)
     * @throws IOException
     */
    public void play() throws IOException {
        this.ubi.deletedPlayer(this);
        this.setUbi(this.ubi.avanzar(this,roll()));
    }
}
