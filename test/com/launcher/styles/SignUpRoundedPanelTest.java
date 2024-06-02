package com.launcher.styles;

import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class SignUpRoundedPanelTest {

    @Test
    void signUpRoundedPanelCanBeInstantiated() {
        assertDoesNotThrow(() -> {
            SignUpRoundedPanel panel = new SignUpRoundedPanel(new FlowLayout(), 15, Color.BLACK);
        });
    }

    @Test
    void signUpRoundedPanelPaintComponentDoesNotThrowException() {
        assertDoesNotThrow(() -> {
            SignUpRoundedPanel panel = new SignUpRoundedPanel(new FlowLayout(), 15, Color.BLACK);
            panel.paintComponent(new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).getGraphics());
        });
    }
}