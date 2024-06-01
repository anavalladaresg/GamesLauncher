package com.launcher;

import com.games.Game;
import database.DatabaseHandler;
import database.UserDatabaseHandler;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

/**
 * Representa un controlador para la pantalla de registro.
 */
public class SignUpController {

    /**
     * La ventana.
     */
    private final JFrame frame;

    /**
     * El usuario actual.
     */
    private final User currentUser = new User();

    /**
     * Inicializa un nuevo SignUpController.
     */
    public SignUpController() {
        frame = new JFrame("Xynx");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    /**
     * Método para colocar los componentes en el panel.
     * @param panel El panel en el que se colocarán los componentes.
     */
    private void placeComponents(JPanel panel) {
        panel.setLayout(new BorderLayout());
        SignUpRoundedPanel colorLayer = new SignUpRoundedPanel(new BorderLayout(), 250, new Color(80, 65, 169));
        colorLayer.setPreferredSize(new Dimension(400, colorLayer.getHeight()));
        panel.add(colorLayer, BorderLayout.WEST);
        colorLayer.setLayout(null);
        JPanel componentsPanel = new JPanel();
        componentsPanel.setLayout(null);
        createAndConfigureLabels(colorLayer);
        createAndConfigureSignInButton(colorLayer);
        createAndConfigureSignInComponents(componentsPanel);
        componentsPanel.setBackground(new Color(238, 238, 238, 255));
        panel.add(componentsPanel, BorderLayout.CENTER);
    }

    /**
     * Método para crear y configurar las etiquetas.
     * @param colorLayer El panel en el que se colocarán las etiquetas.
     */
    private void createAndConfigureLabels(SignUpRoundedPanel colorLayer) {
        // Cargar la imagen
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/com/images/Xynx.png"));
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);

        // Crear un JLabel para la imagen
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(90, 30, 200, 200);
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

    /**
     * Método para crear y configurar el botón de inicio de sesión.
     * @param colorLayer El panel en el que se colocará el botón.
     */
    private void createAndConfigureSignInButton(SignUpRoundedPanel colorLayer) {
        JButton signUpButton = getSignUpButton();
        signUpButton.setOpaque(false);
        signUpButton.setBackground(new Color(80, 65, 165));
        signUpButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        signUpButton.setForeground(Color.WHITE);
        Border whiteLineBorder = new RoundedBorder(Color.WHITE, 10);
        signUpButton.setBorder(whiteLineBorder);
        signUpButton.setContentAreaFilled(true);
        colorLayer.add(signUpButton);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySignInScreen();
            }
        });

        configureListenerToSignUpButton(signUpButton);
    }

    /**
     * Método para obtener el botón de registro.
     * @return JButton
     */
    private static JButton getSignUpButton() {
        JButton signUpButton = new JButton("SIGN IN") {
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
        return signUpButton;
    }

    /**
     * Método para configurar el listener del botón de registro.
     * @param signUpButton El botón de registro.
     */
    private void configureListenerToSignUpButton(JButton signUpButton) {
        signUpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                signUpButton.setBackground(new Color(60, 45, 145));
                signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                signUpButton.setBackground(new Color(80, 65, 165));
                signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    /**
     * Método para crear y configurar los componentes de inicio de sesión.
     * @param componentsPanel El panel en el que se colocarán los componentes.
     */
    private void createAndConfigureSignInComponents(JPanel componentsPanel) {

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

        JButton signUpButton = getUpButton();
        componentsPanel.add(signUpButton);

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/images/XynxLogoVentana.png")));
        Image image = imageIcon.getImage();
        frame.setIconImage(image);


        configureKeyListener(userText, passwordText, signUpButton);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                String regex = "^[a-zA-Z0-9]+$"; //Solo permite caracteres alfanuméricos

                if (!username.matches(regex) || !password.matches(regex)) {
                    JOptionPane.showMessageDialog(frame,
                            "<html><font color='red'>Username and password can only contain alphanumeric characters.</font></html>",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                currentUser.setUserName(username);
                currentUser.setPassword(password);

                UserDatabaseHandler udb = new UserDatabaseHandler();
                if (udb.userExists(currentUser.getUserName())) {
                    JOptionPane.showMessageDialog(frame,
                            "<html><font color='red'>User already exists. Please choose a different username.</font></html>",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    udb.addUser(currentUser.getUserName(), currentUser.getPassword());
                    JOptionPane.showMessageDialog(frame, "Registration successful!");
                    displayMainMenu();
                }
            }
        });
        configureListenerToSignUpButton(signUpButton);
    }

    static void configureKeyListener(PlaceholderTextField userText, PlaceholderPasswordField passwordText, JButton signUpButton) {
        userText.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    signUpButton.doClick();
                }
            }
        });

        passwordText.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    signUpButton.doClick();
                }
            }
        });
    }

    /**
     * Método para obtener el botón de registro.
     * @return JButton
     */
    private static JButton getUpButton() {
        JButton signUpButton = getButton();
        signUpButton.setOpaque(false); // Make the button non-opaque
        signUpButton.setBackground(new Color(80, 65, 165));
        signUpButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setBorder(new RoundedBorder(Color.WHITE, 10));
        signUpButton.setContentAreaFilled(true);
        return signUpButton;
    }

    /**
     * Método para obtener un botón.
     * @return JButton
     */
    private static JButton getButton() {
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
        signUpButton.setBounds(125, 320, 150, 35);
        return signUpButton;
    }

    /**
     * Método para mostrar la pantalla de inicio de sesión.
     */
    public void displaySignInScreen() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.dispose();
                new SignInController();
            }
        });
    }

    /**
     * Método para mostrar el menú principal.
     */
    public void displayMainMenu() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.dispose();
                new LibraryController(currentUser.getUserName());
            }
        });
    }
}