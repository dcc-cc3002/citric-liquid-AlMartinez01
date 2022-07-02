package cl.uchile.dcc.citricliquid.model.unidades;

import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.Attackable;
import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.Attacker;
import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.Initio_combat;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Units;
import cl.uchile.dcc.citricliquid.model.paneles.Panel;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class UnitsPlayer extends Units implements Initio_combat, Attackable, Attacker {
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
    @Override
    public int loot(){
        int i = this.getStars()/2;
        this.setStars(stars-i);
        return i;
    }

    @Override
    public void addStars(int stars) {
        System.out.printf(this.getId() +": Bueno, creo que estas 'stars' son para mi\n");
        setStars(Math.max(this.getStars() + stars,0));
        System.out.printf(this.getId()+" a cogido "+stars + " 'stars'");

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
            Scanner entrada = new Scanner(System.in);
            this.view_carts();
            System.out.println("posee " +this.cant_carts() + " cartas, elija segun el numero \n");
            int i = entrada.nextInt();
            if ((0 <= i) && (i < this.cant_carts())){
                Carts cartaSelecciona = this.getMano()[i];
                System.out.print("Selecciono la carta " + cartaSelecciona.toString() + "\n");
                cartaSelecciona.Active(this);
                this.deleteCart(i);
            }
            else{
                System.out.print("No se selecciono carta\n");
            }
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

    public void incrementedWins(int wins){
        this.setWins(Math.max(this.getWins() + wins, 0));
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

    @Override
    public void attack(Attackable target) {
        target.receiveDamagePlayer(this.attack());
    }

    @Override
    public void receiveDamagePlayer(int damage) {
        Scanner entrada = new Scanner(System.in);
        int i = -1;

        System.out.println("Defender(0) o esquivar(1)?   ");
        i = entrada.nextInt();


        if (i == 0){
            this.defense(damage);
        }
        else{
            this.dodge(damage);
        }
    }

    @Override
    public void victory(int[] recompense) {
        this.incrementStars(recompense[0]);
        this.incrementedWins(recompense[1]);
        System.out.printf("se a conseguido: " + recompense);
    }

    @Override
    public int[] defeat() {
        return (new int[]{this.loot(), 2});
    }
}
