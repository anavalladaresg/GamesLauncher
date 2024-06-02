package com.launcher.functional.mainpanel;

import com.games.Game;
import com.launcher.functional.initpanel.SignInController;
import com.launcher.styles.RoundedBorder;
import database.GameDatabaseHandler;

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
 * Esta clase controla la interfaz de la biblioteca de juegos.
 */
public class LibraryController {
    GameDatabaseHandler db = new GameDatabaseHandler();
    String userName;
    JPanel gameListPanel;

    /**
     * Constructor de la clase LibraryController.
     * @param userName El nombre de usuario del usuario.
     */
    public LibraryController(String userName) {
        this.userName = userName;
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        gameListPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        ArrayList<Game> games = (ArrayList<Game>) db.getGames(this.userName);


        // Meter el gif en el panel izquierdo
        ImageIcon imageIcon = new ImageIcon("src/com/images/gif.gif");
        Image image = imageIcon.getImage().getScaledInstance(300, 250, Image.SCALE_DEFAULT);
        ImageIcon scaledImageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        imageLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        leftPanel.add(imageLabel);

        // Configurar el frame
        frame.setTitle("Game Library");
        panel.setBackground(new Color(224, 224, 224, 255));
        leftPanel.setBackground(SignInController.getPurple());
        leftPanel.setPreferredSize(new Dimension(350, 100));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.CENTER);
        frame.setSize(2560, 1600);
        frame.add(panel);
        frame.setVisible(true);

        rightPanel.setBackground(new Color(224, 224, 224, 255));
        rightPanel.setPreferredSize(new Dimension(panel.getWidth() - leftPanel.getWidth(), panel.getHeight()));

        // Crear un JLabel para el título de la biblioteca
        JLabel libraryLabel = new JLabel("Library");
        libraryLabel.setFont(new Font("Helvetica", Font.BOLD, 19));
        libraryLabel.setBorder(new EmptyBorder(0, 35, 0, 170)); // Adjust right margin as needed.
        libraryLabel.setForeground(Color.WHITE);
        leftPanel.add(libraryLabel);

        // Crear un botón para añadir juegos
        JButton addButton = new JButton("+");
        addButton.setFont(new Font("Helvetica", Font.PLAIN, 25));
        addButton.setForeground(Color.WHITE);
        addButton.setOpaque(false);
        addButton.setBackground(SignInController.getPurple());
        Border whiteLineBorder = new RoundedBorder(SignInController.getPurple(), 10);
        addButton.setBorder(new CompoundBorder(whiteLineBorder, new EmptyBorder(0, 20, 0, 0))); // Remove border
        configureActionListenerToAddButton(userName, addButton, rightPanel, panel, leftPanel);
        configureMouseListenerToAddButton(addButton);

        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(addButton);

