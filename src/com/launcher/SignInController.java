package com.launcher;

import database.DatabaseHandler;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class controls the user interface.
 */
public class SignInController {
    private static final Color purple = new Color(80, 65, 165);
    private final JFrame frame;
    private final User currentUser;

    /**
     * Constructor for the SignInController class.
     */
    public SignInController() {
        frame = createFrame();
        currentUser = new User();
    }

    /**
     * This method returns the purple color.
     *
     * @return The purple color.
     */
    public static Color getPurple() {
        return purple;
    }

    /**
     * This method creates the frames and panels for the UI.
     *
     * @return The principal frame.
     */
    private JFrame createFrame() {
        JFrame frame = new JFrame("Xynx");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
        frame.add(panel);

        SignInRoundedPanel colorLayer = createColorLayer();
        panel.add(colorLayer, BorderLayout.EAST);

        JPanel componentsPanel = createComponentsPanel();
        panel.add(componentsPanel, BorderLayout.CENTER);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/com/images/XynxLogoVentana.png"));
        Image image = imageIcon.getImage();
        frame.setIconImage(image);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        return frame;
    }

    /**
     * This method creates the color panel.
     *
     * @return The color panel.
     */
    private SignInRoundedPanel createColorLayer() {
        SignInRoundedPanel colorLayer = new SignInRoundedPanel(new BorderLayout(), 250, purple);
        colorLayer.setPreferredSize(new Dimension(400, colorLayer.getHeight()));
        colorLayer.setLayout(null);

        createAndConfigureLabels(colorLayer);
        createAndConfigureSignUpButton(colorLayer);

        return colorLayer;
    }

    /**
     * This method creates the components panel.
     *
     * @return The components panel.
     */
    private JPanel createComponentsPanel() {
        JPanel componentsPanel = new JPanel();
        componentsPanel.setLayout(null);
        componentsPanel.setBackground(new Color(238, 238, 238, 255));

        createAndConfigureSignInComponents(componentsPanel);

        return componentsPanel;
    }

    /**
     * This method creates and configures the labels.
     *
     * @param colorLayer The color layer.
     */
    private void createAndConfigureLabels(SignInRoundedPanel colorLayer) {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/com/images/Xynx.png"));
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(120, 30, 200, 200);
        colorLayer.add(imageLabel);

        JLabel greetingLabel = new JLabel("WELCOME TO XYNX");
        greetingLabel.setBounds(115, 200, 500, 25);
        greetingLabel.setForeground(Color.white);
        greetingLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        colorLayer.add(greetingLabel);

        JLabel invitationLabel = new JLabel("<html><div style='text-align: center;'>If you already have an account, press the Sign In button.</html>");
        invitationLabel.setBounds(100, 230, 230, 50);
        invitationLabel.setForeground(Color.white);
        invitationLabel.setFont(new Font("Helvetica", Font.PLAIN, 14));
        colorLayer.add(invitationLabel);
    }

    /**
     * This method creates and configures the sign up button.
     *
     * @param colorLayer The color layer.
     */
    private void createAndConfigureSignUpButton(SignInRoundedPanel colorLayer) {
        JButton signUpButton = new JButton("SIGN UP") {
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
        signUpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                signUpButton.setBackground(new Color(60, 45, 145));
                signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                signUpButton.setBackground(purple);
                signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        signUpButton.setBounds(140, 295, 150, 35);
        signUpButton.setOpaque(false);
        signUpButton.setBackground(purple);
        signUpButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        signUpButton.setForeground(Color.WHITE);

        Border whiteLineBorder = new RoundedBorder(Color.WHITE, 10);
        signUpButton.setBorder(whiteLineBorder);

        signUpButton.setContentAreaFilled(true);

        colorLayer.add(signUpButton);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySignUpScreen();
            }
        });
    }

    /**
     * This method creates and configures the sign in components.
     *
     * @param componentsPanel The components panel.
     */
    private void createAndConfigureSignInComponents(JPanel componentsPanel) {
        JLabel signInLabel = new JLabel("SIGN IN");
        signInLabel.setBounds(135, 60, 280, 30);
        signInLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        componentsPanel.add(signInLabel);

        JLabel userLabel = new JLabel("USERNAME");
        userLabel.setBounds(40, 120, 280, 25);
        userLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
        componentsPanel.add(userLabel);

        PlaceholderTextField userText = new PlaceholderTextField(20);
        userText.setPrompt("Enter your username");
        userText.setForeground(new Color(0, 0, 0));
        userText.setBackground(new Color(224, 224, 224, 255));
        userText.setBorder(new CompoundBorder(new RoundedBorder(new Color(114, 114, 114), 10), new EmptyBorder(0, 10, 0, 0)));
        userText.setBounds(40, 155, 300, 40);
        componentsPanel.add(userText);

        JLabel passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setBounds(40, 220, 180, 25);
        passwordLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
        componentsPanel.add(passwordLabel);

        PlaceholderPasswordField passwordText = new PlaceholderPasswordField(20);
        passwordText.setPrompt("Enter your password");
        passwordText.setBounds(40, 255, 300, 40);
        passwordText.setBackground(new Color(224, 224, 224, 255));
        passwordText.setBorder(new CompoundBorder(new RoundedBorder(new Color(114, 114, 114), 10), new EmptyBorder(0, 10, 0, 0)));
        componentsPanel.add(passwordText);

        JButton loginButton = new JButton("SIGN IN") {
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
        loginButton.setBounds(115, 330, 150, 35);
        loginButton.setOpaque(false);
        loginButton.setBackground(purple);
        loginButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(new RoundedBorder(Color.WHITE, 10));
        loginButton.setContentAreaFilled(true);
        componentsPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentUser.setUserName(userText.getText());
                currentUser.setPassword(new String(passwordText.getPassword()));

                DatabaseHandler db = new DatabaseHandler();
                if (db.userExists(currentUser.getUserName())) {
                    if (currentUser.login(userText.getText(), new String(passwordText.getPassword()))) {
                        displayMainMenu();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Login failed. Please try again.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "<html><font color='red'>User does not exist. Please sign up.</font></html>",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(new Color(60, 45, 145));
                loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(purple);
                loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    /**
     * This method displays the sign up screen.
     */
    public void displaySignUpScreen() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.dispose();
                new SignUpController();
            }
        });
    }

    /**
     * This method displays the main menu.
     */
    public void displayMainMenu() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.dispose();
                new LibraryController();
            }
        });
    }

}