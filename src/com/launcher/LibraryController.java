package com.launcher;

import com.games.Game;

import javax.swing.*;
import java.awt.*;
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
        leftPanel.setPreferredSize(null);

        // Load the image
        ImageIcon imageIcon = new ImageIcon("src/com/images/Xynx.png");
        Image image = imageIcon.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        leftPanel.add(imageLabel);

        // Create some space
        leftPanel.add(Box.createRigidArea(new Dimension(50, 20))); // Adjust the second parameter to move the label up or down

        // Create the label
        JLabel libraryLabel = new JLabel("Library");
        libraryLabel.setFont(new Font("Helvetica", Font.BOLD, 19));
        libraryLabel.setForeground(Color.WHITE);
        leftPanel.add(libraryLabel);

        frame.setTitle("Game Library");
        panel.setBackground(new Color(224, 224, 224, 255));
        leftPanel.setBackground(SignInController.getPurple());
        leftPanel.setPreferredSize(new Dimension(350, frame.getHeight()));
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.add(leftPanel, BorderLayout.WEST);
        frame.add(panel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);


        // Crear el JPopupMenu
        JPopupMenu gameMenu = new JPopupMenu();

        // Obtener la lista de videojuegos
        Library library = new Library();
        ArrayList<Game> games = library.getGames();

        // Crear los juegos
        Game redDeadRedemption = new Game(
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

        Game leagueOfLegends = new Game(
                2,
                "League of Legends",
                "A fast-paced, competitive online game",
                0.0,
                "MOBA",
                LocalDate.of(2009, 10, 27),
                "Riot Games",
                4.3,
                "path/to/league_of_legends_image.png"
        );

        Game warframe = new Game(
                3,
                "Warframe",
                "A cooperative free-to-play third person online action game",
                0.0,
                "Action",
                LocalDate.of(2013, 3, 25),
                "Digital Extremes",
                4.5,
                "path/to/warframe_image.png"
        );

        // Añadir los juegos a la biblioteca
        library.addGame(redDeadRedemption);
        library.addGame(leagueOfLegends);
        library.addGame(warframe);

        for (Game game : games) {
            JPanel gameItem = new JPanel(new FlowLayout(FlowLayout.LEFT));
            gameItem.setPreferredSize(new Dimension(gameItem.getWidth(), 0));
            gameItem.add(Box.createRigidArea(new Dimension(10, 0)));
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

        // Agregar un MouseListener a libraryLabel que muestre el JPopupMenu cuando se haga clic en la etiqueta
        libraryLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                gameMenu.show(e.getComponent(), e.getX(), e.getY() + libraryLabel.getHeight());
            }

            public void mouseEntered(MouseEvent e) {
                libraryLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }
}