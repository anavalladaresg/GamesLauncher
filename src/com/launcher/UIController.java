package com.launcher;

import com.games.Game;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * UIController class that controls the user interface.
 */
public class UIController {
    private final JFrame frame; // Window frame
    private User currentUser; // Current user

    /**
     * UIController constructor.
     * Initializes the window frame and places the components on the panel.
     */
    public UIController() {
        frame = new JFrame("Steam"); // Create a new frame with the title "Steam"
        frame.setSize(800, 500); // Set the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the close operation

        JPanel panel = new JPanel(); // Create a new panel
        frame.add(panel); // Add the panel to the frame
        placeComponents(panel); // Place the components on the panel

        frame.setVisible(true); // Make the frame visible
    }

    /**
     * Method to place the components on the panel.
     *
     * @param panel The panel on which the components will be placed.
     */
    private void placeComponents(JPanel panel) {
        panel.setLayout(new BorderLayout()); // Change to BorderLayout

        // Create a new panel for the color layer
        RoundedPanel colorLayer = new RoundedPanel(new BorderLayout(), 250, new Color(80, 65, 169));
        colorLayer.setPreferredSize(new Dimension(400, colorLayer.getHeight())); // Set the size of the panel
        panel.add(colorLayer, BorderLayout.EAST); // Add the panel to the right
        colorLayer.setLayout(null); // Set the layout of the panel

        // Create a new panel for the existing components
        JPanel componentsPanel = new JPanel();
        componentsPanel.setLayout(null); // Set the layout of the panel

        // Create and configure the labels
        createAndConfigureLabels(colorLayer);

        // Create and configure the sign up button
        createAndConfigureSignUpButton(colorLayer);

        // Create and configure the sign in components
        createAndConfigureSignInComponents(componentsPanel);

        componentsPanel.setBackground(new Color(238, 238, 238, 255)); // Set the background color of the panel

        panel.add(componentsPanel, BorderLayout.CENTER); // Add the components panel to the center
    }

    private void createAndConfigureLabels(RoundedPanel colorLayer) {
        // Cargar la imagen
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/com/images/Xynx.png"));// Reemplaza "/path/to/Xynx.png" con la ruta real de tu imagen
        Image image = imageIcon.getImage(); // Transforma el ImageIcon en Image
        Image scaledImage = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // Escala la imagen
        imageIcon = new ImageIcon(scaledImage); // Transforma la Image escalada de nuevo a ImageIcon

        // Crear un JLabel para la imagen
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(120, 0, 200, 200); // Ajusta estos valores para posicionar la imagen correctamente
        colorLayer.add(imageLabel);

        // Crear un JLabel para el saludo
        JLabel greetingLabel = new JLabel("WELCOME TO STEAM");
        greetingLabel.setBounds(110, 170, 500, 25);
        greetingLabel.setForeground(Color.white);
        greetingLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        colorLayer.add(greetingLabel);

        // Crear un JLabel para la invitación
        JLabel invitationLabel = new JLabel("<html><div style='text-align: center;'>Register with your personal details<br>to get started</html>");
        invitationLabel.setBounds(107, 200, 230, 50);
        invitationLabel.setForeground(Color.white);
        invitationLabel.setFont(new Font("Helvetica", Font.PLAIN, 14));
        colorLayer.add(invitationLabel);
    }

    private void createAndConfigureSignUpButton(RoundedPanel colorLayer) {
        JButton signUpButton = new JButton("SIGN UP");
        signUpButton.setBounds(140, 260, 150, 35);
        signUpButton.setOpaque(true); // Make the button opaque
        signUpButton.setBackground(new Color(80, 65, 165)); // Background color
        signUpButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        signUpButton.setForeground(Color.WHITE); // Text color

        // Set the border of the button to a thin white line
        Border whiteLineBorder = BorderFactory.createLineBorder(Color.WHITE);
        signUpButton.setBorder(whiteLineBorder);

        signUpButton.setContentAreaFilled(true); // Fill the content area

        colorLayer.add(signUpButton);
    }

    private void createAndConfigureSignInComponents(JPanel componentsPanel) {

        JLabel signInLabel = new JLabel("SIGN IN");
        signInLabel.setBounds(135, 60, 280, 30);
        signInLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        componentsPanel.add(signInLabel);

        // Sign in text
        JLabel userLabel = new JLabel("USERNAME");
        userLabel.setBounds(40, 120, 280, 25);
        userLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
        componentsPanel.add(userLabel);

        // Text field for username
        PlaceholderTextField userText = new PlaceholderTextField(20);
        userText.setPrompt("Enter your username");
        userText.setBackground(new Color(215, 215, 215, 255));
        userText.setBorder(new EmptyBorder(0, 10, 0, 0)); // Set margin to align text
        userText.setBounds(40, 155, 300, 40);
        componentsPanel.add(userText);

        // Password text
        JLabel passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setBounds(40, 220, 180, 25);
        passwordLabel.setForeground(Color.DARK_GRAY);
        passwordLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
        componentsPanel.add(passwordLabel);

        // Password field
        PlaceholderPasswordField passwordText = new PlaceholderPasswordField(20);
        passwordText.setPrompt("Enter your password");
        passwordText.setBounds(40, 255, 300, 40);
        passwordText.setBackground(new Color(215, 215, 215, 255));
        passwordText.setBorder(new EmptyBorder(0, 10, 0, 0));
        componentsPanel.add(passwordText);

        // Sign in button
        JButton loginButton = new JButton("SIGN IN");
        loginButton.setBounds(115, 330, 150, 35);
        loginButton.setOpaque(true);
        loginButton.setBackground(new Color(80, 65, 165));
        loginButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        loginButton.setContentAreaFilled(true);
        componentsPanel.add(loginButton);

        // Add a listener to the sign in button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentUser.login(userText.getText(), new String(passwordText.getPassword()))) {
                    JOptionPane.showMessageDialog(frame, "Login successful!"); // Show a success message
                    displayMainMenu(); // Show the main menu
                } else {
                    JOptionPane.showMessageDialog(frame, "Login failed. Please try again."); // Show an error message
                }
            }
        });
    }

    /**
     * Method to display the login screen.
     */
    public void displayLoginScreen() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UIController(); // Create a new instance of UIController
            }
        });
    }

    /**
     * Method to display the main menu.
     */
    public void displayMainMenu() {
    }

    /**
     * Method to view the game details.
     *
     * @param game The game whose details will be viewed.
     */
    public void viewGameDetails(Game game) {
    }
}