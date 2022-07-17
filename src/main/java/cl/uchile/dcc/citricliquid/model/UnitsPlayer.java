package cl.uchile.dcc.citricliquid.model;

public class UnitsPlayer extends Units {
    private int normaLevel;
    private int stars;
    private int wins;

    public UnitsPlayer(String name, int maxHp, int atk, int def, int evd, int normaLevel, int stars, int wins) {
        super(name, maxHp, atk, def, evd);
        this.normaLevel = normaLevel;
        this.stars = stars;
        this.wins = wins;
    }
}
