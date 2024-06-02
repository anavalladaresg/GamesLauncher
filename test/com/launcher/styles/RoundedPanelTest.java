package com.launcher.styles;

import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

public class RoundedPanelTest {

    @Test
    void roundedPanelCanBeInstantiated() {
        assertDoesNotThrow(() -> {
            RoundedPanel panel = new RoundedPanel(new FlowLayout(), 15, Color.BLACK);
        });
    }

    @Test
    void roundedPanelBackgroundColorCanBeSetAndRetrieved() {
        RoundedPanel panel = new RoundedPanel(new FlowLayout(), 15, Color.BLACK);
        panel.setBackground(Color.RED);
        assertEquals(Color.RED, panel.getBackground());
    }

    @Test
    void roundedPanelForegroundCanBeSetAndRetrieved() {
        RoundedPanel panel = new RoundedPanel(new FlowLayout(), 15, Color.BLACK);
        panel.setForeground(Color.RED);
        assertEquals(Color.RED, panel.getForeground());
    }
}