        // Crear un separador
        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(340, 5));
        separator.setBackground(Color.WHITE);
        leftPanel.add(separator);

        // Añadir los juegos a la lista
        gameListPanel.setLayout(new BoxLayout(gameListPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(SignInController.getPurple());
        leftPanel.setPreferredSize(new Dimension(350, 100));
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

            // Cambiar el layout a BoxLayout
            gameItem.setLayout(new BoxLayout(gameItem, BoxLayout.X_AXIS));

            // Añadir el resto de los componentes como antes
            gameItem.add(gameImageLabel);
            gameItem.add(gameNameLabel);

            // Añadir un espacio flexible
            gameItem.add(Box.createHorizontalGlue());

            // Crear un ImageIcon
            ImageIcon deleteIcon = new ImageIcon("src/com/images/trash.png");

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
                    rightPanel.removeAll();
                    leftPanel.revalidate();
                    leftPanel.repaint();

                    // Eliminar el juego de la base de datos
                    db.deleteGame(game.getGameName(), userName);
                }
            });
            gameItem.add(deleteButton);

            // ImageIcon para el botón de edición
            ImageIcon editIcon = new ImageIcon("src/com/images/edit.png");

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
                private static JButton createCancelButton() {
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
                    return cancelButton;
                }

                public void actionPerformed(ActionEvent e) {
                    // Mostrar el formulario de edición (puedes usar un JOptionPane con varios campos de entrada)
                    JTextField gameNameField = new JTextField(game.getGameName());
                    JTextField gameDescriptionField = new JTextField(game.getGameDescription());
                    JTextField gameGenreField = new JTextField(game.getGameGenre());
                    JTextField gameImageField = new JTextField(game.getGameImage());
                    JTextField gameCoverField = new JTextField(game.getGameCoverImage());
                    JTextField gameExeField = new JTextField(game.getExeLocation());
                    JTextField gameFolderField = new JTextField(game.getFolderLocation());

                    Object[] message = {
                            "Game Name:", gameNameField,
                            "Game Description:", gameDescriptionField,
                            "Game Genre:", gameGenreField,
                            "Game Image Path:", gameImageField,
                            "Game Cover Path:", gameCoverField,
                            "Game .exe Link:", gameExeField,
                            "Game Folder Link:", gameFolderField
                    };

                    int option = JOptionPane.showConfirmDialog(null, message, "Edit Game", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        // Verificar si todos los campos están llenos
                        if (gameNameField.getText().isEmpty() || gameDescriptionField.getText().isEmpty() || gameGenreField.getText().isEmpty() || gameImageField.getText().isEmpty() || gameCoverField.getText().isEmpty() || gameExeField.getText().isEmpty() || gameFolderField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            // Recoger los datos ingresados por el usuario y actualizar el objeto Game
                            game.setGameName(gameNameField.getText());
                            game.setGameDescription(gameDescriptionField.getText());
                            game.setGameGenre(gameGenreField.getText());
                            game.setGameImage(gameImageField.getText());
                            game.setGameCoverImage(gameCoverField.getText());
                            game.setExeLocation(gameExeField.getText());
                            game.setFolderLocation(gameFolderField.getText());

                            // Actualizar la base de datos con los nuevos datos del juego
                            db.updateGame(game);

                            leftPanel.revalidate();
                            leftPanel.repaint();
                        }
                    }
                }
            });

            // Añadir el botón de edición al panel del juego
            gameItem.add(editButton);
            gameListPanel.add(gameItem);
            leftPanel.add(gameListPanel);


            editButton.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    editButton.setBackground(new Color(60, 45, 145));
                    deleteButton.setBackground(new Color(60, 45, 145));
                    gameItem.setBackground(new Color(60, 45, 145));
                    Timer timer = new Timer(15, new ActionListener() {
                        int red = editButton.getBackground().getRed();
                        int green = editButton.getBackground().getGreen();
                        int blue = editButton.getBackground().getBlue();

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (red < 60) red++;
                            if (green > 45) green--;
                            if (blue > 145) blue--;
                            editButton.setBackground(new Color(red, green, blue));
                            deleteButton.setBackground(new Color(red, green, blue));
                            gameItem.setBackground(new Color(red, green, blue));
                            if (red == 60 && green == 45 && blue == 145) {
                                ((Timer) e.getSource()).stop();
                            }
                        }
                    });
                    timer.start();
                    ImageIcon editIcon2 = new ImageIcon("src/com/images/edit.png");
                    Image auxEdit2 = editIcon2.getImage();
                    auxEdit2 = auxEdit2.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                    editIcon2 = new ImageIcon(auxEdit2);
                    editButton.setIcon(editIcon2);
                }


                public void mouseExited(MouseEvent e) {
                    editButton.setBackground(SignInController.getPurple());
                    deleteButton.setBackground(SignInController.getPurple());
                    gameItem.setBackground(SignInController.getPurple());
                    ImageIcon editIcon2 = new ImageIcon("src/com/images/edit.png");
                    Image auxEdit2 = editIcon2.getImage();
                    auxEdit2 = auxEdit2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                    editIcon2 = new ImageIcon(auxEdit2);
                    editButton.setIcon(editIcon2);
                }
            });
            deleteButton.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    editButton.setBackground(new Color(60, 45, 145));
                    deleteButton.setBackground(new Color(60, 45, 145));
                    gameItem.setBackground(new Color(60, 45, 145));
                    Timer timer = new Timer(15, new ActionListener() {
                        int red = deleteButton.getBackground().getRed();
                        int green = deleteButton.getBackground().getGreen();
                        int blue = deleteButton.getBackground().getBlue();

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (red < 60) red++;
                            if (green > 45) green--;
                            if (blue > 145) blue--;
                            deleteButton.setBackground(new Color(red, green, blue));
                            deleteButton.setBackground(new Color(red, green, blue));
                            gameItem.setBackground(new Color(red, green, blue));
                            if (red == 60 && green == 45 && blue == 145) {
                                ((Timer) e.getSource()).stop();
                            }
                        }
                    });
                    timer.start();
                    ImageIcon deleteIcon2 = new ImageIcon("src/com/images/trash.png");
                    Image aux2 = deleteIcon2.getImage();
                    aux2 = aux2.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                    deleteIcon2 = new ImageIcon(aux2);
                    deleteButton.setIcon(deleteIcon2);
                }


                public void mouseExited(MouseEvent e) {
                    editButton.setBackground(SignInController.getPurple());
                    deleteButton.setBackground(SignInController.getPurple());
                    gameItem.setBackground(SignInController.getPurple());
                    ImageIcon deleteIcon2 = new ImageIcon("src/com/images/trash.png");
                    Image aux2 = deleteIcon2.getImage();
                    aux2 = aux2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                    deleteIcon2 = new ImageIcon(aux2);
                    deleteButton.setIcon(deleteIcon2);
                }
            });

            gameItem.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    gameItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    gameItem.setBackground(new Color(60, 45, 145));
                    editButton.setBackground(new Color(60, 45, 145));
                    deleteButton.setBackground(new Color(60, 45, 145));
                }

                public void mouseExited(MouseEvent e) {
                    gameItem.setBackground(SignInController.getPurple());
                    editButton.setBackground(SignInController.getPurple());
                    deleteButton.setBackground(SignInController.getPurple());
                }

                public void mouseClicked(MouseEvent e) {
                    rightPanel.removeAll();
                    rightPanel.revalidate();
                    rightPanel.repaint();

                    // Panel principal de la información del juego
                    JPanel gameInfoPanel = new JPanel(new BorderLayout()) {
                        private ImageIcon gifBackground;

                        {
                            try {
                                // Cargar la imagen GIF como ImageIcon
                                gifBackground = new ImageIcon("src/com/images/fondo.gif");
                                gifBackground.setImage(gifBackground.getImage().getScaledInstance(panel.getWidth() - leftPanel.getWidth(), panel.getHeight(), Image.SCALE_DEFAULT));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        protected void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            if (gifBackground != null) {
                                // Dibujar el GIF animado como fondo
                                gifBackground.paintIcon(this, g, 0, 0);
                            }
                        }
                    };
                    gameInfoPanel.setPreferredSize(new Dimension(panel.getWidth() - leftPanel.getWidth(), panel.getHeight()));
                    gameInfoPanel.setBackground(new Color(224, 224, 224, 255));

                    // Añadir la imagen de portada del juego en la parte superior
                    ImageIcon gameCoverImageIcon = new ImageIcon(game.getGameCoverImage());
                    Image gameCoverImage = gameCoverImageIcon.getImage().getScaledInstance(panel.getWidth() - leftPanel.getWidth(), 350, Image.SCALE_SMOOTH);
                    ImageIcon scaledGameCoverImageIcon = new ImageIcon(gameCoverImage);
                    JLabel gameCoverImageLabel = new JLabel(scaledGameCoverImageIcon, SwingConstants.CENTER);
                    gameInfoPanel.add(gameCoverImageLabel, BorderLayout.NORTH);

                    // Crear el botón de jugar
                    JButton playButton = new JButton("Play") {
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
                    playButton.setBounds(115, 330, 150, 35);
                    playButton.setBackground(SignInController.getPurple());
                    playButton.setFont(new Font("Helvetica", Font.BOLD, 20));
                    playButton.setForeground(Color.WHITE);
                    playButton.setPreferredSize(new Dimension(150, 75));
                    playButton.setBorder(new RoundedBorder(SignInController.getPurple(), 10));
                    playButton.setBorder(new RoundedBorder(Color.WHITE, 10));
                    playButton.setContentAreaFilled(true);
                    playButton.setOpaque(false);
                    gameInfoPanel.add(playButton, BorderLayout.CENTER);

                    playButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                Runtime.getRuntime().exec(game.getExeLocation(), null, new File(game.getFolderLocation()));
                            } catch (IOException ex) {
                                // Mostrar un mensaje de error
                                JOptionPane.showMessageDialog(null, "Error al ejecutar el juego. Por favor, verifica la ubicación del archivo .exe y la carpeta del juego.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });

                    // Envolver el botón en un panel para centrarlo y mantener su tamaño
                    JPanel playButtonPanel = new JPanel(new GridBagLayout());
                    playButtonPanel.setOpaque(false); // Hacer que el panel sea transparente
                    playButtonPanel.add(playButton);

                    gameInfoPanel.add(playButtonPanel, BorderLayout.CENTER);

                    JLabel gameDetailsLabel = new JLabel("<html><div style='text-align: center;'> <b>Name: </b> <br>" + game.getGameName() + "<br> <br><b> Description: </b> <br>" + game.getGameDescription() + "<br> <br> <b> Genre: </b> <br> " + game.getGameGenre() + "</div></html>", SwingConstants.CENTER);
                    gameDetailsLabel.setForeground(Color.WHITE);
                    gameDetailsLabel.setBorder(new EmptyBorder(0, 35, 30, 35));
                    gameDetailsLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
                    gameInfoPanel.add(gameDetailsLabel, BorderLayout.SOUTH);

                    // Añadir el panel de información del juego al panel principal
                    rightPanel.add(gameInfoPanel);
                    rightPanel.revalidate();
                    rightPanel.repaint();
                }
            });
            leftPanel.revalidate();
            leftPanel.repaint();

            configureActionListenerToAddButton(userName, addButton, rightPanel, panel, leftPanel);
            configureMouseListenerToAddButton(addButton);
        }
    }

    private static JButton createConfirmButton() {
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setPreferredSize(new Dimension(100, 30));
        confirmButton.setBackground(new Color(80, 65, 165));
        confirmButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setBorder(new RoundedBorder(Color.WHITE, 10));
        confirmButton.setOpaque(false);
        confirmButton.setContentAreaFilled(true);
        return confirmButton;
    }

    private static void configureMouseListenerToAddButton(JButton addButton) {
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

    public static String getImagePath(String imagePath) {
        Path path = Paths.get(imagePath);
        if (!Files.exists(path)) {
            JOptionPane.showMessageDialog(null, "Image not found: " + imagePath, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return path.toString();
    }

    private void configureActionListenerToAddButton(String userName, JButton addButton, JPanel rightPanel, JPanel panel, JPanel leftPanel) {
        addButton.addActionListener(e -> {
            rightPanel.removeAll();
            rightPanel.revalidate();
            rightPanel.repaint();

            // Crear un panel para el formulario
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

            // Crear un panel para los campos de entrada
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
            inputPanel.setBorder(new EmptyBorder(20, 50, 20, 50));

            // Crear los campos de entrada
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

            // Descripción del juego
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

            // Genero del juego
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

            // Imagen del juego
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

            // Portada del juego
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

            // Exe del juego
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

            // Carpeta del juego
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

            // Añadir el panel de campos de entrada al panel del formulario
            formPanel.add(inputPanel, BorderLayout.CENTER);

            // Crear botones de confirmación y cancelación
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

            addGameButton.setPreferredSize(new Dimension(100, 30));
            cancelButton.setPreferredSize(new Dimension(100, 30));

            addGameButton.setBackground(new Color(80, 65, 165));
            cancelButton.setBackground(new Color(80, 65, 165));

            addGameButton.setFont(new Font("Helvetica", Font.BOLD, 14));
            cancelButton.setFont(new Font("Helvetica", Font.BOLD, 14));

            addGameButton.setForeground(Color.WHITE);
            cancelButton.setForeground(Color.WHITE);

            addGameButton.setBorder(new RoundedBorder(Color.WHITE, 10));
            cancelButton.setBorder(new RoundedBorder(Color.WHITE, 10));

            addGameButton.setOpaque(false);
            cancelButton.setOpaque(false);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            buttonPanel.add(addGameButton);
            buttonPanel.add(cancelButton);

            formPanel.add(buttonPanel, BorderLayout.SOUTH);

            rightPanel.add(formPanel);
            rightPanel.revalidate();
            rightPanel.repaint();

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

            // Añaadir un listener para el botón de añadir juego
            addGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (gameNameField.getText().isEmpty() || gameDescriptionField.getText().isEmpty() || gameGenreField.getText().isEmpty() || gameImageField.getText().isEmpty() || gameCoverField.getText().isEmpty() || gameExeField.getText().isEmpty() || gameFolderField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Game newGame = new Game();
                    newGame.setGameName(gameNameField.getText());
                    newGame.setGameDescription(gameDescriptionField.getText());
                    newGame.setGameGenre(gameGenreField.getText());
                    newGame.setGameImage(getImagePath(gameImageField.getText()));
                    newGame.setGameCoverImage(getImagePath(gameCoverField.getText()));
                    newGame.setExeLocation(gameExeField.getText());
                    newGame.setFolderLocation(gameFolderField.getText());

                    db.addGame(newGame, userName);

                    // Crear un nuevo panel para el juego añadido
                    JPanel gameItem = new JPanel();
                    gameItem.setPreferredSize(new Dimension(337, 40));
                    gameItem.setBorder(new EmptyBorder(0, 25, 0, 0));
                    gameItem.setLayout(new FlowLayout(FlowLayout.LEFT));
                    ImageIcon gameImageIcon2 = new ImageIcon(newGame.getGameImage());
                    Image gameImage2 = gameImageIcon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                    ImageIcon scaledGameImageIcon2 = new ImageIcon(gameImage2);
                    JLabel gameImageLabel2 = new JLabel(scaledGameImageIcon2);
                    gameItem.add(gameImageLabel2);
                    JLabel gameNameLabel2 = new JLabel(newGame.getGameName());
                    gameNameLabel2.setFont(new Font("Helvetica", Font.PLAIN, 16));
                    gameNameLabel2.setForeground(Color.WHITE);
                    gameItem.add(gameNameLabel2);
                    gameItem.setBackground(SignInController.getPurple());

                    // Añadir el resto de los componentes como antes
                    gameListPanel.add(gameItem);
                    leftPanel.add(gameListPanel);

                    gameItem.add(Box.createHorizontalGlue());

                    // Crear un ImageIcon
                    ImageIcon deleteIcon = new ImageIcon("src/com/images/trash.png");

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
                            db.deleteGame(newGame.getGameName(), userName);
                        }
                    });
                    gameItem.add(deleteButton);

                    // ImageIcon para el botón de edición
                    ImageIcon editIcon = new ImageIcon("src/com/images/edit.png");

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
                        private static JButton createCancelButton() {
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
                            return cancelButton;
                        }

                        public void actionPerformed(ActionEvent e) {
                            // 1. Mostrar el formulario de edición (puedes usar un JOptionPane con varios campos de entrada)
                            JTextField gameNameField = new JTextField(newGame.getGameName());
                            JTextField gameDescriptionField = new JTextField(newGame.getGameDescription());
                            JTextField gameGenreField = new JTextField(newGame.getGameGenre());
                            JTextField gameImageField = new JTextField(newGame.getGameImage());
                            JTextField gameCoverField = new JTextField(newGame.getGameCoverImage());
                            JTextField gameExeField = new JTextField(newGame.getExeLocation());
                            JTextField gameFolderField = new JTextField(newGame.getFolderLocation());

                            Object[] message = {
                                    "Game Name:", gameNameField,
                                    "Game Description:", gameDescriptionField,
                                    "Game Genre:", gameGenreField,
                                    "Game Image Path:", gameImageField,
                                    "Game Cover Path:", gameCoverField,
                                    "Game .exe Link:", gameExeField,
                                    "Game Folder Link:", gameFolderField
                            };

                            int option = JOptionPane.showConfirmDialog(null, message, "Edit Game", JOptionPane.OK_CANCEL_OPTION);
                            if (option == JOptionPane.OK_OPTION) {
                                // Verificar si todos los campos están llenos
                                if (gameNameField.getText().isEmpty() || gameDescriptionField.getText().isEmpty() || gameGenreField.getText().isEmpty() || gameImageField.getText().isEmpty() || gameCoverField.getText().isEmpty() || gameExeField.getText().isEmpty() || gameFolderField.getText().isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    // 2. Recoger los datos ingresados por el usuario y actualizar el objeto Game
                                    newGame.setGameName(gameNameField.getText());
                                    newGame.setGameDescription(gameDescriptionField.getText());
                                    newGame.setGameGenre(gameGenreField.getText());
                                    newGame.setGameImage(gameImageField.getText());
                                    newGame.setGameCoverImage(gameCoverField.getText());
                                    newGame.setExeLocation(gameExeField.getText());
                                    newGame.setFolderLocation(gameFolderField.getText());

                                    // 3. Actualizar la base de datos con los nuevos datos del juego
                                    db.updateGame(newGame);
                                    leftPanel.removeAll();
                                    leftPanel.revalidate();
                                    leftPanel.repaint();
                                }
                            }
                        }
                    });

                    // Añadir el botón de edición al panel del juego
                    gameItem.add(editButton);
                    leftPanel.add(gameItem);

                    editButton.addMouseListener(new MouseAdapter() {
                        public void mouseEntered(MouseEvent e) {
                            editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                            editButton.setBackground(new Color(60, 45, 145));
                            deleteButton.setBackground(new Color(60, 45, 145));
                            gameItem.setBackground(new Color(60, 45, 145));
                            Timer timer = new Timer(15, new ActionListener() {
                                int red = editButton.getBackground().getRed();
                                int green = editButton.getBackground().getGreen();
                                int blue = editButton.getBackground().getBlue();

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (red < 60) red++;
                                    if (green > 45) green--;
                                    if (blue > 145) blue--;
                                    editButton.setBackground(new Color(red, green, blue));
                                    deleteButton.setBackground(new Color(red, green, blue));
                                    gameItem.setBackground(new Color(red, green, blue));
                                    if (red == 60 && green == 45 && blue == 145) {
                                        ((Timer) e.getSource()).stop();
                                    }
                                }
                            });
                            timer.start();
                            ImageIcon editIcon2 = new ImageIcon("src/com/images/edit.png");
                            Image auxEdit2 = editIcon2.getImage();
                            auxEdit2 = auxEdit2.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                            editIcon2 = new ImageIcon(auxEdit2);
                            editButton.setIcon(editIcon2); // Set the icon on the button
                        }


                        public void mouseExited(MouseEvent e) {
                            editButton.setBackground(SignInController.getPurple());
                            deleteButton.setBackground(SignInController.getPurple());
                            gameItem.setBackground(SignInController.getPurple());
                            ImageIcon editIcon2 = new ImageIcon("src/com/images/edit.png");
                            Image auxEdit2 = editIcon2.getImage();
                            auxEdit2 = auxEdit2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                            editIcon2 = new ImageIcon(auxEdit2);
                            editButton.setIcon(editIcon2); // Establecer el icono en el botón
                        }
                    });
                    deleteButton.addMouseListener(new MouseAdapter() {
                        public void mouseEntered(MouseEvent e) {
                            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                            editButton.setBackground(new Color(60, 45, 145));
                            deleteButton.setBackground(new Color(60, 45, 145));
                            gameItem.setBackground(new Color(60, 45, 145));
                            Timer timer = new Timer(15, new ActionListener() {
                                int red = deleteButton.getBackground().getRed();
                                int green = deleteButton.getBackground().getGreen();
                                int blue = deleteButton.getBackground().getBlue();

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (red < 60) red++;
                                    if (green > 45) green--;
                                    if (blue > 145) blue--;
                                    deleteButton.setBackground(new Color(red, green, blue));
                                    deleteButton.setBackground(new Color(red, green, blue));
                                    gameItem.setBackground(new Color(red, green, blue));
                                    if (red == 60 && green == 45 && blue == 145) {
                                        ((Timer) e.getSource()).stop();
                                    }
                                }
                            });
                            timer.start();
                            ImageIcon deleteIcon2 = new ImageIcon("src/com/images/trash.png");
                            Image aux2 = deleteIcon2.getImage();
                            aux2 = aux2.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                            deleteIcon2 = new ImageIcon(aux2);
                            deleteButton.setIcon(deleteIcon2); // Establecer el icono en el botón
                        }


                        public void mouseExited(MouseEvent e) {
                            editButton.setBackground(SignInController.getPurple());
                            deleteButton.setBackground(SignInController.getPurple());
                            gameItem.setBackground(SignInController.getPurple());
                            ImageIcon deleteIcon2 = new ImageIcon("src/com/images/trash.png");
                            Image aux2 = deleteIcon2.getImage();
                            aux2 = aux2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                            deleteIcon2 = new ImageIcon(aux2);
                            deleteButton.setIcon(deleteIcon2); // Establecer el icono en el botón
                        }
                    });

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

                            // Panel principal de la información del juego
                            JPanel gameInfoPanel = new JPanel(new BorderLayout()) {
                                private ImageIcon gifBackground;

                                {
                                    try {
                                        // Cargar la imagen GIF como ImageIcon
                                        gifBackground = new ImageIcon("src/com/images/fondo.gif");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                protected void paintComponent(Graphics g) {
                                    super.paintComponent(g);
                                    if (gifBackground != null) {
                                        // Dibujar el GIF animado como fondo
                                        gifBackground.paintIcon(this, g, 0, 0);
                                    }
                                }
                            };
                            gameInfoPanel.setPreferredSize(new Dimension(panel.getWidth() - leftPanel.getWidth(), panel.getHeight()));
                            gameInfoPanel.setBackground(new Color(224, 224, 224, 255));


                            // Añadir la imagen de portada del juego en la parte superior
                            ImageIcon gameCoverImageIcon = new ImageIcon(newGame.getGameCoverImage());
                            Image gameCoverImage = gameCoverImageIcon.getImage().getScaledInstance(panel.getWidth() - leftPanel.getWidth(), 350, Image.SCALE_SMOOTH);
                            ImageIcon scaledGameCoverImageIcon = new ImageIcon(gameCoverImage);
                            JLabel gameCoverImageLabel = new JLabel(scaledGameCoverImageIcon, SwingConstants.CENTER);
                            gameInfoPanel.add(gameCoverImageLabel, BorderLayout.NORTH);

                            JButton playButton = new JButton("Play") {
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
                            playButton.setBounds(115, 330, 150, 35);
                            playButton.setBackground(SignInController.getPurple());
                            playButton.setFont(new Font("Helvetica", Font.BOLD, 14));
                            playButton.setForeground(Color.WHITE);
                            playButton.setPreferredSize(new Dimension(150, 75));
                            playButton.setBackground(new Color(80, 65, 165));
                            playButton.setBorder(new RoundedBorder(Color.WHITE, 10));
                            playButton.setContentAreaFilled(true);
                            playButton.setOpaque(false);
                            gameInfoPanel.add(playButton, BorderLayout.CENTER);

                            playButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        Runtime.getRuntime().exec(newGame.getExeLocation(), null, new File(newGame.getFolderLocation()));
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            });
                            playButton.addMouseListener(new MouseAdapter() {
                                public void mouseEntered(MouseEvent e) {
                                    playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                                    playButton.setBackground(new Color(60, 45, 145));
                                }

                                public void mouseExited(MouseEvent e) {
                                    playButton.setBackground(new Color(80, 65, 165));
                                }
                            });

                            gameInfoPanel.add(playButton, BorderLayout.CENTER);

                            JLabel gameDetailsLabel = new JLabel("<html><div style='text-align: center;'>Name: " + newGame.getGameName() + "<br>Description: " + newGame.getGameDescription() + "<br>Genre: " + newGame.getGameGenre() + "</div></html>", SwingConstants.CENTER);
                            gameInfoPanel.add(gameDetailsLabel, BorderLayout.SOUTH);

                            rightPanel.add(gameInfoPanel);


                            rightPanel.revalidate();
                            rightPanel.repaint();
                        }
                    });

                    leftPanel.revalidate();
                    leftPanel.repaint();

                    rightPanel.remove(formPanel);

                    panel.revalidate();
                    panel.repaint();
                }
            });
        });
    }
}