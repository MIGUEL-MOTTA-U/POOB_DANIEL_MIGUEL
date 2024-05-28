package presentation;

import domain.GameModeObserver;
import domain.NormalSquare;
import domain.QuoriPOOBException;
import domain.Square;
import domain.TimeObserver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * This class contains the board, along with the information and actions of the game.
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2024
 */
public class BoardGUI extends JPanel implements TimeObserver, GameModeObserver {
    private static final Color BACKGROUND = new Color(250, 250, 250);
    private static final Color BACKGROUND_SPECIAL_SQUARES = new Color(220, 220, 220);

    private QuoridorGUI quoridorGUI;
    private SquareGUI[][] squaresGUI;
    private JPanel containerBoard;
    private VictoryScreen victoryScreen;
    private DefeatScreen defeatScreen;

    // Board
    private JPanel panelBoard;

    // Time and moves
    private JPanel panelEast;
    private JPanel panelTime;
    private JLabel imageStopWatch;
    private JLabel labelTime;
    private JPanel panelMoves;
    private JButton buttonUp;
    private JButton buttonLeftUp;
    private JButton buttonLeft;
    private JButton buttonLeftDown;
    private JButton buttonDown;
    private JButton buttonRightDown;
    private JButton buttonRight;
    private JButton buttonRightUp;
    private CirclePanel tokenPlaying;

    // Information
    private JPanel panelSouth;

    // Player 1
    private JPanel panelPlayer1;
    private CirclePanel imagePlayer1;
    private JLabel labelNamePlayer1;
    private JLabel labelWallsPlayer1;
    private JLabel labelNormalWallsPlayer1;
    private JLabel labelTemporaryWallsPlayer1;
    private JLabel labelLongWallsPlayer1;
    private JLabel labelAlliedWallsPlayer1;

    // Player 2
    private JPanel panelPlayer2;
    private CirclePanel imagePlayer2;
    private JLabel labelNamePlayer2;
    private JLabel labelWallsPlayer2;
    private JLabel labelNormalWallsPlayer2;
    private JLabel labelTemporaryWallsPlayer2;
    private JLabel labelLongWallsPlayer2;
    private JLabel labelAlliedWallsPlayer2;

