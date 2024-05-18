package com.launcher;

import com.games.Game;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class represents the controller for the library.
 * It sets up the UI for the library and handles user interactions.
 */
public class LibraryController {

    /**
     * Constructor for the LibraryController class.
     * It initializes the UI components and sets up the library view.
     */
    public LibraryController() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        ArrayList<Game> games = new ArrayList<>();
        Library library = new Library();
        JPopupMenu gameMenu = new JPopupMenu();

        // Load the image
        ImageIcon imageIcon = new ImageIcon("src/com/images/Xynx.png");
        Image image = imageIcon.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        leftPanel.add(imageLabel);

        frame.setTitle("Game Library");
        panel.setBackground(new Color(224, 224, 224, 255));
        leftPanel.setBackground(SignInController.getPurple());
        leftPanel.setPreferredSize(new Dimension(350, 100));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.add(leftPanel, BorderLayout.WEST);
        frame.add(panel);
        frame.setVisible(true);

        // Create the label
        JLabel libraryLabel = new JLabel("Library");
        libraryLabel.setFont(new Font("Helvetica", Font.BOLD, 19));
        libraryLabel.setBorder(new EmptyBorder(0, 35, 0, 170)); // Adjust right margin as needed.
        libraryLabel.setForeground(Color.WHITE);
        leftPanel.add(libraryLabel);

        // Create the button for adding a game
        JButton addButton = new JButton("+");
        addButton.setFont(new Font("Helvetica", Font.PLAIN, 25));
        addButton.setForeground(Color.WHITE);
        addButton.setOpaque(false);
        addButton.setBackground(SignInController.getPurple());

        Border whiteLineBorder = new RoundedBorder(SignInController.getPurple(), 10);
        addButton.setBorder(new CompoundBorder(whiteLineBorder, new EmptyBorder(0, 0, 0, 0))); // Remove border

        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(addButton);

