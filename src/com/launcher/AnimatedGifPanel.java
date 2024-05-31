package com.launcher;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AnimatedGifPanel extends JPanel {

    private ImageIcon gifImage;

    public AnimatedGifPanel() {
        try {
            // Cargar la imagen GIF como ImageIcon
            gifImage = new ImageIcon("src/com/images/fondo.gif");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gifImage != null) {
            // Dibujar el GIF animado
            gifImage.paintIcon(this, g, 0, 0);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(gifImage.getIconWidth(), gifImage.getIconHeight());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animated GIF Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        AnimatedGifPanel animatedGifPanel = new AnimatedGifPanel();
        frame.add(animatedGifPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
