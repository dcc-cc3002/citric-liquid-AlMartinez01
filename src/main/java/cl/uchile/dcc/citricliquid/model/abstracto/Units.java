package cl.uchile.dcc.citricliquid.model.abstracto;

public abstract class Units {
    private String id;//Para diferenciar a las unidades (funciona como nombre
    private int hpMax;
    private int hpActual;
    private int atk;
    private int def;
    private int evd;


    public Units(String id, int hpMax, int hp, int atk, int def, int evd) {
        this.id = id;
        this.hpMax = hpMax;
        this.hpActual = hp;
        this.atk = atk;
        this.def = def;
        this.evd = evd;
    }
}
