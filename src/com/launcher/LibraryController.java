package com.launcher;

import javax.swing.*;
import java.awt.*;

/**
 * Controller for the game library.
 */
public class LibraryController {
    private JFrame frame;
    private JPanel panel;

    /**
     * Constructor for LibraryController.
     */
    public LibraryController() {
        frame = new JFrame();
        panel = new JPanel();
        frame.setTitle("Game Library");
        panel.setBackground(new Color(224, 224, 224, 255));
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}