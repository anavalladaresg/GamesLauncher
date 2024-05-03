package com.launcher;

import javax.swing.*;
import java.awt.*;

class RoundedPanel extends JPanel {
    private Color backgroundColor;
    private int cornerRadius = 15;

    public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
        super(layout);
        cornerRadius = radius;
        backgroundColor = bgColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draws the rounded panel with borders.
        graphics.setColor(backgroundColor);
        graphics.fillRoundRect(0, 0, width - 1, height - 1, cornerRadius, cornerRadius); //paint background
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width - 1, height - 1, cornerRadius, cornerRadius); //paint border

        // Draws the right corners as square.
        graphics.setColor(backgroundColor); // Use the same background color
        graphics.fillRect(width / 2, 0, width / 2, cornerRadius);
        graphics.fillRect(width / 2, height - cornerRadius, width / 2, cornerRadius);
    }
}