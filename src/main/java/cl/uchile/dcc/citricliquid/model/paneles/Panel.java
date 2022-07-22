package cl.uchile.dcc.citricliquid.model.paneles;

import cl.uchile.dcc.citricliquid.model.controller.Transferencia.FinishedEvent.ObservableEvent;
import cl.uchile.dcc.citricliquid.model.controller.Transferencia.FinishedEvent.ObserverEvent;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanels.SelectNextPanel;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanels.Standly_mode_panel;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanels.StatesPanel;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanels.Select_player_Panel;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.StandbyPanel;
import cl.uchile.dcc.citricliquid.model.unidades.StatesUnitsplayers.Standby_mode_Player;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Units;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class  Panel implements ObservableEvent, ObserverEvent{
    private UnitsPlayer[] units;//Unidades en el panel
    private Panel[] nexts;//Los Paneles con los que esta unido o se puede continuar
    private Carts carta;//Carta en el panel
    public StatesPanel statesPanel;

    ObserverEvent observerEvent;
    public Panel(UnitsPlayer[] units, Panel[] nexts, Carts carta) {
        this.units = units;
        this.nexts = nexts;
        this.carta = carta;
        this.statesPanel = new Standly_mode_panel();
    }
    public Panel(){
        this.units = null;
        this.nexts = null;
        this.carta = null;
        this.statesPanel = new Standly_mode_panel();
    }

    public ObserverEvent getObserverEvent() {
        return observerEvent;
    }

    public int cantNexts(){
        if (nexts == null)return 0;
        if (Arrays.equals(nexts, new Panel[]{})) {
            return 0;
        }
        int i = 0;
        for (Panel ignored : nexts){
            i++;
        }
        return i;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Panel panel = (Panel) o;
        return Arrays.equals(units, panel.units) && Arrays.equals(nexts, panel.nexts) && Objects.equals(carta, panel.carta);
    }

    public Panel[] getNexts() {
        return nexts;
    }

    public UnitsPlayer[] getUnits() {
        return units;
    }

    /**
     * dice si una unidad existe o no
     * @return boolean
     */
    public boolean unitExist(@NotNull Units u1){
        if (this.units == null){return false;}
        for (Units i : this.units){
            if (u1.equals(i)){return true;}
        }
        return false;
    }

    /**
     * encuentra la ubicacion de un player
     * @return int
     */
    public int unitUbi(@NotNull Units u1){
        if (!this.unitExist(u1)){return -1;}
        int i = 0;
        for (Units u : this.units){
            if (u1.equals(u)){return i;}
            i++;
        }
        return i;
    }
    public int cantUnits(){
        if (units == null){return 0;}
        int i = 0;
        for (UnitsPlayer ignored : this.units){i++;}
        return i;
    }

    /**
     * agrega un player a la lista del panel
     */
    public void agrePlayer(@NotNull UnitsPlayer u1){
        if (units == null){this.units = new UnitsPlayer[]{u1};}
        else {
            UnitsPlayer[] newUnits;
            newUnits = new UnitsPlayer[this.cantUnits() + 1];
            int t = 0;
            for (UnitsPlayer players : this.units) {
                newUnits[t] = players;
                t++;
            }
            newUnits[t] = u1;
            this.units = newUnits;
        }
    }

    public void deletedPlayer(UnitsPlayer player){
        if (!(this.unitExist(player))){return;}
        int r = this.cantUnits()-1;
        if (r == 0){this.units = null; return;}
        UnitsPlayer[] players = new UnitsPlayer[this.cantUnits()-1];
        if (this.unitUbi(player) >= 0) System.arraycopy(this.units, 0, players, 0, this.unitUbi(player));
        if (this.cantUnits() - (this.unitUbi(player) + 1) >= 0)
            System.arraycopy(this.units, this.unitUbi(player) + 1, players, this.unitUbi(player) + 1 - 1, this.cantUnits() - (this.unitUbi(player) + 1));
        this.units = players;
    }
    /**
     * se agregan mutuamente
     */
    public void unitPlayer(@NotNull UnitsPlayer u1){
        this.agrePlayer(u1);
        u1.setUbi(this);
    }
    /**
     * activa el panel
     */
    public void activator(@NotNull UnitsPlayer u1){
        this.unitPlayer(u1);
        u1.setStatesPlayer(new Standby_mode_Player());
        notifierEvent();
    }

    public void avanzar(@NotNull UnitsPlayer u1, int i) throws IOException {
        if (this.cantUnits() > 0){
            setStatesPanel(new Select_player_Panel(this,u1,i));
            return;
        }
        if (nexts == null || i == 0){this.activator(u1);}
        else{
            if (cantNexts() == 0) {activator(u1);return;}
            if (cantNexts() == 1 ) this.nexts[0].avanzar(u1,i-1);
            else {
                this.unitPlayer(u1);
                setStatesPanel(new SelectNextPanel(this, i, u1));
            }
        }
    }


    public void addNextPanel(final Panel panel) {
        if (nexts == null){
            nexts = new Panel[]{panel};
            return;
        }
        Panel[] newNext = new Panel[cantNexts()+1];
        int i = 0;
        for (Panel panel1: nexts){
            newNext[i] = panel1;
            i++;
        }
        newNext[cantNexts()] = panel;
        nexts = newNext;
    }

    @Override
    public void attachEvent(ObserverEvent observer) {
        this.observerEvent = observer;
    }

    @Override
    public void notifierEvent() {
        if (this.observerEvent == null){return;}
        this.observerEvent.updateEvent();
    }
    public void setStatesPanel(@NotNull StatesPanel statesPanel) {
        this.statesPanel = statesPanel;
        statesPanel.addPanel(this);
    }

    ////////////////CONTROLLER//////////////////
    public void rollDice() throws IOException{statesPanel.rollDice();}
    public void option0() throws IOException {statesPanel.option0();}
    public void option1() throws IOException {statesPanel.option1();}
    public void option2() throws IOException {statesPanel.option2();}
    public void option3() throws IOException {statesPanel.option3();}
    public void option4() throws IOException {statesPanel.option4();}
    public void option5() throws IOException {statesPanel.option5();}
    public void option6() throws IOException {statesPanel.option6();}
    public void option7() throws IOException {statesPanel.option7();}
    public void option8() throws IOException {statesPanel.option8();}
    public void option9() throws IOException {statesPanel.option9();}

    @Override
    public void updateEvent() { //RECIBE CUANDO EL COMBATE TERMINA
        if (observerEvent == null)return;
        observerEvent.updateEvent();
    }

    ////////////////CONTROLLER//////////////////

    @Override
    public String toString() {
        String unitstext;
        if (units == null) {unitstext = "no hay jugadores";}
        else {unitstext = Arrays.toString(units);}

        return "Panel{" +
                "units=" + unitstext +
                '}';
    }
}
