package cl.uchile.dcc.citricliquid.model.paneles;

import cl.uchile.dcc.citricliquid.model.paneles.StatesPanels.SelectNextPanel;
import cl.uchile.dcc.citricliquid.model.paneles.StatesPanels.Standly_mode_panel;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElectionNextPanel {
    UnitsPlayer suguri;
    int hp = 6; int def = 2; int evd = 1; int atk = 3;

    private Panel panel1;
    private Panel panel2;
    private Panel panel3;
    private Panel panel4;
    private Panel panel5;
    private Panel panel6;
    private Panel panel7;
    private Panel panel8;
    private Panel panel9;
    private Panel panel10;
    private Panel panel11;

    @BeforeEach
    void setUp() {
        suguri = new UnitsPlayer("suguri",hp,atk,def,evd);

        panel1 = new Panel();
        panel2 =new Panel();
        panel3 = new Panel();
        panel4 = new Panel();
        panel5 = new Panel();
        panel6 = new Panel();
        panel7 =new Panel();
        panel8 = new Panel();
        panel9 = new Panel();
        panel10= new Panel();
        panel11= new Panel();
    }

    @RepeatedTest(100)
    void firstMultiNext() throws IOException {
        long i = 13;
        suguri.setSeed(i);
        panel1.addNextPanel(panel2);
        panel1.addNextPanel(panel3);
        panel1.addNextPanel(panel4);
        panel3.addNextPanel(panel5);
        panel2.addNextPanel(panel5);
        panel4.addNextPanel(panel5);
        panel5.addNextPanel(panel6);
        panel6.addNextPanel(panel7);
        panel7.addNextPanel(panel8);
        panel8.addNextPanel(panel9);
        panel9.addNextPanel(panel1);

        panel1.unitPlayer(suguri);

        suguri.play();
        assertEquals(SelectNextPanel.class,panel1.statesPanel.getClass());

        panel1.option2();
        assertEquals(Standly_mode_panel.class,panel1.statesPanel.getClass());
    }

    @RepeatedTest(100)
    void secondMultiNext() throws IOException {
        long i = new Random().nextLong();
        suguri.setSeed(i);
        if (new Random(i).nextInt(6)+1 == 1)return; //caso que no sirve

        panel1.addNextPanel(panel2);
        panel2.addNextPanel(panel3);
        panel2.addNextPanel(panel4);
        panel1.unitPlayer(suguri);

        suguri.play();

        assertEquals(SelectNextPanel.class,panel2.statesPanel.getClass());

        panel2.option0();

        assertEquals(Standly_mode_panel.class,panel2.statesPanel.getClass());
    }

    @RepeatedTest(200)
    void greatElectionNext() throws IOException {
        panel1.unitPlayer(suguri);

        panel1.addNextPanel(panel2);
        panel1.addNextPanel(panel3);
        panel1.addNextPanel(panel4);
        panel1.addNextPanel(panel5);
        panel1.addNextPanel(panel6);
        panel1.addNextPanel(panel7);
        panel1.addNextPanel(panel8);
        panel1.addNextPanel(panel9);
        panel1.addNextPanel(panel10);
        panel1.addNextPanel(panel11);

        suguri.play();
        assertEquals(SelectNextPanel.class,panel1.statesPanel.getClass());

        int i = new Random().nextInt(10);
        System.out.println(i);
        switch (i){
            case 0 -> panel2.option0();
            case 1 -> panel2.option1();
            case 2 -> panel2.option2();
            case 3 -> panel2.option3();
            case 4 -> panel2.option4();
            case 5 -> panel2.option5();
            case 6 -> panel2.option6();
            case 7 -> panel2.option7();
            case 8 -> panel2.option8();
            case 9 -> panel2.option9();
        }
        assertEquals(Standly_mode_panel.class,panel2.statesPanel.getClass());
    }
}
