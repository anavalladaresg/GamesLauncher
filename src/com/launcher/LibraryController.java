package com.launcher;

import com.games.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.time.LocalDate;

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

        Game fortnite = new Game(
                4,
                "Fortnite",
                "A free-to-play Battle Royale game",
                0.0,
                "Battle Royale",
                LocalDate.of(2017, 7, 25),
                "Epic Games",
                3.6,
                "path/to/fortnite_image.png"
        );

        Game assassinsCreedOrigins = new Game(
                5,
                "Assassin's Creed Origins",
                "Action-adventure video game developed by Ubisoft Montreal",
                59.99,
                "Action-Adventure",
                LocalDate.of(2017, 10, 27),
                "Ubisoft",
                4.0,
                "path/to/assassins_creed_origins_image.png"
        );

        // Añadir los juegos a la biblioteca
        library.addGame(redDeadRedemption);
        library.addGame(leagueOfLegends);
        library.addGame(warframe);
        library.addGame(fortnite);
        library.addGame(assassinsCreedOrigins);

        // Para cada videojuego, crear un JMenuItem y agregarlo al JPopupMenu
        for (Game game : games) {
            JMenuItem gameItem = new JMenuItem(game.getGameName());
            ImageIcon gameIcon = new ImageIcon(game.getGameImage());
            Image scaledImage = gameIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            ImageIcon scaledGameIcon = new ImageIcon(scaledImage);
            gameItem.setIcon(scaledGameIcon);
            gameItem.setForeground(Color.WHITE);
            gameItem.setBackground(SignInController.getPurple());
            gameMenu.add(gameItem);
            gameMenu.setBackground(SignInController.getPurple());
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