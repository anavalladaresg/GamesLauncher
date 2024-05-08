package com.launcher;

import com.games.Game;
import database.DatabaseHandler;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpController {
    private final JFrame frame; // Window frame
    private final User currentUser = new User(); // Current user

    /**
     * UIController constructor.
     * Initializes the window frame and places the components on the panel.
     */
    public SignUpController() {
        frame = new JFrame("Xynx"); // Create a new frame with the title "Xynx"
        frame.setSize(800, 500); // Set the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the close operation
        frame.setResizable(false); // Esto deshabilita el redimensionamiento

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
        SignUpRoundedPanel colorLayer = new SignUpRoundedPanel(new BorderLayout(), 250, new Color(80, 65, 169));
        colorLayer.setPreferredSize(new Dimension(400, colorLayer.getHeight())); // Set the size of the panel
        panel.add(colorLayer, BorderLayout.WEST); // Add the panel to the right
        colorLayer.setLayout(null); // Set the layout of the panel

        // Create a new panel for the existing components
        JPanel componentsPanel = new JPanel();
        componentsPanel.setLayout(null); // Set the layout of the panel

        // Create and configure the labels
        createAndConfigureLabels(colorLayer);

        // Create and configure the sign up button
        createAndConfigureSignInButton(colorLayer);

        // Create and configure the sign in components
        createAndConfigureSignInComponents(componentsPanel);

        componentsPanel.setBackground(new Color(238, 238, 238, 255)); // Set the background color of the panel

        panel.add(componentsPanel, BorderLayout.CENTER); // Add the components panel to the center
    }

    private void createAndConfigureLabels(SignUpRoundedPanel colorLayer) {
        // Cargar la imagen
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/com/images/Xynx.png"));// Reemplaza "/path/to/Xynx.png" con la ruta real de tu imagen
        Image image = imageIcon.getImage(); // Transforma el ImageIcon en Image
        Image scaledImage = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // Escala la imagen
        imageIcon = new ImageIcon(scaledImage); // Transforma la Image escalada de nuevo a ImageIcon

        // Crear un JLabel para la imagen
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(90, 30, 200, 200); // Ajusta estos valores para posicionar la imagen correctamente
        colorLayer.add(imageLabel);

        // Crear un JLabel para el saludo
        JLabel greetingLabel = new JLabel("WELCOME TO XYNX");
        greetingLabel.setBounds(85, 200, 500, 25);
        greetingLabel.setForeground(Color.white);
        greetingLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        colorLayer.add(greetingLabel);

        // Crear un JLabel para la invitación
        JLabel invitationLabel = new JLabel("<html><div style='text-align: center;'>Sign in with your account<br>to get started</html>");
        invitationLabel.setBounds(105, 230, 230, 50);
        invitationLabel.setForeground(Color.white);
        invitationLabel.setFont(new Font("Helvetica", Font.PLAIN, 14));
        colorLayer.add(invitationLabel);
    }

    private void createAndConfigureSignInButton(SignUpRoundedPanel colorLayer) {
        JButton signUpButton = new JButton("SIGN IN") {
            // Override paintComponent to provide our own paint methods while preserving the original functionality
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedBorder) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setPaint(getBackground());
                    g2.fill(((RoundedBorder) getBorder()).getBorderShape(
                            0, 0, getWidth() - 1, getHeight() - 1));
                    g2.dispose();
                }
                super.paintComponent(g);
            }
        };
        signUpButton.setBounds(105, 295, 150, 35);
        signUpButton.setOpaque(false); // Make the button non-opaque
        signUpButton.setBackground(new Color(80, 65, 165)); // Background color
        signUpButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        signUpButton.setForeground(Color.WHITE); // Text color

        // Set the border of the button to a rounded border with a thin white line
        Border whiteLineBorder = new RoundedBorder(Color.WHITE, 10); // 10 is the radius of the border
        signUpButton.setBorder(whiteLineBorder);

        signUpButton.setContentAreaFilled(true); // Fill the content area

        colorLayer.add(signUpButton);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySignInScreen();
            }
        });
    }

    /**
     * Method to create and configure the sign up button.
     *
     * @param componentsPanel The panel on which the button will be placed.
     */
    private void createAndConfigureSignInComponents(JPanel componentsPanel) {

        // Centra la ventana en la pantalla
        frame.setLocationRelativeTo(null);

        JLabel signInLabel = new JLabel("SIGN UP");
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
        userText.setForeground(new Color(0, 0, 0));
        userText.setBackground(new Color(224, 224, 224, 255));
        userText.setBorder(new CompoundBorder(new RoundedBorder(new Color(114, 114, 114), 10), new EmptyBorder(0, 10, 0, 0)));
        userText.setBounds(40, 155, 300, 40);
        componentsPanel.add(userText);

        // Password text
        JLabel passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setBounds(40, 220, 180, 25);
        passwordLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
        componentsPanel.add(passwordLabel);

        // Password field
        PlaceholderPasswordField passwordText = new PlaceholderPasswordField(20);
        passwordText.setPrompt("Enter your password");
        passwordText.setBounds(40, 255, 300, 40);
        passwordText.setBackground(new Color(224, 224, 224, 255));
        passwordText.setBorder(new CompoundBorder(new RoundedBorder(new Color(114, 114, 114), 10), new EmptyBorder(0, 10, 0, 0)));
        componentsPanel.add(passwordText);

        // Sign in button
        JButton signUpButton = new JButton("SIGN UP") {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedBorder) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setPaint(getBackground());
                    g2.fill(((RoundedBorder) getBorder()).getBorderShape(0, 0, getWidth() - 1, getHeight() - 1));
                    g2.dispose();
                }
                super.paintComponent(g);
            }
        };
        signUpButton.setBounds(125, 320, 150, 35); // Set the bounds of the button
        signUpButton.setOpaque(false); // Make the button non-opaque
        signUpButton.setBackground(new Color(80, 65, 165)); // Set the background color
        signUpButton.setFont(new Font("Helvetica", Font.BOLD, 14)); // Set the font
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setBorder(new RoundedBorder(Color.WHITE, 10)); // 10 is the radius of the border
        signUpButton.setContentAreaFilled(true);
        componentsPanel.add(signUpButton);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/com/images/XynxLogoVentana.png"));
        Image image = imageIcon.getImage(); // Transforma el ImageIcon en Image
        frame.setIconImage(image); // Establece la imagen como icono de la ventana

        // Add a listener to the sign in button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mover estas líneas dentro del ActionListener
                currentUser.setUserName(userText.getText());
                currentUser.setPassword(new String(passwordText.getPassword()));

                if (currentUser.signUp(userText.getText(), new String(passwordText.getPassword()))) {
                    DatabaseHandler db = new DatabaseHandler();
                    db.addUser(currentUser.getUserName(), currentUser.getPassword());
                    JOptionPane.showMessageDialog(frame, "Account created!"); // Show a success message
                    displayMainMenu(); // Show the main menu
                } else {
                    JOptionPane.showMessageDialog(frame, "Sign up failed. Please try again."); // Show an error message
                }
            }
        });
    }

    /**
     * Method to display the login screen.
     */
    public void displaySignInScreen() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SignInController(); // Create a new instance of UIController
            }
        });
    }

    /**
     * Method to display the main menu.
     */
    public void displayMainMenu() {
        System.out.println("Vas bien nena");
    }

    /**
     * Method to view the game details.
     *
     * @param game The game whose details will be viewed.
     */
    public void viewGameDetails(Game game) {
    }
}
