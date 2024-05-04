package com.launcher;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;

public class RoundedBorder extends AbstractBorder {
    private Color color;
    private int radius;

    RoundedBorder(Color c, int r) {
        this.color = c;
        this.radius = r;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        super.paintBorder(c, g, x, y, width, height);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

    public Shape getBorderShape(int x, int y, int w, int h) {
        return new RoundRectangle2D.Double(x, y, w, h, radius, radius);
    }
}