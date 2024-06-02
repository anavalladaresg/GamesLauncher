package com.launcher.styles;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class AnimatedGifPanelTest {

    @Test
    void animatedGifPanelCanBeInstantiated() {
        assertDoesNotThrow(() -> {
            AnimatedGifPanel panel = new AnimatedGifPanel();
        });
    }

    @Test
    void getPreferredSizeReturnsCorrectDimension() {
        AnimatedGifPanel panel = new AnimatedGifPanel();
        Dimension expectedDimension = new Dimension(panel.gifImage.getIconWidth(), panel.gifImage.getIconHeight());
        assertEquals(expectedDimension, panel.getPreferredSize());
    }
}