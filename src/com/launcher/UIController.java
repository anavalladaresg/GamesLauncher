package com.launcher;

import com.games.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase UIController que controla la interfaz de usuario.
 */
public class UIController {
    private final JFrame frame; // Marco de la ventana
    private User currentUser; // Usuario actual

    /**
     * Constructor de UIController.
     * Inicializa el marco de la ventana y coloca los componentes en el panel.
     */
    public UIController() {
        frame = new JFrame("Steam"); // Crear un nuevo marco con el título "Steam"
        frame.setSize(800, 500); // Establecer el tamaño del marco
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Establecer la operación de cierre

        JPanel panel = new JPanel(); // Crear un nuevo panel
        frame.add(panel); // Añadir el panel al marco
        placeComponents(panel); // Colocar los componentes en el panel

        frame.setVisible(true); // Hacer visible el marco
    }

    /**
     * Método para colocar los componentes en el panel.
     *
     * @param panel El panel en el que se colocarán los componentes.
     */
    private void placeComponents(JPanel panel) {
        panel.setLayout(new BorderLayout()); // Cambiar a BorderLayout

        // Crear un nuevo panel para la capa de color
        RoundedPanel colorLayer = new RoundedPanel(new BorderLayout(), 250, new Color(80, 65, 169));
        colorLayer.setPreferredSize(new Dimension(400, colorLayer.getHeight())); // Establecer el tamaño del panel
        panel.add(colorLayer, BorderLayout.EAST); // Añadir el panel a la derecha
        colorLayer.setLayout(null); // Establecer el diseño del panel

        // Crear un nuevo panel para los componentes existentes
        JPanel componentsPanel = new JPanel();
        componentsPanel.setLayout(null); // Establecer el diseño del panel

        // Crear y configurar las etiquetas
        createAndConfigureLabels(colorLayer);

        // Crear y configurar el botón de registro
        createAndConfigureSignUpButton(colorLayer);

        // Crear y configurar los componentes de inicio de sesión
        createAndConfigureSignInComponents(componentsPanel);

        componentsPanel.setBackground(new Color(238, 238, 238, 255)); // Establecer el color de fondo del panel

        panel.add(componentsPanel, BorderLayout.CENTER); // Agregar el panel de componentes al centro
    }

    private void createAndConfigureLabels(RoundedPanel colorLayer) {
        JLabel greetingLabel = new JLabel("WELCOME TO STEAM");
        greetingLabel.setBounds(110, 170, 500, 25);
        greetingLabel.setForeground(Color.white);
        greetingLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        colorLayer.add(greetingLabel);

        JLabel invitationLabel = new JLabel("<html><div style='text-align: center;'>Register with your personal details<br>to get started</html>");
        invitationLabel.setBounds(107, 200, 230, 50);
        invitationLabel.setForeground(Color.white);
        invitationLabel.setFont(new Font("Helvetica", Font.PLAIN, 14));
        colorLayer.add(invitationLabel);
    }

    private void createAndConfigureSignUpButton(RoundedPanel colorLayer) {
        JButton signUpButton = new JButton("SIGN UP");
        signUpButton.setBounds(140, 260, 150, 35);
        signUpButton.setOpaque(true); // Hacer el botón opaco
        signUpButton.setBackground(new Color(80, 65, 165)); // Color de fondo
        signUpButton.setForeground(Color.WHITE); // Color del texto

        // Establece el borde del botón a una delgada línea blanca
        Border whiteLineBorder = BorderFactory.createLineBorder(Color.WHITE);
        signUpButton.setBorder(whiteLineBorder);

        signUpButton.setContentAreaFilled(true); // Rellenar el área del contenido

        colorLayer.add(signUpButton);
    }


    private void createAndConfigureSignInComponents(JPanel componentsPanel) {
        JLabel userLabel = new JLabel("SIGN IN WITH YOUR STEAM ACCOUNT");
        userLabel.setBounds(10, 20, 280, 25);
        userLabel.setForeground(Color.orange);
        componentsPanel.add(userLabel);

        JTextField userText = new CustomTextField(20);
        userText.setBounds(7, 40, 300, 40);
        componentsPanel.add(userText);

        JLabel passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setBounds(10, 90, 80, 25);
        passwordLabel.setForeground(Color.DARK_GRAY);
        componentsPanel.add(passwordLabel);

        JPasswordField passwordText = new CustomPasswordField(20);
        passwordText.setBounds(7, 110, 300, 40);
        componentsPanel.add(passwordText);

        JButton loginButton = new JButton("Sign in");
        loginButton.setBounds(50, 160, 200, 35);
        componentsPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentUser.login(userText.getText(), new String(passwordText.getPassword()))) {
                    JOptionPane.showMessageDialog(frame, "Login successful!"); // Mostrar un mensaje de éxito
                    displayMainMenu(); // Mostrar el menú principal
                } else {
                    JOptionPane.showMessageDialog(frame, "Login failed. Please try again."); // Mostrar un mensaje de error
                }
            }
        });
    }

    /**
     * Método para mostrar la pantalla de inicio de sesión.
     */
    public void displayLoginScreen() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UIController(); // Crear una nueva instancia de UIController
            }
        });
    }

    /**
     * Método para mostrar el menú principal.
     */
    public void displayMainMenu() {
    }

    /**
     * Método para ver los detalles del juego.
     *
     * @param game El juego cuyos detalles se van a ver.
     */
    public void viewGameDetails(Game game) {
    }
}