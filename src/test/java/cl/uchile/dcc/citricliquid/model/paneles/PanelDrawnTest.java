package cl.uchile.dcc.citricliquid.model.paneles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PanelDrawnTest {
    PanelDrawn panelDrawn;

    @BeforeEach
    void setUp() {
        panelDrawn = new PanelDrawn(null,null,null);
    }

    @Test
    void buildTest() {
        final var expected = new PanelDrawn(null,null,null);
        assertEquals(expected,panelDrawn);
    }
}