package cl.uchile.dcc.citricliquid.model.paneles.StatesPanels;

import cl.uchile.dcc.citricliquid.model.paneles.Panel;

import java.io.IOException;

public interface StatesPanel {
    void rollDice() throws IOException;

    void option0() throws IOException;

    void option1() throws IOException;

    void option2() throws IOException;

    void option3() throws IOException;
    void option4() throws IOException;
    void option5() throws IOException;
    void option6() throws IOException;
    void option7() throws IOException;
    void option8() throws IOException;
    void option9() throws IOException;
    void addPanel(Panel panel);
}
