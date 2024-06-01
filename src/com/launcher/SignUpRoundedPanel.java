package com.launcher;

import javax.swing.*;
import java.awt.*;

/**
 * Esta clase representa un panel con esquinas redondeadas en el lado izquierdo.
 */
class SignUpRoundedPanel extends JPanel {
    private final Color backgroundColor;
    private final int cornerRadius;

    /**
     * Crea un nuevo SignUpRoundedPanel.
     *
     * @param layout  El layout a utilizar.
     * @param radius  El radio de las esquinas.
     * @param bgColor El color de fondo.
     */
    public SignUpRoundedPanel(LayoutManager layout, int radius, Color bgColor) {
        super(layout);
        cornerRadius = radius;
        backgroundColor = bgColor;
    }

    /**
     * Este método se encarga de pintar el panel.
     * @param g El objeto Graphics.
     */
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
        graphics.fillRect(0, 0, width / 2, cornerRadius);
        graphics.fillRect(0, height - cornerRadius, width / 2, cornerRadius);
    }
}