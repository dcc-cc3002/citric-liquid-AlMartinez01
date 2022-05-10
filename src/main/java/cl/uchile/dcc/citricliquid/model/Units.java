package cl.uchile.dcc.citricliquid.model;

public class Units {
    final private String name;
    final private int hpMax;
    private int hp;
    final private int atk;
    final private int def;
    final private int evd;

    public Units(String name, int hpMax, int atk, int def, int evd) {
        this.name = name;
        this.hpMax =hp =  hpMax;
        this.atk = atk;
        this.def = def;
        this.evd = evd;
    }
    ////////////// GETTERS AND SETERS ///////////////////////
    public String getName() {
        return name;
    }

    public int getHpMax() {
        return hpMax;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getEvd() {
        return evd;
    }
    ////////////// GETTERS AND SETERS ///////////////////////

    @Override
    public String toString() {
        return "Units{" +
                "name='" + name + '\'' +
                ", hpMax=" + hpMax +
                ", hp=" + hp +
                ", atk=" + atk +
                ", def=" + def +
                ", evd=" + evd +
                '}';
    }
}
