package presentation;

import domain.QuoriPOOBException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;

/**
 * Create the screen where the player configurates the game
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2024
 */
public class SetUpGameGUI extends JPanel {
    public static final Dimension PREFERRED_DIMENSION = new Dimension(250, 30);
    private static final String BOARD_SIZE_MSG = "Board size";
    private static final String TELEPORTER_SQUARES_MSG = "Number of teleporter squares";
    private static final String RETURN_SQUARES_MSG = "Number of return squares";
    private static final String DOUBLE_TURN_SQUARES_MSG = "Number of double turn squares";
    private static final String INVALID_NUMBER_MSG = "You must enter a valid number";
    private static final String INVALID_NUMBER = "Invalid number";

    private QuoridorGUI quoridorGUI;
    private ArrayList<JTextField[]> normalPositions;
    private ArrayList<JTextField[]> teleporterPositions;
    private ArrayList<JTextField[]> returnPositions;
    private ArrayList<JTextField[]> doubleTurnPositions;
    HashMap<String, int[][]> specialSquares;

    // West
    private JPanel panelWest;
    private JLabel imageGame;
    private JLabel labelTitle;
    private JLabel labelDescription;
    private JPanel panelSquares;
    private JLabel labelSquareTitle;
    private JTextField textTeleporterSquares;
    private JButton buttonPositionTeleporter;
    private JTextField textReturnSquares;
    private JButton buttonPositionReturn;
    private JTextField textDoubleTurnSquares;
    private JButton buttonPositionDoubleTurn;

    // East
    private JPanel panelEast;
    private JTextField textBoardSize;
    private JTextField textTime;
    private JPanel panelWalls;
    private JLabel labelWallTitle;
    private JTextField textNormalWalls;
    private JTextField textTemporaryWalls;
    private JTextField textLongWalls;
    private JTextField textAlliedWalls;
    private JButton buttonNext;

    /**
     * Constructor of BoardGUI
     */
    public SetUpGameGUI(QuoridorGUI quoridorGUI) {
        this.quoridorGUI = quoridorGUI;
        normalPositions = new ArrayList<>();
        teleporterPositions = new ArrayList<>();
        returnPositions = new ArrayList<>();
        doubleTurnPositions = new ArrayList<>();
        specialSquares = new HashMap<>();
        prepareElements();
        prepareActions();
        setVisible(true);
    }

    /*
     * Create all the elements
     */
    private void prepareElements() {
        JPanel container = new JPanel();

        JPanel content = new JPanel(new BorderLayout());
        content.setBorder(new RoundBorder(Color.WHITE, Color.WHITE, 20));

        prepareElementsWest(content);
        prepareElementsEast(content);

        container.add(content);
        add(container);
    }

    /*
     * Create the squares configuration elements
     */
    private void prepareElementsWest(JPanel content) {
        panelWest = new JPanel();
        panelWest.setBackground(Color.WHITE);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new EmptyBorder(0, 0, 0, 50));
        container.setBackground(Color.WHITE);

        JPanel containerHeader = new JPanel(new BorderLayout());
        containerHeader.setBackground(Color.WHITE);

        containerHeader.add(prepareElementsWestHeader(), BorderLayout.WEST);
        container.add(containerHeader);
        container.add(Box.createVerticalStrut(50));
        container.add(prepareElementsSquares());
        panelWest.add(container);

