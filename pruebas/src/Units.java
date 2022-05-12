public class Units {
    private final String name;
    private final int hpMax;
    private final int atk;
    private final int def;
    private final int evd;
    private int hpCorrupt;
    public Units(String name, int hpMax, int atk, int def, int evd) {
        this.name = name;
        this.hpMax = hpCorrupt = hpMax;
        this.atk = atk;
        this.def = def;
        this.evd = evd;
    }
}
