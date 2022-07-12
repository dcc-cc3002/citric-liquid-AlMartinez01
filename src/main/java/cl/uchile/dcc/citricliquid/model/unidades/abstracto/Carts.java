package cl.uchile.dcc.citricliquid.model.unidades.abstracto;

import java.util.Objects;

public abstract class Carts {
    private final String name;
    private final String Characters;

    public Carts(String name, String characters) {
        this.name = name;
        this.Characters = characters;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carts carts = (Carts) o;
        return Objects.equals(name, carts.name) && Objects.equals(Characters, carts.Characters);
    }

    @Override
    public String toString() {
        return "Carts{" +
                "name='" + name + '\'' +
                ", Characters='" + Characters + '\'' +
                '}';
    }

    public void Active(Units units){
    }
}