    /**
     * Constructor of BoardGUI
     */
    public BoardGUI(QuoridorGUI quoridorGUI) {
        this.quoridorGUI = quoridorGUI;
        this.victoryScreen = null;
        this. defeatScreen = null;
        prepareElements();
        prepareActions();
        setVisible(true);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setFocusable(true);
                requestFocusInWindow();
            }
        });
    }

    /*
     * Create all the elements
     */
    private void prepareElements() {
        setLayout(new BorderLayout());

        JPanel content = new JPanel(new BorderLayout());

        content.add(prepareElementsBoard(), BorderLayout.CENTER);
        prepareElementsEast(content);
        prepareElementsSouth(content);

        add(content);
    }

    /*
     * Create the time and moves elements
     */
    private void prepareElementsEast(JPanel content) {
        panelEast = new JPanel(new GridBagLayout());
        panelEast.setBackground(BACKGROUND);

        JPanel container = new JPanel();
        container.setBackground(BACKGROUND);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new EmptyBorder(0, 30, 0, 30));

        container.add(prepareElementsTime());
        container.add(Box.createVerticalStrut(50));
        container.add(prepareElementsMove());
        panelEast.add(container);

        content.add(panelEast, BorderLayout.EAST);
    }

    /*
     * Create the time elements
     */
    private JPanel prepareElementsTime() {
        panelTime = new JPanel(new GridBagLayout());
        panelTime.setBackground(BACKGROUND);
        panelTime.setBorder(new RoundBorder(Color.BLACK, Color.WHITE, 20));

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.setBackground(Color.WHITE);

        imageStopWatch = new JLabel();
        imageStopWatch.setSize(50, 50);
        createImage(imageStopWatch, "assets/stopWatch.png");

        labelTime = new JLabel();
        if (!quoridorGUI.timeMode()) labelTime.setText("---");
        labelTime.setFont(new Font(QuoridorGUI.FONT_TEXT, Font.PLAIN, 20));

        container.add(imageStopWatch);
        container.add(Box.createHorizontalStrut(40));
        container.add(labelTime);
        panelTime.add(container);

        return panelTime;
    }

    /*
     * Create the moves elements
     */
    private JPanel prepareElementsMove() {
        panelMoves = new JPanel(new GridLayout(3, 3));
        panelMoves.setBackground(BACKGROUND);
        panelMoves.setBorder(new RoundBorder(Color.BLACK, Color.WHITE, 20));

        JPanel panelLeftUp = new JPanel(new GridBagLayout());
        panelLeftUp.setBackground(Color.WHITE);
        JPanel panelUp = new JPanel(new GridBagLayout());
        panelUp.setBackground(Color.WHITE);
        JPanel panelRightUp = new JPanel(new GridBagLayout());
        panelRightUp.setBackground(Color.WHITE);
        JPanel panelLeft = new JPanel(new GridBagLayout());
        panelLeft.setBackground(Color.WHITE);
        tokenPlaying = new CirclePanel(parseColor(this.quoridorGUI.getPlayerPlaying().getColor()));
        JPanel panelRight = new JPanel(new GridBagLayout());
        panelRight.setBackground(Color.WHITE);
        JPanel panelLeftDown = new JPanel(new GridBagLayout());
        panelLeftDown.setBackground(Color.WHITE);
        JPanel panelDown = new JPanel(new GridBagLayout());
        panelDown.setBackground(Color.WHITE);
        JPanel panelRightDown = new JPanel(new GridBagLayout());
        panelRightDown.setBackground(Color.WHITE);

        JPanel panelButtonLeftUp = new JPanel(new GridBagLayout());
        panelButtonLeftUp.setBackground(Color.WHITE);
        panelButtonLeftUp.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel panelButtonUp = new JPanel(new GridBagLayout());
        panelButtonUp.setBackground(Color.WHITE);
        panelButtonUp.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel panelButtonRightUp = new JPanel(new GridBagLayout());
        panelButtonRightUp.setBackground(Color.WHITE);
        panelButtonRightUp.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel panelButtonLeft = new JPanel(new GridBagLayout());
        panelButtonLeft.setBackground(Color.WHITE);
        panelButtonLeft.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel panelButtonRight = new JPanel(new GridBagLayout());
        panelButtonRight.setBackground(Color.WHITE);
        panelButtonRight.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel panelButtonLeftDown = new JPanel(new GridBagLayout());
        panelButtonLeftDown.setBackground(Color.WHITE);
        panelButtonLeftDown.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel panelButtonDown = new JPanel(new GridBagLayout());
        panelButtonDown.setBackground(Color.WHITE);
        panelButtonDown.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel panelButtonRightDown = new JPanel(new GridBagLayout());
        panelButtonRightDown.setBackground(Color.WHITE);
        panelButtonRightDown.setBorder(new EmptyBorder(10, 10, 10, 10));

        buttonLeftUp = createButton("LU", QuoridorGUI.BUTTONS_COLOR, Color.WHITE, QuoridorGUI.BUTTONS_COLOR_HOVER);
        buttonUp = createButton("U", QuoridorGUI.BUTTONS_COLOR, Color.WHITE, QuoridorGUI.BUTTONS_COLOR_HOVER);
        buttonRightUp = createButton("RU", QuoridorGUI.BUTTONS_COLOR, Color.WHITE, QuoridorGUI.BUTTONS_COLOR_HOVER);
        buttonLeft = createButton("L", QuoridorGUI.BUTTONS_COLOR, Color.WHITE, QuoridorGUI.BUTTONS_COLOR_HOVER);
        buttonRight = createButton("R", QuoridorGUI.BUTTONS_COLOR, Color.WHITE, QuoridorGUI.BUTTONS_COLOR_HOVER);
        buttonLeftDown = createButton("LD", QuoridorGUI.BUTTONS_COLOR, Color.WHITE, QuoridorGUI.BUTTONS_COLOR_HOVER);
        buttonDown = createButton("D", QuoridorGUI.BUTTONS_COLOR, Color.WHITE, QuoridorGUI.BUTTONS_COLOR_HOVER);
        buttonRightDown = createButton("RD", QuoridorGUI.BUTTONS_COLOR, Color.WHITE, QuoridorGUI.BUTTONS_COLOR_HOVER);

        panelButtonLeftUp.add(buttonLeftUp);
        panelButtonUp.add(buttonUp);
        panelButtonRightUp.add(buttonRightUp);
        panelButtonLeft.add(buttonLeft);
        panelButtonRight.add(buttonRight);
        panelButtonLeftDown.add(buttonLeftDown);
        panelButtonDown.add(buttonDown);
        panelButtonRightDown.add(buttonRightDown);

        panelLeftUp.add(panelButtonLeftUp);
        panelUp.add(panelButtonUp);
        panelRightUp.add(panelButtonRightUp);
        panelLeft.add(panelButtonLeft);
        panelRight.add(panelButtonRight);
        panelLeftDown.add(panelButtonLeftDown);
        panelDown.add(panelButtonDown);
        panelRightDown.add(panelButtonRightDown);

        panelMoves.add(panelLeftUp);
        panelMoves.add(panelUp);
        panelMoves.add(panelButtonRightUp);
        panelMoves.add(panelLeft);
        panelMoves.add(tokenPlaying);
        panelMoves.add(panelRight);
        panelMoves.add(panelLeftDown);
        panelMoves.add(panelButtonDown);
        panelMoves.add(panelButtonRightDown);

        return panelMoves;
    }

    /*
     * Create the board elements
     */
    private JPanel prepareElementsBoard() {
        containerBoard = new JPanel(new BorderLayout());
        containerBoard.setBackground(BACKGROUND);
        containerBoard.setBorder(new EmptyBorder(30, 30, 30, 30));

        int size = quoridorGUI.getBoardSize();
        squaresGUI = new SquareGUI[size][size];

        panelBoard = new JPanel(new GridLayout(size, size));
        panelBoard.setBackground(Color.WHITE);

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                SquareGUI squareGUI = new SquareGUI(quoridorGUI, this, row, column);
                panelBoard.add(squareGUI);
                squaresGUI[row][column] = squareGUI;
            }
        }

        updateBoard();

        containerBoard.add(panelBoard);

        return containerBoard;
    }

    /*
     * Update the board view
     */
    private void updateBoard() {
        Square[][] squares = quoridorGUI.getBoardMatrix();
        int size = quoridorGUI.getBoardSize();

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                SquareGUI squareGUI = squaresGUI[row][column];
                Square square = squares[row][column];

                if (!(square instanceof NormalSquare)) squaresGUI[row][column].paintBackgroung(BACKGROUND_SPECIAL_SQUARES);

                if (square.getToken() != null) {
                    squareGUI.setColorToken(parseColor(square.getToken().getColor()));
                    squareGUI.drawToken();
                } else {
                    squareGUI.eraseToken();
                }

                paintWalls(squareGUI, square);
            }
        }

        repaint();
        validate();
    }

    /*
     * Paint the walls on the board
     */
    private void paintWalls(SquareGUI squareGUI, Square square) {
        if (square.getWallUp() != null) {
            if (!squareGUI.getWallUp()) {
                squareGUI.setWallUp(parseColor(square.getWallUp().getColor()));
                squareGUI.setWallUp(true);
            }
        } else if (square.getWallLeft() != null) {
            if (!squareGUI.getWallLeft()) {
                squareGUI.setWallLeft(parseColor(square.getWallLeft().getColor()));
                squareGUI.setWallLeft(true);
            }
        } else if (square.getWallDown() != null) {
            if (!squareGUI.getWallDown()) {
                squareGUI.setWallDown(parseColor(square.getWallDown().getColor()));
                squareGUI.setWallDown(true);
            }
        } else if (square.getWallRight() != null) {
            if (!squareGUI.getWallRight()) {
                squareGUI.setWallRight(parseColor(square.getWallRight().getColor()));
                squareGUI.setWallRight(true);
            }
        } else {
            eraseWalls(squareGUI, square);
        }
    }

    /*
     * Erase the walls from the board
     */
    private void eraseWalls(SquareGUI squareGUI, Square square) {
        if (squareGUI.getWallUp()) {
            squareGUI.delWallUp();
            squareGUI.setWallUp(false);
        } else if (squareGUI.getWallLeft()) {
            squareGUI.delWallLeft();
            squareGUI.setWallLeft(false);
        } else if (squareGUI.getWallDown()) {
            squareGUI.delWallDown();
            squareGUI.setWallDown(false);
        } else if (squareGUI.getWallRight()) {
            squareGUI.delWallRight();
            squareGUI.setWallRight(false);
        }
    }

    /*
     * Create the information elements
     */
    private void prepareElementsSouth(JPanel content) {
        panelSouth = new JPanel(new BorderLayout());
        panelSouth.setBackground(BACKGROUND);
        panelSouth.setBorder(new EmptyBorder(0, 30, 15, 30));

        JPanel container = new JPanel(new GridLayout(1, 2));
        container.setBackground(BACKGROUND);
        container.setBorder(new RoundBorder(Color.BLACK, Color.WHITE, 20));

        container.add(prepareElementsPlayer1());
        container.add(prepareElementsPlayer2());
        panelSouth.add(container, BorderLayout.CENTER);

        content.add(panelSouth, BorderLayout.SOUTH);
    }

    /*
     *  Create the information elements of player 1
     */
    private JPanel prepareElementsPlayer1() {
        JPanel container = new JPanel(new GridBagLayout());
        container.setBackground(Color.WHITE);

        panelPlayer1 = new JPanel();
        panelPlayer1.setBackground(Color.WHITE);
        panelPlayer1.setLayout(new BoxLayout(panelPlayer1, BoxLayout.X_AXIS));
        panelPlayer1.setBorder(new EmptyBorder(10, 10, 10, 10));

        String namePlayer = this.quoridorGUI.getNames()[0];
        imagePlayer1 = new CirclePanel(getColorPlayer(namePlayer));
        imagePlayer1.setPreferredSize(new Dimension(50, 50));

        labelNamePlayer1 = new JLabel(namePlayer);
        labelNamePlayer1.setFont(new Font(QuoridorGUI.FONT_SUBTITLE, Font.BOLD, 20));

        labelWallsPlayer1 = createLabel("Remaining walls: " + getTotalRemainingWalls(namePlayer));
        labelNormalWallsPlayer1 = createLabel("Normal: " + getRemainingWalls(namePlayer).get("NormalWall"));
        labelTemporaryWallsPlayer1 = createLabel("Temporary: " + getRemainingWalls(namePlayer).get("Temporary"));
        labelLongWallsPlayer1 = createLabel("Long: " + getRemainingWalls(namePlayer).get("LongWall"));
        labelAlliedWallsPlayer1 = createLabel("Allied: " + getRemainingWalls(namePlayer).get("Allied"));

        panelPlayer1.add(imagePlayer1);
        panelPlayer1.add(Box.createHorizontalStrut(20));
        panelPlayer1.add(prepareElemetsNamePlayer(labelNamePlayer1, labelWallsPlayer1));
        panelPlayer1.add(Box.createHorizontalStrut(30));
        panelPlayer1.add(prepareElementsWallsPlayer(labelNormalWallsPlayer1, labelTemporaryWallsPlayer1,
                labelLongWallsPlayer1, labelAlliedWallsPlayer1));
        container.add(panelPlayer1);

        return container;
    }

    /*
     * Create the information elements of player 2
     */
    private JPanel prepareElementsPlayer2() {
        JPanel container = new JPanel(new GridBagLayout());
        container.setBackground(Color.WHITE);

        panelPlayer2 = new JPanel();
        panelPlayer2.setBackground(Color.WHITE);
        panelPlayer2.setLayout(new BoxLayout(panelPlayer2, BoxLayout.X_AXIS));
        panelPlayer2.setBorder(new EmptyBorder(10, 10, 10, 10));

        String namePlayer = this.quoridorGUI.getNames()[1];
        imagePlayer2 = new CirclePanel(getColorPlayer(namePlayer));
        imagePlayer2.setPreferredSize(new Dimension(50, 50));

        labelNamePlayer2 = new JLabel(namePlayer);
        labelNamePlayer2.setFont(new Font(QuoridorGUI.FONT_SUBTITLE, Font.BOLD, 20));

        labelWallsPlayer2 = createLabel("Remaining walls: " + getTotalRemainingWalls(namePlayer));
        labelNormalWallsPlayer2 = createLabel("Normal: " + getRemainingWalls(namePlayer).get("NormalWall"));
        labelTemporaryWallsPlayer2 = createLabel("Temporary: " + getRemainingWalls(namePlayer).get("Temporary"));
        labelLongWallsPlayer2 = createLabel("Long: " + getRemainingWalls(namePlayer).get("LongWall"));
        labelAlliedWallsPlayer2 = createLabel("Allied: " + getRemainingWalls(namePlayer).get("Allied"));

        panelPlayer2.add(imagePlayer2);
        panelPlayer2.add(Box.createHorizontalStrut(20));
        panelPlayer2.add(prepareElemetsNamePlayer(labelNamePlayer2, labelWallsPlayer2));
        panelPlayer2.add(Box.createHorizontalStrut(30));
        panelPlayer2.add(prepareElementsWallsPlayer(labelNormalWallsPlayer2, labelTemporaryWallsPlayer2,
                labelLongWallsPlayer2, labelAlliedWallsPlayer2));
        container.add(panelPlayer2);

        return container;
    }

    /*
     * Create the name player elements
     */
    private JPanel prepareElemetsNamePlayer(JLabel playerName, JLabel playerWalls) {
        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.add(playerName);
        content.add(playerWalls);

        return content;
    }

    /*
     * create the walls information elements
     */
    private JPanel prepareElementsWallsPlayer(JLabel normal, JLabel temporary, JLabel longW, JLabel allied) {
        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.add(normal);
        content.add(Box.createVerticalStrut(5));
        content.add(temporary);
        content.add(Box.createVerticalStrut(5));
        content.add(longW);
        content.add(Box.createVerticalStrut(5));
        content.add(allied);

        return content;
    }

    /*
     * Create a button
     */
    private JButton createButton(String text, Color background, Color foreGround, Color hover) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBackground(background);
        button.setForeground(foreGround);
        button.setPreferredSize(new Dimension(60, 30));

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
     * Create a label
     */
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(QuoridorGUI.FONT_TEXT, Font.PLAIN, 10));

        return label;
    }

    /*
     * Create the actions
     */
    private void prepareActions() {
        prepareActionButtonsMove();
        prepareActionKeys();
    }

    /*
     * Create the button actions
     */
    private void prepareActionButtonsMove() {
        buttonUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    quoridorGUI.moveToken("UP");
                    refresh();
                } catch (QuoriPOOBException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    quoridorGUI.moveToken("LEFT");
                    refresh();
                } catch (QuoriPOOBException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    quoridorGUI.moveToken("DOWN");
                    refresh();
                } catch (QuoriPOOBException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    quoridorGUI.moveToken("RIGHT");
                    refresh();
                } catch (QuoriPOOBException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonLeftDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    quoridorGUI.moveToken("DOWNLEFT");
                    refresh();
                } catch (QuoriPOOBException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonLeftUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    quoridorGUI.moveToken("UPLEFT");
                    refresh();
                } catch (QuoriPOOBException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonRightDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    quoridorGUI.moveToken("DOWNRIGHT");
                    refresh();
                } catch (QuoriPOOBException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonRightUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    quoridorGUI.moveToken("UPRIGHT");
                    refresh();
                } catch (QuoriPOOBException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /*
     * Create the keyboard actions
     */
    private void prepareActionKeys() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ev) {
                handleKeyPress(ev);
            }
        });
    }

    /*
     * Create the keyboard actions
     */
    private void handleKeyPress(KeyEvent ev) {
        int keyCode = ev.getKeyCode();
        try {
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    quoridorGUI.moveToken("UP");
                    break;
                case KeyEvent.VK_DOWN:
                    quoridorGUI.moveToken("DOWN");
                    break;
                case KeyEvent.VK_LEFT:
                    quoridorGUI.moveToken("LEFT");
                    break;
                case KeyEvent.VK_RIGHT:
                    quoridorGUI.moveToken("RIGHT");
                    break;
                default:
                    return;
            }
            refresh();
        } catch (QuoriPOOBException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Create a new game
     */
    public void newGame() {
        this.quoridorGUI.restart();
        this.quoridorGUI.showStartGUI();
    }

    /**
     * Exit the game
     */
    public void exit() {
        this.quoridorGUI.confirmClose();
    }

    /**
     * It is the behavior when the game time is over
     */
    @Override
    public void timeIsUp() {
        if (this.quoridorGUI.getGameOver()) {
            showDefeatScreen();
        }

        refresh();
    }

    /**
     * Update the player time remaining
     */
    @Override
    public void updateTime(int time) {
        String timeString = String.format("%d", time);
        labelTime.setText(timeString);
    }

    /**
     * It is the behavior when a player finishes his turn
     */
    @Override
    public void onGameModeUpdated() {
        refresh();
    }

    /**
     * check if the game is over
     */
    @Override
    public void checkGameFinished() {
        if (quoridorGUI.getGameOver()) {
            if (quoridorGUI.getWinner() != null) {
                if (quoridorGUI.getWinner().getName().equals("Machine")) {
                    showDefeatScreen(); 
                } else {
                    showVictoryScreen();
                }
            } else {
                showDefeatScreen();
            }
        }
    }

    /*
     * Create an image
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
     * Get the remaining walls of the player
     */
    private HashMap<String, Integer> getRemainingWalls(String namePlayer) {
        try {
            String colorPlayer = this.quoridorGUI.getColorPlayer(namePlayer);
            HashMap<String, HashMap<String, Integer>> numberWalls = this.quoridorGUI.getReaminingWalls();

            for (Map.Entry<String, HashMap<String, Integer>> entry : numberWalls.entrySet()) {
                String color = entry.getKey();
                if (color.equals(colorPlayer)) {
                    return entry.getValue();
                }
            }
        } catch (QuoriPOOBException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    /*
     * Get the total walls of a player
     */
    private int getTotalRemainingWalls(String namePlayer) {
        HashMap<String, Integer> numWalls = getRemainingWalls(namePlayer);
        int total = 0;

        for (int numWall : numWalls.values()) {
            total += numWall;
        }

        return total;
    }

    /*
     * Update the number walls of each player
     */
    private void updateNumWalls() {
        String namePlayer1 = this.quoridorGUI.getNames()[0];
        String namePlayer2 = this.quoridorGUI.getNames()[1];

        labelWallsPlayer1.setText("Remaining walls: " + getTotalRemainingWalls(namePlayer1));
        labelNormalWallsPlayer1.setText("Normal: " + getRemainingWalls(namePlayer1).get("NormalWall"));
        labelTemporaryWallsPlayer1.setText("Temporary: " + getRemainingWalls(namePlayer1).get("Temporary"));
        labelLongWallsPlayer1.setText("Long: " + getRemainingWalls(namePlayer1).get("LongWall"));
        labelAlliedWallsPlayer1.setText("Allied: " + getRemainingWalls(namePlayer1).get("Allied"));
        labelWallsPlayer2.setText("Remaining walls: " + getTotalRemainingWalls(namePlayer2));
        labelNormalWallsPlayer2.setText("Normal: " + getRemainingWalls(namePlayer2).get("NormalWall"));
        labelTemporaryWallsPlayer2.setText("Temporary: " + getRemainingWalls(namePlayer2).get("Temporary"));
        labelLongWallsPlayer2.setText("Long: " + getRemainingWalls(namePlayer2).get("LongWall"));
        labelAlliedWallsPlayer2.setText("Allied: " + getRemainingWalls(namePlayer2).get("Allied"));
    }

    /*
     * Get the color player
     */
    private Color getColorPlayer(String name) {
        Color color = null;

        try {
            color = parseColor(this.quoridorGUI.getColorPlayer(name));
        } catch (QuoriPOOBException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return color;
    }

    /**
     * Update the game information and the game view
     */
    public void refresh() {
        updateBoard();
        updateNumWalls();
        tokenPlaying.setCircleColor(parseColor(this.quoridorGUI.getPlayerPlaying().getColor()));
        validate();
        repaint();
    }

    /*
     * Show the victory screen
     */
    private void showVictoryScreen() {
        if (victoryScreen == null) {
            victoryScreen = new VictoryScreen(this, this.quoridorGUI.getWinner().getName());
            containerBoard.remove(panelBoard);
            containerBoard.setLayout(new GridBagLayout());
            containerBoard.add(victoryScreen);
            refresh();
        }
    }

    /*
     * Show the defeat screen
     */
    private void showDefeatScreen() {
        if (defeatScreen == null) {
            defeatScreen = new DefeatScreen(this);
            containerBoard.remove(panelBoard);
            containerBoard.setLayout(new GridBagLayout());
            containerBoard.add(defeatScreen);
            refresh();
        }
    }

    /*
	 * Convert a string into a color
	 */
	private static Color parseColor(String colorString) {
        colorString = colorString.replace("java.awt.Color[", "").replace("]", "");
        String[] components = colorString.split(",");
        int r = 0, g = 0, b = 0;

        for (String component : components) {
            String[] keyValue = component.split("=");
            String key = keyValue[0].trim();
            int value = Integer.parseInt(keyValue[1].trim());

            switch (key) {
                case "r":
                    r = value;
                    break;
                case "g":
                    g = value;
                    break;
                case "b":
                    b = value;
                    break;
            }
        }

        return new Color(r, g, b);
    }
}
