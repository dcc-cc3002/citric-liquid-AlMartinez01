package cl.uchile.dcc.citricliquid.model;

public abstract class Units {
    private final String name;
    private final int maxHp;
    private final int atk;
    private final int def;
    private final int evd;
    private int currentHp;

    public Units(String name, int maxHp, int atk, int def, int evd) {
        this.name = name;
        this.maxHp = maxHp;
        this.atk = atk;
        this.def = def;
        this.evd = evd;
    }
}
