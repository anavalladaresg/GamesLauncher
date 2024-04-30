package com.launcher;

import com.games.Game;

import javax.swing.*;
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
        panel.setLayout(null); // Establecer el diseño del panel

        // Crear y configurar la etiqueta del usuario
        JLabel userLabel = new JLabel("SIGN IN WITH YOUR STEAM ACCOUNT");
        userLabel.setBounds(10, 20, 280, 25);
        userLabel.setForeground(Color.orange);
        panel.add(userLabel);

        // Crear y configurar el campo de texto del usuario
        JTextField userText = new CustomTextField(20);
        userText.setBounds(7, 40, 300, 40);
        panel.add(userText);

        // Crear y configurar la etiqueta de la contraseña
        JLabel passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setBounds(10, 90, 80, 25);
        passwordLabel.setForeground(Color.DARK_GRAY);
        panel.add(passwordLabel);

        // Crear y configurar el campo de texto de la contraseña
        JPasswordField passwordText = new CustomPasswordField(20);
        passwordText.setBounds(7, 110, 300, 40);
        panel.add(passwordText);

        // Crear y configurar el botón de inicio de sesión
        JButton loginButton = new JButton("Sign in");
        loginButton.setBounds(50, 160, 200, 35);
        loginButton.setBackground(Color.orange);
        panel.add(loginButton);

        panel.setBackground(Color.gray); // Establecer el color de fondo del panel

        // Añadir un oyente de acción al botón de inicio de sesión
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Intentar iniciar sesión con el nombre de usuario y la contraseña proporcionados
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