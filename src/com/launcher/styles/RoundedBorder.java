package com.launcher.styles;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;

/**
 * Clase que representa un borde redondeado.
 */
public class RoundedBorder extends AbstractBorder {

    /**
     * Color del borde.
     */
    private Color color;

    /**
     * Radio del borde.
     */
    private int radius;

    /**
     * Crea un nuevo RoundedBorder.
     * @param c Color del borde.
     * @param r Radio del borde.
     */
    public RoundedBorder(Color c, int r) {
        this.color = c;
        this.radius = r;
    }

    /**
     * Pinta el borde redondeado.
     * @param c Componente.
     * @param g Gráficos.
     * @param x Posición x.
     * @param y Posición y.
     * @param width Ancho.
     * @param height Alto.
     */
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        super.paintBorder(c, g, x, y, width, height);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

    /**
     * Devuelve la forma del borde.
     * @param x Posición x.
     * @param y Posición y.
     * @param w Ancho.
     * @param h Alto.
     * @return Shape
     */
    public Shape getBorderShape(int x, int y, int w, int h) {
        return new RoundRectangle2D.Double(x, y, w, h, radius, radius);
    }
}