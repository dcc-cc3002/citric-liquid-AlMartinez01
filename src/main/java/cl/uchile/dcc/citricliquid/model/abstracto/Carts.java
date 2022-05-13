package cl.uchile.dcc.citricliquid.model.abstracto;

public abstract class Carts {
    private final String name;
    private final String Characters;

    public Carts(String name, String characters) {
        this.name = name;
        this.Characters = characters;
    }

    public String getName() {
        return name;
    }

    public String getCharacters() {
        return Characters;
    }

    public void activator(Units p1){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Carts carts = (Carts) o;

        if (!name.equals(carts.name)) return false;
        return Characters.equals(carts.Characters);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + Characters.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Carts{" +
                "name='" + name + '\'' +
                ", Characters='" + Characters + '\'' +
                '}';
    }
}
