package cl.uchile.dcc.citricliquid.model.controller.SistemaCombate;

public interface Initio_combat {
    void initio_combat();

    void victory(int[] recompense);

    int[] defeat();
}
