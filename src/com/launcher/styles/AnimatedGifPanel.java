package com.launcher.styles;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Clase que representa un panel con una imagen GIF animada.
 */
public class AnimatedGifPanel extends JPanel {

    /**
     * Imagen GIF animada
     */
    private ImageIcon gifImage;

    /**
     * Constructor por defecto.
     */
    public AnimatedGifPanel() {
        try {
            // Cargar la imagen GIF como ImageIcon
            gifImage = new ImageIcon("src/com/images/fondo.gif");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Protege el <code>Graphics</code> del componente.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gifImage != null) {
            // Dibujar el GIF animado
            gifImage.paintIcon(this, g, 0, 0);
        }
    }

    /**
     * Devuelve el tamaño preferido del panel.
     * @return Dimension
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(gifImage.getIconWidth(), gifImage.getIconHeight());
    }
}
