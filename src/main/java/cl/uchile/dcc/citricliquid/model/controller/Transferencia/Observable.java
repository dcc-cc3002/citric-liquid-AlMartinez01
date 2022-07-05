package cl.uchile.dcc.citricliquid.model.controller.Transferencia;

public interface Observable {
    void attach(Observer observer);
    void notifier();
}
