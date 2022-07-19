package cl.uchile.dcc.citricliquid.model.paneles;


import cl.uchile.dcc.citricliquid.model.unidades.abstracto.Carts;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.jetbrains.annotations.NotNull;

public class PanelDrop extends Panel {
    public PanelDrop(UnitsPlayer[] units, Panel[] nexts, Carts carta) {
        super(units, nexts, carta);
    }
    public PanelDrop(){
        super();
    }

    /**
     * la cantidad de estrellas que quita
     * @param player
     * @return
     */
    private int drop (@NotNull UnitsPlayer player){
        return player.roll() * player.getLvlNorma();
    }
    @Override
    public void activator(final @NotNull UnitsPlayer player) {
        this.unitPlayer(player);
        player.incrementStars(-drop(player));
        super.activator(player);
    }



}
