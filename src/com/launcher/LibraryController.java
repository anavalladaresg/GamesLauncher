package com.launcher;

import com.games.Game;
import database.DatabaseHandler;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This class represents the controller for the library.
 * It sets up the UI for the library and handles user interactions.
 */

public class LibraryController {
    DatabaseHandler db = new DatabaseHandler();

    /**
     * Constructor for the LibraryController class.
     * It initializes the UI components and sets up the library view.
     */
    public LibraryController() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        ArrayList<Game> games = (ArrayList<Game>) db.getGames();


        // Load the GIF
        ImageIcon imageIcon = new ImageIcon("src/com/images/gif.gif");
        Image image = imageIcon.getImage().getScaledInstance(300, 250 , Image.SCALE_DEFAULT);
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
        panel.add(rightPanel, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);

        rightPanel.setBackground(new Color(224, 224, 224, 255));
        rightPanel.setPreferredSize(new Dimension(panel.getWidth() - leftPanel.getWidth(), panel.getHeight()));

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
        addButton.setBorder(new CompoundBorder(whiteLineBorder, new EmptyBorder(0, 20, 0, 0))); // Remove border

        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(addButton);

        // Create the separator
        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(340, 5));
        separator.setBackground(Color.WHITE);
        leftPanel.add(separator);

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

            // Cambiar el layout a BoxLayout
            gameItem.setLayout(new BoxLayout(gameItem, BoxLayout.X_AXIS));

            // Añadir el resto de los componentes como antes
            gameItem.add(gameImageLabel);
            gameItem.add(gameNameLabel);

            // Añadir un espacio flexible
            gameItem.add(Box.createHorizontalGlue());

            // Crear un ImageIcon
            ImageIcon deleteIcon = new ImageIcon("src/com/images/Trash.jpg");

            Image aux = deleteIcon.getImage();
            aux = aux.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            deleteIcon = new ImageIcon(aux);

            // Crear botón de eliminar
            JButton deleteButton = new JButton();
            deleteButton.setIcon(deleteIcon); // Establecer el icono en el botón
            deleteButton.setPreferredSize(new Dimension(40, 30));
            deleteButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setBackground(SignInController.getPurple());
            deleteButton.setFocusPainted(false);
            deleteButton.setBorderPainted(false);
            // Añadir listener para el botón de eliminar
            deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Eliminar el panel del juego del panel izquierdo
                    leftPanel.remove(gameItem);
                    leftPanel.revalidate();
                    leftPanel.repaint();

                    // Eliminar el juego de la base de datos
                    db.deleteGame(game.getGameName());
                }
            });
            gameItem.add(deleteButton);

            // ImageIcon para el botón de edición
            ImageIcon editIcon = new ImageIcon("src/com/images/Edit.jpg");

            Image auxEdit = editIcon.getImage();
            auxEdit = auxEdit.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            editIcon = new ImageIcon(auxEdit);

            // Crear botón de edición
            JButton editButton = new JButton();
            editButton.setIcon(editIcon); // Establecer el icono en el botón
            editButton.setPreferredSize(new Dimension(40, 30));
            editButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
            editButton.setForeground(Color.WHITE);
            editButton.setBackground(SignInController.getPurple());
            editButton.setFocusPainted(false);
            editButton.setBorderPainted(false);

            // Añadir listener para el botón de edición
            editButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                //editar el juego

                }
            });

            // Añadir el botón de edición al panel del juego
            gameItem.add(editButton);

            gameItem.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    gameItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    gameItem.setBackground(new Color(60, 45, 145));
                }

                public void mouseExited(MouseEvent e) {
                    gameItem.setBackground(SignInController.getPurple());
                }

                public void mouseClicked(MouseEvent e) {
                    rightPanel.removeAll();
                    rightPanel.revalidate();
                    rightPanel.repaint();

                    JPanel gameInfoPanel = new JPanel();
                    gameInfoPanel.setLayout(new BoxLayout(gameInfoPanel, BoxLayout.Y_AXIS));

                    ImageIcon gameCoverImageIcon = new ImageIcon(game.getGameCoverImage());
                    Image gameCoverImage = gameCoverImageIcon.getImage().getScaledInstance(panel.getWidth() - 300, 295, Image.SCALE_SMOOTH);
                    ImageIcon scaledGameCoverImageIcon = new ImageIcon(gameCoverImage);
                    JLabel gameCoverImageLabel = new JLabel(scaledGameCoverImageIcon, SwingConstants.CENTER);
                    gameCoverImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    gameInfoPanel.add(gameCoverImageLabel);


                    // Crear el botón de jugar con tamaño personalizado
                    JButton playButton = new JButton("Play");
                    playButton.setFont(new Font("Helvetica", Font.BOLD, 14));
                    playButton.setForeground(Color.WHITE);
                    playButton.setLayout(null);
                    playButton.setBounds(50,50,100,50);
                    playButton.setBackground(new Color(80, 65, 165));
                    playButton.setBorder(new RoundedBorder(Color.WHITE, 10));
                    playButton.setPreferredSize(new Dimension(200, 100));
                    playButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                    gameInfoPanel.add(Box.createVerticalStrut(10)); // Espacio entre imagen y botón
                    gameInfoPanel.add(playButton);

                    playButton.addActionListener(e1 -> {
                        try {
                            Runtime.getRuntime().exec(game.getExeLocation(), null, new File(game.getFolderLocation()));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });

                    JLabel gameDetailsLabel = new JLabel("<html>Name: " + game.getGameName() + "<br>Description: " + game.getGameDescription() + "<br>Genre: " + game.getGameGenre() + "</html>");
                    gameDetailsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                    gameInfoPanel.add(Box.createVerticalStrut(10)); // Espacio entre botón y descripción
                    gameInfoPanel.add(gameDetailsLabel);

                    rightPanel.add(gameInfoPanel);
                    rightPanel.revalidate();
                    rightPanel.repaint();
                }
            });
            leftPanel.revalidate();
            leftPanel.repaint();

            // Add action listener to the button
            addButton.addActionListener(e -> {
                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();

                // Create the form panel
                JPanel formPanel = new JPanel(new BorderLayout());
                ImageIcon newGameIcon = new ImageIcon("src/com/images/NewGame.png");
                Image newGameImage = newGameIcon.getImage().getScaledInstance(panel.getWidth() - 300, 230, Image.SCALE_SMOOTH);
                ImageIcon scaledNewGameIcon = new ImageIcon(newGameImage);
                JLabel newGameLabel = new JLabel(scaledNewGameIcon, SwingConstants.CENTER);
                formPanel.add(newGameLabel, BorderLayout.NORTH);

                Border bottomBorder = new Border() {
                    @Override
                    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                        g.setColor(SignInController.getPurple());
                        g.drawLine(x, y + height - 1, x + width, y + height - 1);
                    }

                    @Override
                    public Insets getBorderInsets(Component c) {
                        return new Insets(0, 0, 1, 0);
                    }

                    @Override
                    public boolean isBorderOpaque() {
                        return true;
                    }
                };

                // Create a new panel for input fields
                JPanel inputPanel = new JPanel();
                inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
                inputPanel.setBorder(new EmptyBorder(20, 50, 20, 50));

                // Create the game name field
                JPanel gameNamePanel = new JPanel(new BorderLayout());
                gameNamePanel.setBorder(new EmptyBorder(0, 0, 20, 0));
                JTextField gameNameField = new JTextField();
                gameNameField.setBorder(new CompoundBorder(bottomBorder, new EmptyBorder(0, 0, 100, 0)));
                gameNameField.setBackground(inputPanel.getBackground());
                gameNameField.setPreferredSize(new Dimension(500, 0));
                gameNameField.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        gameNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SignInController.getPurple()));
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        gameNameField.setBorder(bottomBorder);
                    }
                });
                gameNamePanel.add(new JLabel("Game Name:"), BorderLayout.NORTH);
                gameNamePanel.add(gameNameField, BorderLayout.CENTER);
                inputPanel.add(gameNamePanel);

                // Game Description Panel
                JPanel gameDescriptionPanel = new JPanel(new BorderLayout());
                gameDescriptionPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
                JTextField gameDescriptionField = new JTextField();
                gameDescriptionField.setBorder(new CompoundBorder(bottomBorder, new EmptyBorder(0, 0, 100, 0)));
                gameDescriptionField.setBackground(inputPanel.getBackground());
                gameDescriptionField.setPreferredSize(new Dimension(500, 0));
                gameDescriptionField.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        gameDescriptionField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SignInController.getPurple()));
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        gameDescriptionField.setBorder(bottomBorder);
                    }
                });
                gameDescriptionPanel.add(new JLabel("Game Description:"), BorderLayout.NORTH);
                gameDescriptionPanel.add(gameDescriptionField, BorderLayout.CENTER);
                inputPanel.add(gameDescriptionPanel);

                // Game Genre Panel
                JPanel gameGenrePanel = new JPanel(new BorderLayout());
                gameGenrePanel.setBorder(new EmptyBorder(0, 0, 20, 0));
                JTextField gameGenreField = new JTextField();
                gameGenreField.setBorder(new CompoundBorder(bottomBorder, new EmptyBorder(0, 0, 100, 0)));
                gameGenreField.setBackground(inputPanel.getBackground());
                gameGenreField.setPreferredSize(new Dimension(500, 0));
                gameGenreField.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        gameGenreField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SignInController.getPurple()));
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        gameGenreField.setBorder(bottomBorder);
                    }
                });
                gameGenrePanel.add(new JLabel("Game Genre:"), BorderLayout.NORTH);
                gameGenrePanel.add(gameGenreField, BorderLayout.CENTER);
                inputPanel.add(gameGenrePanel);

                // Game Image Path Panel
                JPanel gameImagePathPanel = new JPanel(new BorderLayout());
                gameImagePathPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
                JTextField gameImageField = new JTextField();
                gameImageField.setBorder(new CompoundBorder(bottomBorder, new EmptyBorder(0, 0, 100, 0)));
                gameImageField.setBackground(inputPanel.getBackground());
                gameImageField.setPreferredSize(new Dimension(500, 0));
                gameImageField.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        gameImageField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SignInController.getPurple()));
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        gameImageField.setBorder(bottomBorder);
                    }
                });
                gameImagePathPanel.add(new JLabel("Game Image Path:"), BorderLayout.NORTH);
                gameImagePathPanel.add(gameImageField, BorderLayout.CENTER);
                inputPanel.add(gameImagePathPanel);

                // Game Cover Path Panel
                JPanel gameCoverPathPanel = new JPanel(new BorderLayout());
                gameCoverPathPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
                JTextField gameCoverField = new JTextField();
                gameCoverField.setBorder(new CompoundBorder(bottomBorder, new EmptyBorder(0, 0, 100, 0)));
                gameCoverField.setBackground(inputPanel.getBackground());
                gameCoverField.setPreferredSize(new Dimension(500, 0));
                gameCoverField.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        gameCoverField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SignInController.getPurple()));
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        gameCoverField.setBorder(bottomBorder);
                    }
                });
                gameCoverPathPanel.add(new JLabel("Game Cover Path:"), BorderLayout.NORTH);
                gameCoverPathPanel.add(gameCoverField, BorderLayout.CENTER);
                inputPanel.add(gameCoverPathPanel);

                // Game .exe Link Panel
                JPanel gameExeLinkPanel = new JPanel(new BorderLayout());
                gameExeLinkPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
                JTextField gameExeField = new JTextField();
                gameExeField.setBorder(new CompoundBorder(bottomBorder, new EmptyBorder(0, 0, 100, 0)));
                gameExeField.setBackground(inputPanel.getBackground());
                gameExeField.setPreferredSize(new Dimension(500, 0));
                gameExeField.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        gameExeField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SignInController.getPurple()));
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        gameExeField.setBorder(bottomBorder);
                    }
                });
                gameExeLinkPanel.add(new JLabel("Game .exe Link:"), BorderLayout.NORTH);
                gameExeLinkPanel.add(gameExeField, BorderLayout.CENTER);
                inputPanel.add(gameExeLinkPanel);

                // Game Folder Link Panel
                JPanel gameFolderLinkPanel = new JPanel(new BorderLayout());
                gameFolderLinkPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
                JTextField gameFolderField = new JTextField();
                gameFolderField.setBorder(new CompoundBorder(bottomBorder, new EmptyBorder(0, 0, 100, 0)));
                gameFolderField.setBackground(inputPanel.getBackground());
                gameFolderField.setPreferredSize(new Dimension(500, 0));
                gameFolderField.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        gameFolderField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SignInController.getPurple()));
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        gameFolderField.setBorder(bottomBorder);
                    }
                });
                gameFolderLinkPanel.add(new JLabel("Game Folder Link:"), BorderLayout.NORTH);
                gameFolderLinkPanel.add(gameFolderField, BorderLayout.CENTER);
                inputPanel.add(gameFolderLinkPanel);

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
                rightPanel.add(formPanel);
                rightPanel.revalidate();
                rightPanel.repaint();

                // Add action listener to the cancel button
                cancelButton.addActionListener(cancelEvent -> {
                    rightPanel.remove(formPanel);
                    rightPanel.revalidate();
                    rightPanel.repaint();
                });

                cancelButton.addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e) {
                        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        cancelButton.setBackground(new Color(60, 45, 145));
                    }

                    public void mouseExited(MouseEvent e) {
                        cancelButton.setBackground(new Color(80, 65, 165));
                    }
                });

                addGameButton.addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e) {
                        addGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        addGameButton.setBackground(new Color(60, 45, 145));
                    }

                    public void mouseExited(MouseEvent e) {
                        addGameButton.setBackground(new Color(80, 65, 165));
                    }
                });

                // Add action listener to the add game button
                addGameButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Game newGame = new Game();
                        newGame.setGameName(gameNameField.getText());
                        newGame.setGameDescription(gameDescriptionField.getText());
                        newGame.setGameGenre(gameGenreField.getText());
                        try {
                            newGame.setGameImage(getImagePath(gameImageField.getText()));
                            newGame.setGameCoverImage(getImagePath(gameCoverField.getText()));
                        } catch (IOException i) {
                            i.printStackTrace();
                            return; // If an exception occurs, stop executing the rest of the code in this block
                        }
                        newGame.setExeLocation(gameExeField.getText());
                        newGame.setFolderLocation(gameFolderField.getText());

                        db.addGame(newGame);

                        // Create a new game item panel
                        JPanel gameItem2 = new JPanel();
                        gameItem2.setPreferredSize(new Dimension(337, 40));
                        gameItem2.setBorder(new EmptyBorder(0, 25, 0, 0));
                        gameItem2.setLayout(new FlowLayout(FlowLayout.LEFT));
                        ImageIcon gameImageIcon2 = new ImageIcon(newGame.getGameImage());
                        Image gameImage2 = gameImageIcon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                        ImageIcon scaledGameImageIcon2 = new ImageIcon(gameImage2);
                        JLabel gameImageLabel2 = new JLabel(scaledGameImageIcon2);
                        gameItem2.add(gameImageLabel2);
                        JLabel gameNameLabel2 = new JLabel(newGame.getGameName());
                        gameNameLabel2.setFont(new Font("Helvetica", Font.PLAIN, 16));
                        gameNameLabel2.setForeground(Color.WHITE);
                        gameItem2.add(gameNameLabel2);
                        gameItem2.setBackground(SignInController.getPurple());

                        // Add the new game item panel to the left panel
                        leftPanel.add(gameItem);

                        gameItem.addMouseListener(new MouseAdapter() {
                            public void mouseEntered(MouseEvent e) {
                                gameItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
                                gameItem.setBackground(new Color(60, 45, 145));
                            }

                            public void mouseExited(MouseEvent e) {
                                gameItem.setBackground(SignInController.getPurple());
                            }

                            public void mouseClicked(MouseEvent e) {

                                rightPanel.removeAll();
                                rightPanel.revalidate();
                                rightPanel.repaint();

                                // Create a new panel for game information
                                JPanel gameInfoPanel = new JPanel(new BorderLayout());
                                gameInfoPanel.setPreferredSize(new Dimension(panel.getWidth() - leftPanel.getWidth(), panel.getHeight()));
                                gameInfoPanel.setBackground(new Color(224, 224, 224, 255));

                                // Add game cover image at the top
                                ImageIcon gameCoverImageIcon = new ImageIcon(newGame.getGameCoverImage());
                                Image gameCoverImage = gameCoverImageIcon.getImage().getScaledInstance(panel.getWidth() - leftPanel.getWidth(), 350, Image.SCALE_SMOOTH);
                                ImageIcon scaledGameCoverImageIcon = new ImageIcon(gameCoverImage);
                                JLabel gameCoverImageLabel = new JLabel(scaledGameCoverImageIcon, SwingConstants.CENTER);
                                gameInfoPanel.add(gameCoverImageLabel, BorderLayout.NORTH);

                                // Crear el botón de jugar
                                JButton playButton = new JButton("Play");
                                playButton.setFont(new Font("Helvetica", Font.BOLD, 14));
                                playButton.setForeground(Color.WHITE);
                                playButton.setLayout(null);
                                playButton.setBounds(50, 50, 100, 50);
                                playButton.setBackground(new Color(80, 65, 165));
                                playButton.setBorder(new RoundedBorder(Color.WHITE, 10));
                                playButton.setOpaque(false);

                                playButton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        try {
                                            Runtime.getRuntime().exec(newGame.getExeLocation(), null, new File(newGame.getFolderLocation()));
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                });

                                gameInfoPanel.add(playButton, BorderLayout.CENTER);

                                JLabel gameDetailsLabel = new JLabel("<html>Name: " + newGame.getGameName() + "<br>Description: " + newGame.getGameDescription() + "<br>Genre: " + newGame.getGameGenre() + "</html>");
                                gameInfoPanel.add(gameDetailsLabel, BorderLayout.SOUTH);

                                // Add the game information panel to the main panel
                                rightPanel.add(gameInfoPanel);

                                // Refresh the main panel
                                rightPanel.revalidate();
                                rightPanel.repaint();
                            }
                        });

                        // Refresh the left panel
                        leftPanel.revalidate();
                        leftPanel.repaint();

                        // Remove the formPanel from the main panel
                        rightPanel.remove(formPanel);

                        // Refresh the main panel
                        panel.revalidate();
                        panel.repaint();
                    }
                });
            });

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

    public static String getImagePath(String imagePath) throws IOException {
        Path path = Paths.get(imagePath);
        if (Files.exists(path)) {
            return path.toString();
        } else {
            throw new IOException("File not found: " + imagePath);
        }
    }

    /**
     * public void mouseClicked(MouseEvent a) {
     *                     try {
     *                         Runtime.getRuntime().exec("C:\\Users\\anxor\\AppData\\Local\\Warframe\\Downloaded\\Public\\Tools\\Launcher.exe", null, new File("C:\\Users\\anxor\\AppData\\Local\\Warframe\\Downloaded\\Public\\Tools"));
     *                     } catch (IOException e) {
     *                         e.printStackTrace();
     *                     }
     *                 }
     */
}