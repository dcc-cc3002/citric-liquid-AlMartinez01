package cl.uchile.dcc.citricliquid.model.controller.Transferencia.FinishedEvent;

public interface ObservableEvent {
    void attachEvent(ObserverEvent observer);
    void notifierEvent();
}
