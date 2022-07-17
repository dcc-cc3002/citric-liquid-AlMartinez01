package cl.uchile.dcc.citricliquid.model.paneles.StatesPanelHome;

import cl.uchile.dcc.citricliquid.model.paneles.Panel;

import java.io.IOException;

public interface StatesPanel {
    void rollDice() throws IOException;

    void option0() throws IOException;

    void option1();

    void option2();

    void option3();
    void option4();
    void option5();
    void option6();
    void option7();
    void option8();
    void option9();
    void addPanel(Panel panel);
}
