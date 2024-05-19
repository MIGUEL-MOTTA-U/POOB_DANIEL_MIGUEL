package presentation;

import domain.QuoriPOOBException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;

public class SetUpGameGUI extends JPanel {
    public static final Dimension PREFERRED_DIMENSION = new Dimension(250, 30);

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
    private JTextField textNormalSquares;
    private JButton buttonPositionNormal;
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

    private void prepareElements() {
        JPanel container = new JPanel();

        JPanel content = new JPanel(new BorderLayout());
        content.setBorder(new RoundBorder(Color.WHITE, Color.WHITE, 20));

        prepareElementsWest(content);
        prepareElementsEast(content);

        container.add(content);
        add(container);
    }

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

    private JPanel prepareElementsSquares() {
        panelSquares = new JPanel();
        panelSquares.setLayout(new BoxLayout(panelSquares, BoxLayout.Y_AXIS));
        panelSquares.setBackground(Color.WHITE);

        labelSquareTitle = new JLabel("Squares");
        labelSquareTitle.setFont(new Font(QuoridorGUI.FONT_SUBTITLE, Font.BOLD, 15));

        textNormalSquares = createTextField("Number of normal squares");
        textTeleporterSquares = createTextField("Number of teleporter squares");
        textReturnSquares = createTextField("Number of return squares");
        textDoubleTurnSquares = createTextField("Number of double turn squares");

        buttonPositionNormal = createButton("Positions", QuoridorGUI.BUTTONS_COLOR, Color.WHITE,
                QuoridorGUI.BUTTONS_COLOR_HOVER);
        buttonPositionNormal.setBorder(new EmptyBorder(5, 5, 5, 5));
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
        panelSquares.add(prepareElementsSquare(textNormalSquares, buttonPositionNormal));
        panelSquares.add(Box.createVerticalStrut(10));
        panelSquares.add(prepareElementsSquare(textTeleporterSquares, buttonPositionTeleporter));
        panelSquares.add(Box.createVerticalStrut(10));
        panelSquares.add(prepareElementsSquare(textReturnSquares, buttonPositionReturn));
        panelSquares.add(Box.createVerticalStrut(10));
        panelSquares.add(prepareElementsSquare(textDoubleTurnSquares, buttonPositionDoubleTurn));

        return panelSquares;
    }

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

    private JPanel prepareElementsSquare(JTextField textField, JButton button) {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.setBackground(Color.WHITE);

        container.add(textField);
        container.add(Box.createHorizontalStrut(10));
        container.add(button);

        return container;
    }

    private JPanel prepareElementsTitle(JLabel label) {
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(Color.WHITE);

        container.add(label, BorderLayout.WEST);

        return container;
    }

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

    private void prepareActions() {
        prepareActionsButtons();
    }

    private void prepareActionsButtons() {
        ActionListener positionButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                JButton sourceButton = (JButton) ev.getSource();
                JTextField textField = null;
                String quantityString = "";

                if (sourceButton == buttonPositionNormal) {
                    textField = textNormalSquares;
                } else if (sourceButton == buttonPositionTeleporter) {
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

                    if (quantity > 0) {
                        showPositionsDialog(quantity, sourceButton);
                    } else {
                        JOptionPane.showMessageDialog(null, "You must enter a positive number", "Invalid number",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "You must enter a valid number", "Invalid number",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        buttonPositionNormal.addActionListener(positionButtonListener);
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
                        JOptionPane.showMessageDialog(null, "You must enter a valid number", "Invalid number",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (QuoriPOOBException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
    }

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

    private int getQuantity(String text) throws NumberFormatException {
        return Integer.parseInt(text);
    }

    private boolean emptyInfo() {
        boolean empty = false;

        if (textBoardSize.getText().equals("Board size")) {
            JOptionPane.showMessageDialog(null, "You must enter the size of the board", "Board size not entered",
                    JOptionPane.INFORMATION_MESSAGE);
            empty = true;
        }

        return empty;
    }

    private String getTypeFromButton(JButton button) {
        if (button == buttonPositionNormal) {
            return "NormalSquare";
        } else if (button == buttonPositionTeleporter) {
            return "Teleporter";
        } else if (button == buttonPositionReturn) {
            return "Return";
        } else if (button == buttonPositionDoubleTurn) {
            return "DoubleTurn";
        } else {
            return null;
        }
    }

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
                JOptionPane.showMessageDialog(null, "You must enter a valid number", "Invalid number",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        return null;
    }

    public int[] getNumberWalls() {
        try {
            int[] numberWalls = new int[4];
            numberWalls[0] = Integer.parseInt(textNormalWalls.getText());
            numberWalls[1] = Integer.parseInt(textTemporaryWalls.getText());
            numberWalls[2] = Integer.parseInt(textLongWalls.getText());
            numberWalls[3] = Integer.parseInt(textAlliedWalls.getText());
            
            return numberWalls;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "You must enter a valid number", "Invalid number",
                    JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

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

    private void setTime() {
        if (quoridorGUI.timeMode()) {
            try {
                int time = Integer.parseInt(textTime.getText());
                quoridorGUI.setTime(time);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "You must enter a valid number", "Invalid number",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
