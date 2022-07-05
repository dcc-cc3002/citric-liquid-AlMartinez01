package cl.uchile.dcc.citricliquid.model.controller;

import cl.uchile.dcc.citricliquid.model.controller.Transferencia.Observable;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.Observer;
import cl.uchile.dcc.citricliquid.model.paneles.Panel;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;

import java.util.Arrays;
import java.util.Objects;

public class GameController implements Observable, Observer {
    public UnitsPlayer[] players;
    public Panel[] tablero;
    public int ronda = 1; //Almacenamos la información de la ronda en la que se esta
    public int norma_maxima = 1; // Almacena la norma mas alta de la partida (sera un termino global)

    public Observer[] obs_panel;

    CreatePlayers createPlayers;


    public GameController() {
    }

    public void createPlayers() {
        this.createPlayers = new CreatePlayers(1);
        players = createPlayers.desordenar();
    }

    public void addPlayer(UnitsPlayer unitsPlayer){
        if (this.players == null){
            this.players = new UnitsPlayer[]{unitsPlayer};
            return;
        }
        int contador = 0;
        for (UnitsPlayer ignored : players){
            contador++;
        }
        UnitsPlayer[] newPlayers = new UnitsPlayer[contador +1];
        int i = 0;
        while (i < contador){
            newPlayers[i] = players[i];
            i++;
        }
        newPlayers[contador] = unitsPlayer;
        this.players = newPlayers;
    }
    public void addTablero(Panel[] tablero){
        this.tablero = tablero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameController that = (GameController) o;

        if (ronda != that.ronda) return false;
        if (norma_maxima != that.norma_maxima) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(players, that.players)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(tablero, that.tablero)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(obs_panel, that.obs_panel)) return false;
        return Objects.equals(createPlayers, that.createPlayers);
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
}
