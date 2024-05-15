package com.launcher;

import com.games.Game;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.add(leftPanel, BorderLayout.WEST);
        frame.add(panel);
        frame.setVisible(true);

        // Create the label
        JLabel libraryLabel = new JLabel("Library");
        libraryLabel.setFont(new Font("Helvetica", Font.BOLD, 19));
        libraryLabel.setBorder(new EmptyBorder(0, 35, 0, 170)); // Ajusta el margen derecho según sea necesario.
        libraryLabel.setForeground(Color.WHITE);
        leftPanel.add(libraryLabel);

        // Create the button for adding a game
        JButton addButton = new JButton("+");
        addButton.setFont(new Font("Helvetica", Font.PLAIN, 25));
        addButton.setForeground(Color.WHITE);
        addButton.setOpaque(false);
        addButton.setBackground(SignInController.getPurple());

        Border whiteLineBorder = new RoundedBorder(SignInController.getPurple(), 10);
        addButton.setBorder(new CompoundBorder(whiteLineBorder, new EmptyBorder(0, 0, 0, 0))); // Agrega un margen izquierdo de 10

        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(addButton);

        // Add action listener to the button
        addButton.addActionListener(e -> {
            // Create the form panel
            JPanel formPanel = new JPanel();
            formPanel.setLayout(new GridLayout(0, 2));

            // Create input fields
            JTextField gameNameField = new JTextField();
            JTextField gameDescriptionField = new JTextField();
            JTextField gameGenreField = new JTextField();
            JTextField gameImageField = new JTextField();
            JTextField gameCoverField = new JTextField();
            JTextField gameExeField = new JTextField();
            JTextField gameFolderField = new JTextField();

            // Add input fields to the form panel
            formPanel.add(new JLabel("Game Name:"));
            formPanel.add(gameNameField);
            formPanel.add(new JLabel("Game Description:"));
            formPanel.add(gameDescriptionField);
            formPanel.add(new JLabel("Game Genre:"));
            formPanel.add(gameGenreField);
            formPanel.add(new JLabel("Game Image Path:"));
            formPanel.add(gameImageField);
            formPanel.add(new JLabel("Game Cover Path:"));
            formPanel.add(gameCoverField);
            formPanel.add(new JLabel("Game .exe Link:"));
            formPanel.add(gameExeField);
            formPanel.add(new JLabel("Game Folder Link:"));
            formPanel.add(gameFolderField);

            // Remove all components from the main panel
            panel.removeAll();

            // Add the form panel to the main panel
            formPanel.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 350, 100));
            panel.add(formPanel);

            // Update the main panel
            panel.revalidate();
            panel.repaint();
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
            gameItem.setBorder(new EmptyBorder(0, 25, 0, 0));
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
                public void mouseClicked(MouseEvent e) {
                    // Code to open the .exe file of each game will go here
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

            public void mouseExited(MouseEvent e) {
                addButton.setFont(new Font("Helvetica", Font.PLAIN, 25));
            }
        });
    }
}