        content.add(panelWest, BorderLayout.WEST);
    }

    /*
     * Create the title elements
     */
    private JPanel prepareElementsWestHeader() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.WHITE);

        imageGame = new JLabel();
        imageGame.setSize(50, 50);
        createImage(imageGame, "assets/Logo.png");

        labelTitle = new JLabel("Set up the game");
        labelTitle.setFont(new Font(QuoridorGUI.FONT_TITLE, Font.PLAIN, 25));

        labelDescription = new JLabel("Enter the game data");
        labelDescription.setFont(new Font(QuoridorGUI.FONT_TEXT, Font.PLAIN, 15));

        container.add(imageGame);
        container.add(Box.createVerticalStrut(10));
        container.add(labelTitle);
        container.add(Box.createVerticalStrut(10));
        container.add(labelDescription);

        return container;
    }

    /*
     * Create the squares configuration elements
     */
    private JPanel prepareElementsSquares() {
        panelSquares = new JPanel();
        panelSquares.setLayout(new BoxLayout(panelSquares, BoxLayout.Y_AXIS));
        panelSquares.setBackground(Color.WHITE);

        labelSquareTitle = new JLabel("Squares");
        labelSquareTitle.setFont(new Font(QuoridorGUI.FONT_SUBTITLE, Font.BOLD, 15));

        textTeleporterSquares = createTextField(TELEPORTER_SQUARES_MSG);
        textReturnSquares = createTextField(RETURN_SQUARES_MSG);
        textDoubleTurnSquares = createTextField(DOUBLE_TURN_SQUARES_MSG);

        buttonPositionTeleporter = createButton("Positions", QuoridorGUI.BUTTONS_COLOR, Color.WHITE,
                QuoridorGUI.BUTTONS_COLOR_HOVER);
        buttonPositionTeleporter.setBorder(new EmptyBorder(5, 5, 5, 5));
        buttonPositionReturn = createButton("Positions", QuoridorGUI.BUTTONS_COLOR, Color.WHITE,
                QuoridorGUI.BUTTONS_COLOR_HOVER);
        buttonPositionReturn.setBorder(new EmptyBorder(5, 5, 5, 5));
        buttonPositionDoubleTurn = createButton("Positions", QuoridorGUI.BUTTONS_COLOR, Color.WHITE,
                QuoridorGUI.BUTTONS_COLOR_HOVER);
        buttonPositionDoubleTurn.setBorder(new EmptyBorder(5, 5, 5, 5));

        panelSquares.add(prepareElementsTitle(labelSquareTitle));
        panelSquares.add(Box.createVerticalStrut(10));
        panelSquares.add(prepareElementsSquare(textTeleporterSquares, buttonPositionTeleporter));
        panelSquares.add(Box.createVerticalStrut(10));
        panelSquares.add(prepareElementsSquare(textReturnSquares, buttonPositionReturn));
        panelSquares.add(Box.createVerticalStrut(10));
        panelSquares.add(prepareElementsSquare(textDoubleTurnSquares, buttonPositionDoubleTurn));

        return panelSquares;
    }

    /*
     * Create the board and walls configuration elements
     */
    private void prepareElementsEast(JPanel content) {
        panelEast = new JPanel();
        panelEast.setBackground(Color.WHITE);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.WHITE);

        JPanel panelButtonNext = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelButtonNext.setBackground(Color.WHITE);

        textBoardSize = createTextField("Board size");
        textTime = createTextField("Time");
        if (!quoridorGUI.timeMode()) textTime.setEnabled(false);

        buttonNext = createButton("Next", QuoridorGUI.BUTTONS_COLOR, Color.WHITE, QuoridorGUI.BUTTONS_COLOR_HOVER);
        buttonNext.setBorder(new EmptyBorder(7, 7, 7, 7));

        panelButtonNext.add(buttonNext);
        container.add(Box.createVerticalStrut(70));
        container.add(textBoardSize);
        container.add(Box.createVerticalStrut(10));
        container.add(textTime);
        container.add(Box.createVerticalStrut(30));
        container.add(prepareElementsWalls());
        container.add(Box.createVerticalStrut(50));
        container.add(panelButtonNext);
        panelEast.add(container);

        content.add(panelEast, BorderLayout.EAST);
    }

    /*
     * Create the walls configuration elements
     */
    private JPanel prepareElementsWalls() {
        panelWalls = new JPanel();
        panelWalls.setLayout(new BoxLayout(panelWalls, BoxLayout.Y_AXIS));
        panelWalls.setBackground(Color.WHITE);

        labelWallTitle = new JLabel("Walls");
        labelWallTitle.setFont(new Font(QuoridorGUI.FONT_SUBTITLE, Font.BOLD, 15));

        textNormalWalls = createTextField("Number of normal Walls");
        textTemporaryWalls = createTextField("Number of temporary walls");
        textLongWalls = createTextField("Number of long walls");
        textAlliedWalls = createTextField("Number of allied walls");

        panelWalls.add(prepareElementsTitle(labelWallTitle));
        panelWalls.add(Box.createVerticalStrut(10));
        panelWalls.add(textNormalWalls);
        panelWalls.add(Box.createVerticalStrut(10));
        panelWalls.add(textTemporaryWalls);
        panelWalls.add(Box.createVerticalStrut(10));
        panelWalls.add(textLongWalls);
        panelWalls.add(Box.createVerticalStrut(10));
        panelWalls.add(textAlliedWalls);

        return panelWalls;
    }

    /*
     * Create the squares configuration elements
     */
    private JPanel prepareElementsSquare(JTextField textField, JButton button) {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.setBackground(Color.WHITE);

        container.add(textField);
        container.add(Box.createHorizontalStrut(10));
        container.add(button);

        return container;
    }

    /*
     * Create the title elements
     */
    private JPanel prepareElementsTitle(JLabel label) {
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(Color.WHITE);

        container.add(label, BorderLayout.WEST);

        return container;
    }

    /*
     * Create the squares positions configuration elements
     */
    private void showPositionsDialog(int quantity, JButton button) {
        String type = getTypeFromButton(button);
        clearSquares(type);

        boolean success = false;
        do {
            JPanel panel = new JPanel(new GridLayout(quantity, 1));

            for (int i = 0; i < quantity; i++) {
                panel.add(createPositionsDialog(type));
            }

            int result = JOptionPane.showConfirmDialog(this, panel, "Enter Positions", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                int[][] squares = createSpecialSquares(type);

                if (squares != null) {
                    specialSquares.put("domain." + type, squares);
                    success = true;
                }
            } else {
                break;
            }
        } while (!success);
    }

    /*
     * Create the squares positions configuration elements
     */
    private JPanel createPositionsDialog(String type) {
        JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JTextField textRow = new JTextField();
        textRow.setPreferredSize(new Dimension(50, 20));

        JTextField textColumn = new JTextField();
        textColumn.setPreferredSize(new Dimension(50, 20));

        JLabel labelRow = new JLabel("Row: ");
        JLabel labelColumn = new JLabel("Column: ");

        addPositions(type, textRow, textColumn);

        container.add(labelRow);
        container.add(textRow);
        container.add(labelColumn);
        container.add(textColumn);

        return container;
    }

    /*
     * Create a button
     */
    private JButton createButton(String text, Color background, Color foreGround, Color hover) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(background);
        button.setForeground(foreGround);

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent ev) {
                button.setBackground(hover);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent ev) {
                button.setBackground(background);
                setCursor(Cursor.getDefaultCursor());
            }
        });

        return button;
    }

    /*
     * Create a text field
     */
    private JTextField createTextField(String text) {
        JTextField textField = new JTextField(text);
        textField.setPreferredSize(PREFERRED_DIMENSION);
        textField.setFont(new Font("Arial", Font.PLAIN, 12));
        textField.setForeground(Color.GRAY);

        textField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent ev) {
                if (textField.getText().equals(text)) {
                    textField.setText("");
                }
            }

            public void focusLost(FocusEvent ev) {
                if (textField.getText().isEmpty()) {
                    textField.setText(text);
                }
            }
        });

        return textField;
    }

    /*
     * Creates the actions
     */
    private void prepareActions() {
        prepareActionsButtons();
    }

    /*
     * Create the buttons actions
     */
    private void prepareActionsButtons() {
        ActionListener positionButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                JButton sourceButton = (JButton) ev.getSource();
                JTextField textField = null;
                String quantityString = "";

                if (sourceButton == buttonPositionTeleporter) {
                    textField = textTeleporterSquares;
                } else if (sourceButton == buttonPositionReturn) {
                    textField = textReturnSquares;
                } else if (sourceButton == buttonPositionDoubleTurn) {
                    textField = textDoubleTurnSquares;
                }

                if (textField != null) {
                    quantityString = textField.getText();
                }

                try {
                    int quantity = getQuantity(quantityString);

                    if (quantity >= 0) {
                        showPositionsDialog(quantity, sourceButton);
                    }else if (quantity == 0) {
                        String type = getTypeFromButton(sourceButton);
                        specialSquares.put("domain." + type, null);
                    } else {
                        showError("You must enter a positive number", INVALID_NUMBER);
                    }
                } catch (NumberFormatException e) {
                    showError(INVALID_NUMBER_MSG, INVALID_NUMBER);
                }
            }
        };

        buttonPositionTeleporter.addActionListener(positionButtonListener);
        buttonPositionReturn.addActionListener(positionButtonListener);
        buttonPositionDoubleTurn.addActionListener(positionButtonListener);

        buttonNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (!emptyInfo()) {
                    String stringSize = textBoardSize.getText();
                    try {
                        int size = Integer.parseInt(stringSize);

                        if (specialSquares.isEmpty()) {
                            quoridorGUI.createBoard(size, null);
                        } else {
                            quoridorGUI.createBoard(size, specialSquares);
                        }

                        setTime();

                        int[] numberWalls = getNumberWalls();

                        if (numberWalls != null) {
                            quoridorGUI.addWalls(numberWalls[0], numberWalls[1], numberWalls[2], numberWalls[3]);
                            quoridorGUI.showBoardGUI();
                        }
                    } catch (NumberFormatException e) {
                        showError(INVALID_NUMBER_MSG, INVALID_NUMBER);
                    } catch (QuoriPOOBException e) {
                        showError(e.getMessage(), "Error");
                    }
                }

            }
        });
    }

    /*
     * Create a image
     */
    private void createImage(JLabel label, String path) {
        URL url = getClass().getResource(path);

        if (url != null) {
            ImageIcon img = new ImageIcon(url);
            label.setIcon(new ImageIcon(
                    img.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH)));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
        }
    }

    /*
     * Convert a string to int
     */
    private int getQuantity(String text) throws NumberFormatException {
        return Integer.parseInt(text);
    }

    /*
     * Verify if the game configuration is incomplete
     */
    private boolean emptyInfo() {
        boolean empty = false;

        if (textBoardSize.getText().equals(BOARD_SIZE_MSG)) {
            showMessage("You must enter the size of the board", "Board size not entered");
            empty = true;
        }

        if (isSpecialSquareInfoEmpty(textTeleporterSquares, TELEPORTER_SQUARES_MSG, teleporterPositions)) {
            showMessage("You must enter the positions of the teleporter squares", "Positions not entered");
            empty = true;
        } else if (isSpecialSquareInfoEmpty(textReturnSquares, RETURN_SQUARES_MSG, returnPositions)) {
            showMessage("You must enter the positions of the return squares", "Positions not entered");
            empty = true;
        } else if (isSpecialSquareInfoEmpty(textDoubleTurnSquares, DOUBLE_TURN_SQUARES_MSG, doubleTurnPositions)) {
            showMessage("You must enter the positions of the double turn squares", "Positions not entered");
            empty = true;
        }

        return empty;
    }

    /*
     * Verify if the game configuration is incomplete
     */
    private boolean isSpecialSquareInfoEmpty(JTextField textField, String defaultText, ArrayList<JTextField[]> positions) {
        return !textField.getText().equals(defaultText) && !textField.getText().equals("0") && positions.isEmpty();
    }

    /*
     * Get the square type from the button
     */
    private String getTypeFromButton(JButton button) {
        if (button == buttonPositionTeleporter) {
            return "Teleporter";
        } else if (button == buttonPositionReturn) {
            return "Return";
        } else if (button == buttonPositionDoubleTurn) {
            return "DoubleTurn";
        } else {
            return null;
        }
    }

    /*
     * Add the squares positions
     */
    private void addPositions(String type, JTextField row, JTextField column) {
        JTextField[] textPositions = new JTextField[2];
        textPositions[0] = row;
        textPositions[1] = column;

        switch (type) {
            case "NormalSquare":
                normalPositions.add(textPositions);
                break;
            case "Teleporter":
                teleporterPositions.add(textPositions);
                break;
            case "Return":
                returnPositions.add(textPositions);
                break;
            case "DoubleTurn":
                doubleTurnPositions.add(textPositions);
                break;
            default:
                break;
        }
    }

    /*
     * Create special squares
     */
    private int[][] createSpecialSquares(String type) {
        ArrayList<JTextField[]> positions = null;

        switch (type) {
            case "NormalSquare":
                positions = normalPositions;
                break;
            case "Teleporter":
                positions = teleporterPositions;
                break;
            case "Return":
                positions = returnPositions;
                break;
            case "DoubleTurn":
                positions = doubleTurnPositions;
                break;
            default:
                break;
        }

        if (positions != null) {
            int[][] squares = new int[positions.size()][2];
            int i = 0;

            try {
                for (JTextField[] position : positions) {
                    squares[i][0] = Integer.parseInt(position[0].getText());
                    squares[i][1] = Integer.parseInt(position[1].getText());
                    i++;
                }

                return squares;
            } catch (NumberFormatException e) {
                switch (type) {
                    case "NormalSquare":
                        normalPositions.clear();
                        break;
                    case "Teleporter":
                        teleporterPositions.clear();
                        break;
                    case "Return":
                        returnPositions.clear();
                        break;
                    case "DoubleTurn":
                        doubleTurnPositions.clear();
                        break;
                    default:
                        break;
                }
                showError(INVALID_NUMBER_MSG, INVALID_NUMBER);
            }
        }

        return null;
    }

    /*
     * Get the number walls
     */
    public int[] getNumberWalls() {
        try {
            int[] numberWalls = new int[4];
            numberWalls[0] = Integer.parseInt(textNormalWalls.getText());
            numberWalls[1] = Integer.parseInt(textTemporaryWalls.getText());
            numberWalls[2] = Integer.parseInt(textLongWalls.getText());
            numberWalls[3] = Integer.parseInt(textAlliedWalls.getText());
            
            return numberWalls;
        } catch (NumberFormatException e) {
            showError(INVALID_NUMBER_MSG, INVALID_NUMBER);
        }

        return null;
    }

    /*
     * Clear the square positions
     */
    private void clearSquares(String type) {
        switch (type) {
            case "NormalSquare":
                normalPositions.clear();
                break;
            case "Teleporter":
                teleporterPositions.clear();
                break;
            case "Return":
                returnPositions.clear();
                break;
            case "DoubleTurn":
                doubleTurnPositions.clear();
                break;
            default:
                break;
        }
    }

    /*
     * Set the game time
     */
    private void setTime() {
        if (quoridorGUI.timeMode()) {
            try {
                int time = Integer.parseInt(textTime.getText());
                quoridorGUI.setTime(time);
            } catch (NumberFormatException e) {
                showError(INVALID_NUMBER_MSG, INVALID_NUMBER);
            }
        }
    }

    /*
     * Show a message
     */
    private void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /*
     * Show a error message
     */
    private void showError(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
