package cl.uchile.dcc.citricliquid.model.controller;

import cl.uchile.dcc.citricliquid.model.controller.Transferencia.BossEvent.Informer;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.FinishedEvent.ObserverEvent;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.Observable;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.Observer;
import cl.uchile.dcc.citricliquid.model.paneles.*;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsEnemy;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;

public class GameController implements Observable, Observer, ObserverEvent, Informer {
    private final Random random;
    public UnitsPlayer[] players;
    public Panel[] tablero;
    public int ronda = 1; //Almacenamos la información de la ronda en la que se esta
    public int norma_maxima = 1; // Almacena la norma mas alta de la partida (sera un termino global)
    public int countPlayers = 0; //Comienza a contar de 0
    public int turnPlayer = 0;
    public boolean winner = false;
    public Observer[] obs_panel;
    public UnitsEnemy bossGame;
    int[] boss0=new int[]{8,3,2,-1};
    int[] boss1=new int[]{7,2,3,-2};
    int[] boss2=new int[]{10,2,1,-3};

    public GameController(int seed) {
        random = new Random(seed);
        int i = random.nextInt(3);
        switch (i){
            case 0 -> bossGame = new UnitsEnemy("Store Manager",boss0[0],boss0[1],boss0[2],boss0[3],true,0);
            case 1 -> bossGame = new UnitsEnemy("Shifu Robot",boss1[0],boss1[1],boss1[2],boss1[3],true,0);
            case 2 -> bossGame = new UnitsEnemy("Flying Castle",boss2[0],boss2[1],boss2[2],boss2[3],true,0);
        }
    }
    public GameController() {
        random = new Random();
        int i = random.nextInt(3);
        switch (i){
            case 0 -> bossGame = new UnitsEnemy("Store Manager",boss0[0],boss0[1],boss0[2],boss0[3],true,0);
            case 1 -> bossGame = new UnitsEnemy("Shifu Robot",boss1[0],boss1[1],boss1[2],boss1[3],true,0);
            case 2 -> bossGame = new UnitsEnemy("Flying Castle",boss2[0],boss2[1],boss2[2],boss2[3],true,0);
        }
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
            if (panel.getClass() == PanelHome.class){
                ((PanelHome) panel).attach(this);
            }
            if (panel.getClass() == Panelboss.class){
                ((Panelboss) panel).setInformer(this);
            }
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
        if (norma_maxima==5){
            winner = true;
        }
    }

    public void init_turn(){
        if (winner){
            System.out.println("Ya tenemos un ganador!!!\n");
            UnitsPlayer winnerPlayer = null;
            for (UnitsPlayer player: players){
                if(player.getLvlNorma() == 5){winnerPlayer = player;}
            }
            assert winnerPlayer != null;
            System.out.println("Ganador: "+winnerPlayer.getId());
            return;
        }
        players[turnPlayer].initTurn();
    }
    @Override
    public void updateEvent() {
        turnPlayer++;
        if (turnPlayer > countPlayers){
            turnPlayer=0;
            ronda++;
        }
        init_turn();
    }

    @Override
    public UnitsEnemy send() {
        return bossGame;
    }

    public Panel createPanel(){
        return new Panel();
    }
    public Panelboss createPanelBoss(){
        return new Panelboss();
    }
    public PanelBonus createPanelBonus(){
        return new PanelBonus();
    }
    public PanelEncounter createPanelEncounter(){
        return new PanelEncounter();
    }
    public PanelDrop createPanelDrop(){
        return new PanelDrop();
    }
    public PanelDrawn createPanelDrawn(){
        return new PanelDrawn();
    }
    public PanelHome createPanelHome(){
        return new PanelHome();
    }
    public void setPlayerPanelHome(UnitsPlayer unitsPlayer, @NotNull PanelHome panelHome){
        panelHome.setHome(unitsPlayer);
    }
    public int getRonda(){
        return this.ronda;
    }

}
