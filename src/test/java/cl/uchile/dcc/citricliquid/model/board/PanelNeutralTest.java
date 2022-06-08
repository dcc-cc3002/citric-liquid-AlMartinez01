package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.paneles.Panel;
import cl.uchile.dcc.citricliquid.model.unidades.UnitsPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PanelNeutralTest {
    private final static String NAME_PLAYER = "suguri";
    private final static int BASE_HP = 4;
    private final static int BASE_ATK = 1;
    private final static int BASE_DEF = -1;
    private final static int BASE_EVD = 2;
    private UnitsPlayer suguri;
    private Panel panelneu1;
    private Panel panelneu2;
    private Panel panelneu3;

    @BeforeEach
    public void setUp() {
        suguri = new UnitsPlayer(NAME_PLAYER, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD, null, null, 0, 0, 1);
        panelneu1 = new Panel(null, null, null);
        panelneu2 = new Panel(null, null, null);
        panelneu3 = new Panel(null, null, null);
    }

    @Test
    public void buildTest() {
        var expected = new Panel(null, null, null);
        assertEquals(expected, panelneu1);
    }

    @Test
    public void cantNexts() {
        assertEquals(0, panelneu1.cant_next());
        panelneu1.addNextPanel(panelneu2);
        assertEquals(1, panelneu1.cant_next());
        assertEquals(panelneu2, panelneu1.getNexts()[0]);
        panelneu1.addNextPanel(panelneu3);
        assertEquals(2, panelneu1.cant_next());
    }

    @Test
    public void exiPlayerTest() {
        var pruebPanel = new Panel(new UnitsPlayer[]{suguri}, null, null);
        assertTrue(pruebPanel.unitExist(suguri));
        assertFalse(pruebPanel.unitExist(new UnitsPlayer("none", 1, 1, 1, 1, null, null, 0, 0, 1)));
    }

    @Test
    void cantUnitsTest() {
        assertEquals(0, panelneu1.cantUnits());
        var pruebPanel = new Panel(new UnitsPlayer[]{suguri}, null, null);
        assertEquals(1, pruebPanel.cantUnits());
        var playerTe = new UnitsPlayer("none", 1, 1, 1, 1, null, null, 0, 0, 1);
        var pruebPanel2 = new Panel(new UnitsPlayer[]{suguri, playerTe}, null, null);
        assertEquals(2, pruebPanel2.cantUnits());
    }

    @Test
    public void agrePlayerTest() {
        panelneu1.agrePlayer(suguri);
        assertEquals(suguri, panelneu1.getUnits()[0]);
        var playernew = new UnitsPlayer("alv", 1, 1, 1, 1, null, null, 0, 0, 1);
        panelneu1.agrePlayer(playernew);
        assertNotEquals(playernew, panelneu1.getUnits()[0]);
        assertTrue(panelneu1.unitExist(playernew));
    }

    @Test
    public void unirPanPla() {
        panelneu1.unitPlayer(suguri);
        assertEquals(panelneu1, suguri.getUbi());
        assertTrue(panelneu1.unitExist(suguri));
        var playernew = new UnitsPlayer("alv", 1, 1, 1, 1, null, null, 0, 0, 1);
        assertFalse(panelneu1.unitExist(playernew));
    }

    @Test
    public void avanzarTest() throws IOException {
        panelneu1.addNextPanel(panelneu2);
        panelneu2.addNextPanel(panelneu3);
        panelneu1.unitPlayer(suguri);
        Panel obj = panelneu1.avanzar(suguri, 1);
        assertEquals(suguri, panelneu2.getUnits()[0]);
        assertEquals(panelneu2, obj);
        Panel obj2 = panelneu1.avanzar(suguri, 2);
        Panel obj3 = panelneu2.avanzar(suguri, 1);
        assertEquals(obj2, obj3);
    }

    @Test
    public void unitPlayerTest() {
        panelneu1.unitPlayer(suguri);
        assertEquals(panelneu1, suguri.getUbi());
        assertEquals(panelneu1.getUnits()[0], suguri);
    }

    @Test
    public void deletedPlayerTest() {
        panelneu1.agrePlayer(suguri);
        panelneu1.deletedPlayer(suguri);
        var expected = new Panel(null, null, null);

        assertEquals(expected, panelneu1);

        var suguri2 = new UnitsPlayer("nombre_creativo", BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD, null, null, 0, 0, 1);
        expected.agrePlayer(suguri2);
        panelneu1.agrePlayer(suguri2);
        panelneu1.agrePlayer(suguri); //ahora hay 2 players en panelneu1 y el espected es como esperamos que vuelva
        panelneu1.deletedPlayer(suguri);

        assertEquals(expected, panelneu1);

        //en panelneu1 solo esta suguri2, trataremos de eliminar suguri nuevamente
        panelneu1.deletedPlayer(suguri);

        assertEquals(expected, panelneu1); //en teoria panelneu1 no debio cambiar

        var suguri3 = new UnitsPlayer("nombre_creativo2", BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD, null, null, 0, 0, 1);
        var suguri4 = new UnitsPlayer("nombre_creativo3", BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD, null, null, 0, 0, 1);
        panelneu1.agrePlayer(suguri3);
        panelneu1.agrePlayer(suguri4);
        //Hay 3, ahora sacaremos a suguri3 por estar en medio
        panelneu1.deletedPlayer(suguri3);
        expected.agrePlayer(suguri4);
        assertEquals(expected, panelneu1);
    }
    
}

