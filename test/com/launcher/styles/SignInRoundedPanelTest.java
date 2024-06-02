package com.launcher.styles;

import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class SignInRoundedPanelTest {

    @Test
    void signInRoundedPanelCanBeInstantiated() {
        assertDoesNotThrow(() -> {
            SignInRoundedPanel panel = new SignInRoundedPanel(new FlowLayout(), 15, Color.BLACK);
        });
    }

    @Test
    void signInRoundedPanelPaintComponentDoesNotThrowException() {
        assertDoesNotThrow(() -> {
            SignInRoundedPanel panel = new SignInRoundedPanel(new FlowLayout(), 15, Color.BLACK);
            panel.paintComponent(new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).getGraphics());
        });
    }
}