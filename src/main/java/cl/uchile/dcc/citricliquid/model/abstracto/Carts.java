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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Carts carts = (Carts) o;

        if (!name.equals(carts.name)) return false;
        return Characters.equals(carts.Characters);
    }


    @Override
    public String toString() {
        return "Carts{" +
                "name='" + name + '\'' +
                ", Characters='" + Characters + '\'' +
                '}';
    }
}
