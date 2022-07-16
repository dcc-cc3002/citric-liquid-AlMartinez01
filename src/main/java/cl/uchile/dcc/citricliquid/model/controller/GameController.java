package cl.uchile.dcc.citricliquid.model.controller;

import cl.uchile.dcc.citricliquid.model.controller.Transferencia.FinishedEvent.ObserverEvent;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.Observable;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.Observer;
import cl.uchile.dcc.citricliquid.model.paneles.Panel;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

public class GameController implements Observable, Observer, ObserverEvent {
    public UnitsPlayer[] players;
    public Panel[] tablero;
    public int ronda = 1; //Almacenamos la información de la ronda en la que se esta
    public int norma_maxima = 1; // Almacena la norma mas alta de la partida (sera un termino global)
    public int countPlayers = 0; //Comienza a contar de 0
    public int turnPlayer = 0;

    public Observer[] obs_panel;


    public GameController() {
    }


    public void addPlayer(UnitsPlayer unitsPlayer){
        if (this.players == null){
            this.players = new UnitsPlayer[]{unitsPlayer};
            return;
        }
        int contador = countPlayers+1;
        UnitsPlayer[] newPlayers = new UnitsPlayer[contador +1];
        int i = 0;
        while (i < contador){
            newPlayers[i] = players[i];
            i++;
        }
        newPlayers[contador] = unitsPlayer;
        this.players = newPlayers;
        this.countPlayers++;
    }
    public void addTablero(Panel @NotNull [] tablero){
        this.tablero = tablero;
        for (Panel panel: tablero){
            panel.attachEvent(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameController that = (GameController) o;
        return ronda == that.ronda && norma_maxima == that.norma_maxima && Arrays.equals(players, that.players) && Arrays.equals(tablero, that.tablero) && Arrays.equals(obs_panel, that.obs_panel);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(ronda, norma_maxima);
        result = 31 * result + Arrays.hashCode(players);
        result = 31 * result + Arrays.hashCode(tablero);
        result = 31 * result + Arrays.hashCode(obs_panel);
        return result;
    }

    @Override
    public void attach(Observer observer) {
        if (this.obs_panel == null){
            this.obs_panel = new Observer[]{observer};
            return;
        }
        int cant_Observer = 0;
        for (Observer ignored : obs_panel){
            cant_Observer++;
        }
        Observer[] new_Obs_panel = new Observer[cant_Observer + 1];
        int i = 0;
        while (i < cant_Observer){
            new_Obs_panel[i] = obs_panel[i];
            i++;
        }
        new_Obs_panel[cant_Observer] = observer;
        this.obs_panel = new_Obs_panel;
    }


    @Override
    public void notifier() {
        if (obs_panel == null){return;}
        for(Observer o: obs_panel){
            o.update();
        }
    }

    @Override
    public void update() {//OBSERVAREMOS LOS PANEL HOMES PARA SABER CUANDO ALGUIEN SUBE DE NORMA
        int i = norma_maxima;
        System.out.println("esta notificando");
        for (UnitsPlayer unitsPlayer: this.players){
            this.norma_maxima = Math.max(this.norma_maxima,unitsPlayer.getLvlNorma());
        }
        if ((i != this.norma_maxima) && (this.norma_maxima==3)){
            notifier();
        }
    }

    public void init_turn(){
        players[turnPlayer].initTurn();
        turnPlayer++;
        if (turnPlayer > countPlayers){
            turnPlayer=0;
        }
    }

    @Override
    public void updateEvent() {
        init_turn();
    }
}
