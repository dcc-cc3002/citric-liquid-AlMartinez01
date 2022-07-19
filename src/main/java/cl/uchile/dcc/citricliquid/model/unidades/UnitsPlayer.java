package cl.uchile.dcc.citricliquid.model.unidades;

import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.Attackable;
import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.Attacker;
import cl.uchile.dcc.citricliquid.model.controller.SistemaCombate.Initio_combat;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.FinishedEvent.ObservableEvent;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.FinishedEvent.ObserverEvent;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanels.SelectNextPanel;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanels.Select_player_Panel;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.*;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Units;
import cl.uchile.dcc.citricliquid.model.paneles.Panel;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;

public class UnitsPlayer extends Units implements Initio_combat, Attackable, Attacker, ObservableEvent {
    Carts[] mano; //mano de cartas disponibles
    Panel ubi;
    private ObserverEvent observerEvent;
    //////////////////NORMA///////////////////
    int stars;
    int wins;
    int lvlNorma;
    //////////////////FIN NORMA///////////////

    private StatesPlayer statesPlayer;

    /////////////////CONTROLLER////////////

    public void rollDice() throws IOException{statesPlayer.rollDice();}
    public void option0(){statesPlayer.option0();}
    public void option1(){statesPlayer.option1();}
    public void option2(){statesPlayer.option2();}
    public void option3(){statesPlayer.option3();}
    public void option4(){statesPlayer.option4();}
    public void option5(){statesPlayer.option5();}
    public void option6(){statesPlayer.option6();}
    public void option7(){statesPlayer.option7();}
    public void option8(){statesPlayer.option8();}
    public void option9(){statesPlayer.option9();}

    ///////////FIN CONTROLLER///////////////


    public UnitsPlayer(String id, int hpMax, int atk, int def, int evd, Carts[] mano, Panel ubi, int stars, int wins, int lvlNorma) {
        super(id, hpMax, atk, def, evd);
        this.mano = mano;
        this.ubi = ubi;
        this.stars = stars;
        this.wins = wins;
        this.lvlNorma = lvlNorma;
        setStatesPlayer(new Standby_mode_Player() );
    }

    public UnitsPlayer(String id, int hpMax, int atk, int def, int evd){
        super(id, hpMax, atk, def, evd);
        this.mano = null;
        this.ubi = null;
        this.stars = 0;
        this.wins = 0;
        this.lvlNorma = 1;
        setStatesPlayer(new Standby_mode_Player() );
    }

    public void setStatesPlayer(@NotNull StatesPlayer statesPlayer) {
        this.statesPlayer = statesPlayer;
        statesPlayer.setUnitPlayers(this);
    }
    public StatesPlayer getStatesPlayer() {
        return statesPlayer;
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
    public ObserverEvent getObserverEvent() {
        return observerEvent;
    }
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
     * agrega una carta a la mano del jugador
     */
    public void add_cart(Carts carts){
        if (this.mano == null || this.cant_carts() == 0){
            this.mano = new Carts[] {carts}; return;
        }
        int counter = this.cant_carts();
        Carts[] newMano = new Carts[counter+1];
        int i = 0;
        while (counter > i) {
            newMano[i] = this.mano[i];
            i++;
        }
        newMano[counter] = carts;
        this.mano = newMano;


    }



    /**
     * elimina la carta establecida de la mano
     */
    public void deleteCart(int i){
        if (i > this.cant_carts()-1){return;}
        int count = this.cant_carts();
        Carts[] manoNew = new Carts[count-1];
        int t = 0; //contador//
        while (t < i){
            manoNew[t] = this.mano[t];
            t++;
        }
        t ++;
        while (t < count) {
            manoNew[t - 1] = this.mano[t];
            t++;
        }
        this.setMano(manoNew);
    }

    public boolean selectCart(int i){
        if (i >= this.cant_carts() || i<0){
            System.out.print("no se selcciono carta, reintentar\n");
            return false;
        }
        else{
            Carts carts = this.getMano()[i];
            System.out.printf("Se selecciono la carta "+ carts.toString()+"\n");
            carts.Active(this);
            this.deleteCart(i);
            return true;
        }
    }

    public void initio_combat() {
        if (this.mano == null || this.cant_carts() == 0){
            System.out.print("No hay cartas disponibles");
            return;
        }
        System.out.print("seleccion de cartas jugador: " + this.getId() + " \n");
        this.setStatesPlayer(new Selection_cart_mode_player());

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
     *  IOException
     */
    public void play() throws IOException {

        int i = this.roll();
        System.out.println("Se avanzara: "+i+" cacillas");
        if (ubi.cantNexts() == 0){
            this.ubi.deletedPlayer(this);
            ubi.activator(this);
            return;
        }
        if (ubi.cantNexts() == 1){
            this.ubi.deletedPlayer(this);
            this.ubi.getNexts()[0].avanzar(this,i-1);
        }
        else{
            this.ubi.deletedPlayer(this);
            this.getUbi().setStatesPanel(new SelectNextPanel(this.getUbi(),i,this));

        }
    }

    @Override
    public void attack(@NotNull Attackable target) {
        target.receiveDamagePlayer(this.attack());
    }

    @Override
    public void receiveDamagePlayer(int damage) {
        this.statesPlayer = new Receive_damage_mode_player(damage,this);
    }

    @Override
    public void victory(int @NotNull [] recompense) {
        this.incrementStars(recompense[0]);
        this.incrementedWins(recompense[1]);
        System.out.printf("se a conseguido: " + recompense[0]);
    }

    @Override
    public int[] defeat() {
        this.setStatesPlayer(new KO_StatePlayer());
        int lot = this.loot();
        System.out.println(this.getId() +" a perdido: " + lot + " estrellas\n");
        return (new int[]{lot, 2});
    }

    public void initTurn() {
        if(statesPlayer.getClass() == KO_StatePlayer.class){
            this.statesPlayer.activeState();
            return;
        }
        this.statesPlayer = new Play_mode_player();
        System.out.println("lanzar dado: "+this.getId());
        statesPlayer.setUnitPlayers(this);
    }

    @Override
    public String toString() {
        return "UnitsPlayer{" +
                "mano=" + Arrays.toString(mano) +
                ", ubi=" + ubi +
                ", stars=" + stars +
                ", wins=" + wins +
                ", lvlNorma=" + lvlNorma +
                ", statesPlayer=" + statesPlayer +
                '}';
    }

    @Override
    public void attachEvent(ObserverEvent observer) {
        this.observerEvent = observer;
    }

    @Override
    public void notifierEvent() {
        if (observerEvent == null){return;}
        this.observerEvent.updateEvent();
    }
}