        // Create the separator
        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(340, 5));
        separator.setBackground(Color.WHITE);
        leftPanel.add(separator);

        // Add action listener to the button
        addButton.addActionListener(e -> {
            // Create the form panel
            JPanel formPanel = new JPanel(new BorderLayout());
            ImageIcon newGameIcon = new ImageIcon("src/com/images/NewGame.png");
            Image newGameImage = newGameIcon.getImage().getScaledInstance(panel.getWidth() - 300, 300, Image.SCALE_SMOOTH);
            ImageIcon scaledNewGameIcon = new ImageIcon(newGameImage);
            JLabel newGameLabel = new JLabel(scaledNewGameIcon, SwingConstants.CENTER);
            formPanel.add(newGameLabel, BorderLayout.NORTH);

            // Create a new panel for input fields
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(0, 2, 10, 10));
            inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

            // Create input fields
            JTextField gameNameField = new JTextField();
            JTextField gameDescriptionField = new JTextField();
            JTextField gameGenreField = new JTextField();
            JTextField gameImageField = new JTextField();
            JTextField gameCoverField = new JTextField();
            JTextField gameExeField = new JTextField();
            JTextField gameFolderField = new JTextField();

            // Add input fields to the input panel
            inputPanel.add(new JLabel("Game Name:"));
            inputPanel.add(gameNameField);
            inputPanel.add(new JLabel("Game Description:"));
            inputPanel.add(gameDescriptionField);
            inputPanel.add(new JLabel("Game Genre:"));
            inputPanel.add(gameGenreField);
            inputPanel.add(new JLabel("Game Image Path:"));
            inputPanel.add(gameImageField);
            inputPanel.add(new JLabel("Game Cover Path:"));
            inputPanel.add(gameCoverField);
            inputPanel.add(new JLabel("Game .exe Link:"));
            inputPanel.add(gameExeField);
            inputPanel.add(new JLabel("Game Folder Link:"));
            inputPanel.add(gameFolderField);

            // Add the input panel to the form panel
            formPanel.add(inputPanel, BorderLayout.CENTER);
// Create buttons
            JButton addGameButton = new JButton("Add Game") {
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

            JButton cancelButton = new JButton("Cancel") {
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

            // Set the preferred size of the buttons
            addGameButton.setPreferredSize(new Dimension(100, 30));
            cancelButton.setPreferredSize(new Dimension(100, 30));

            // Set the background color of the buttons
            addGameButton.setBackground(new Color(80, 65, 165));
            cancelButton.setBackground(new Color(80, 65, 165));

            // Set the font of the buttons
            addGameButton.setFont(new Font("Helvetica", Font.BOLD, 14));
            cancelButton.setFont(new Font("Helvetica", Font.BOLD, 14));

            // Set the foreground color of the buttons
            addGameButton.setForeground(Color.WHITE);
            cancelButton.setForeground(Color.WHITE);

            // Set the border of the buttons
            addGameButton.setBorder(new RoundedBorder(Color.WHITE, 10));
            cancelButton.setBorder(new RoundedBorder(Color.WHITE, 10));

            // Set the content area filled property of the buttons
            addGameButton.setOpaque(false);
            cancelButton.setOpaque(false);

            // Create a panel for the buttons
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            // Add the buttons to the panel
            buttonPanel.add(addGameButton);
            buttonPanel.add(cancelButton);

            // Add the button panel to the form panel
            formPanel.add(buttonPanel, BorderLayout.SOUTH);

            // Add the form panel to the main panel
            panel.add(formPanel, BorderLayout.CENTER);
            panel.revalidate();
            panel.repaint();

            // Add action listener to the cancel button
            cancelButton.addActionListener(cancelEvent -> {
                panel.remove(formPanel);
                panel.revalidate();
                panel.repaint();
            });

            // Add action listener to the add game button
            addGameButton.addActionListener(addEvent -> {
                // Add your code here for what should happen when the add game button is clicked
            });
        });

        // Create the games
        Game redDeadRedemption = new Game(
                1, // gameId
                "Red Dead Redemption", // gameName
                "A Western-themed action-adventure game", // gameDescription
                59.99, // gamePrice
                "Action-Adventure", // gameGenre
                LocalDate.of(2018, 10, 26), // releaseDate
                "Rockstar Games", // gameDeveloper
                4.6, // gameRating
                "src/com/images/RedDeadRedemption2.png" // gameImage
        );

        Game redDeadRedemption2 = new Game(
                1, // gameId
                "Red Dead Redemption 2", // gameName
                "A Western-themed action-adventure game", // gameDescription
                59.99, // gamePrice
                "Action-Adventure", // gameGenre
                LocalDate.of(2018, 10, 26), // releaseDate
                "Rockstar Games", // gameDeveloper
                4.6, // gameRating
                "src/com/images/RedDeadRedemption2.png" // gameImage
        );

        // Add the games to the library
        games.add(redDeadRedemption);
        games.add(redDeadRedemption2);

        // Add games to the left panel
        for (Game game : games) {
            JPanel gameItem = new JPanel();
            gameItem.setPreferredSize(new Dimension(337, 40));
            gameItem.setBorder(new EmptyBorder(0, 25, 0, 0));
            gameItem.setLayout(new FlowLayout(FlowLayout.LEFT));
            ImageIcon gameImageIcon = new ImageIcon(game.getGameImage());
            Image gameImage = gameImageIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon scaledGameImageIcon = new ImageIcon(gameImage);
            JLabel gameImageLabel = new JLabel(scaledGameImageIcon);
            gameItem.add(gameImageLabel);
            JLabel gameNameLabel = new JLabel(game.getGameName());
            gameNameLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
            gameNameLabel.setForeground(Color.WHITE);
            gameItem.add(gameNameLabel);
            gameItem.setBackground(SignInController.getPurple());
            leftPanel.add(gameItem);

            gameItem.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent a) {
                    try {
                        Runtime.getRuntime().exec("C:\\Users\\anxor\\AppData\\Local\\Warframe\\Downloaded\\Public\\Tools\\Launcher.exe", null, new File("C:\\Users\\anxor\\AppData\\Local\\Warframe\\Downloaded\\Public\\Tools"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                public void mouseEntered(MouseEvent e) {
                    gameItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    gameItem.setBackground(new Color(60, 45, 145));
                }

                public void mouseExited(MouseEvent e) {
                    gameItem.setBackground(SignInController.getPurple());
                }
            });
        }

        addButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                addButton.setFont(new Font("Helvetica", Font.BOLD, 25));
                addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                addButton.setBackground(SignInController.getPurple());
            }

            public void mouseExited(MouseEvent e) {
                addButton.setFont(new Font("Helvetica", Font.PLAIN, 25));
            }
        });
    }
}
