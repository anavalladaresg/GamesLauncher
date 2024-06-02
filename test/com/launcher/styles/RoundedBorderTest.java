package com.launcher.styles;

import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import static org.junit.jupiter.api.Assertions.*;

public class RoundedBorderTest {

    @Test
    void roundedBorderCanBeInstantiated() {
        assertDoesNotThrow(() -> {
            RoundedBorder border = new RoundedBorder(Color.BLACK, 10);
        });
    }

    @Test
    void getBorderShapeReturnsCorrectShape() {
        RoundedBorder border = new RoundedBorder(Color.BLACK, 10);
        Shape expectedShape = new RoundRectangle2D.Double(0, 0, 100, 100, 10, 10);
        assertEquals(expectedShape, border.getBorderShape(0, 0, 100, 100));
    }
}