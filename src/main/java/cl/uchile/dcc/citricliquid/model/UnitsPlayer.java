package cl.uchile.dcc.citricliquid.model;

public class UnitsPlayer extends Units {
    private int normaLvl;
    private int starts;
    private int wins;

    public UnitsPlayer(String name, int hpMax, int atk, int def, int evd, int normaLvl, int starts, int wins) {
        super(name, hpMax, atk, def, evd);
        this.normaLvl = normaLvl;
        this.starts = starts;
        this.wins = wins;
    }
    ////////////// GETTERS AND SETERS ///////////////////////
    public int getNormaLvl() {
        return normaLvl;
    }

    public void setNormaLvl(int normaLvl) {
        this.normaLvl = normaLvl;
    }

    public int getStarts() {
        return starts;
    }

    public void setStarts(int starts) {
        this.starts = starts;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
    ////////////// GETTERS AND SETERS ///////////////////////
    /*
    @Override
    public String toString() {
        return super.toString() + " Character{" +
                "normaLvl=" + normaLvl +
                ", starts=" + starts +
                ", wins=" + wins +
                '}';
    }
    */
}

