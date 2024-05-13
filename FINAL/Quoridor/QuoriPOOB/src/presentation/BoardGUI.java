package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import domain.Square;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;


public class BoardGUI extends JPanel{
    private static final Color BACKGROUND = new Color(250, 250, 250);

    private QuoridorGUI quoridorGUI;
    private SquareGUI[][] squaresGUI;

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

    // Information
    private JPanel panelSouth;

    // Player 1
    private JPanel panelPlayer1;
    private JLabel imagePlayer1;
    private JLabel labelNamePlayer1;
    private JLabel labelWallsPlayer1;
    private JLabel labelNormalWallsPlayer1;
    private JLabel labelTemporaryWallsPlayer1;
    private JLabel labelLongWallsPlayer1;
    private JLabel labelAlliedWallsPlayer1;

    // Player 2
    private JPanel panelPlayer2;
    private JLabel imagePlayer2;
    private JLabel labelNamePlayer2;
    private JLabel labelWallsPlayer2;
    private JLabel labelNormalWallsPlayer2;
    private JLabel labelTemporaryWallsPlayer2;
    private JLabel labelLongWallsPlayer2;
    private JLabel labelAlliedWallsPlayer2;


    public BoardGUI(QuoridorGUI quoridorGUI) {
        this.quoridorGUI = quoridorGUI;
        prepareElements();
        prepareActions();
        setVisible(true);
    }

    private void prepareElements() {
        setLayout(new BorderLayout());

        JPanel content = new JPanel(new BorderLayout());

        content.add(prepareElementsBoard(), BorderLayout.CENTER);
        prepareElementsEast(content);
        prepareElementsSouth(content);

        add(content);
    }

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

        labelTime = new JLabel("2:24");
        labelTime.setFont(new Font(QuoridorGUI.FONT_TEXT, Font.PLAIN, 20));

        container.add(imageStopWatch);
        container.add(Box.createHorizontalStrut(40));
        container.add(labelTime);
        panelTime.add(container);

        return panelTime;
    }

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
        JPanel panelCenter = new JPanel(new GridBagLayout());
        panelCenter.setBackground(Color.WHITE);
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

        JPanel  panelButtonUp = new JPanel(new GridBagLayout());
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

        JPanel  panelButtonDown = new JPanel(new GridBagLayout());
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
        panelMoves.add(panelCenter);
        panelMoves.add(panelRight);
        panelMoves.add(panelLeftDown);
        panelMoves.add(panelButtonDown);
        panelMoves.add(panelButtonRightDown);

        return panelMoves;
    }

    private JPanel prepareElementsBoard() {
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(BACKGROUND);
        container.setBorder(new EmptyBorder(30, 30, 30, 30));
    
        int size = quoridorGUI.getBoardSize();
        squaresGUI = new SquareGUI[size][size];
        
        panelBoard = new JPanel(new GridLayout(size, size));
        panelBoard.setBackground(Color.WHITE);
    
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                SquareGUI squareGUI = new SquareGUI(quoridorGUI, row, column);
                panelBoard.add(squareGUI);
                squaresGUI[row][column] = squareGUI;
            }
        }
    
        updateBoard();
    
        container.add(panelBoard);

        return container;
    }

    private void updateBoard() {
        Square[][] squares = quoridorGUI.getBoardMatrix();
        int size = quoridorGUI.getBoardSize();
    
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                SquareGUI squareGUI = squaresGUI[row][column];
                Square square = squares[row][column];
    
                if (square.getToken() != null) {
                    Color color = square.getToken().getColor();
                    squareGUI.setColorToken(color);
                    squareGUI.drawToken();
                } else {
                    squareGUI.eraseToken();
                }
    
                if (square.getWallUp() != null) {
                    if (!squareGUI.getWallUp()) {
                        squareGUI.setWallUp();
                        squareGUI.setWallUp(true);
                    }
                } else if (square.getWallLeft() != null) {
                    if (!squareGUI.getWallLeft()) {
                        squareGUI.setWallLeft();
                        squareGUI.setWallLeft(true);
                    }
                } else if (square.getWallDown() != null) {
                    if (!squareGUI.getWallDown()) {
                        squareGUI.setWallDown();
                        squareGUI.setWallDown(true);
                    }
                } else if (square.getWallRight() != null) {
                    if (!squareGUI.getWallRight()) {
                        squareGUI.setWallRight();
                        squareGUI.setWallRight(true);
                    }
                } else {
                    if (squareGUI.getWallUp()) {
                        squareGUI.delWallUp();
                        squareGUI.setWallUp(false);
                    } else if (squareGUI.getWallLeft()) {
                        squareGUI.delWallLeft();
                        squareGUI.setWallLeft(false);
                    }  else if (squareGUI.getWallDown()) {
                        squareGUI.delWallDown();
                        squareGUI.setWallDown(false);
                    }  else if (squareGUI.getWallRight()) {
                        squareGUI.delWallRight();
                        squareGUI.setWallRight(false);
                    }
                }
            }
        }
    
        repaint();
    }

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

    private JPanel prepareElementsPlayer1() {
        JPanel container = new JPanel(new GridBagLayout());
        container.setBackground(Color.WHITE);

        panelPlayer1 = new JPanel();
        panelPlayer1.setBackground(Color.WHITE);
        panelPlayer1.setLayout(new BoxLayout(panelPlayer1, BoxLayout.X_AXIS));
        panelPlayer1.setBorder(new EmptyBorder(10, 10, 10, 10));

        imagePlayer1 = new JLabel();
        imagePlayer1.setSize(50, 50);
        createImage(imagePlayer1, "assets/stopWatch.png");

        labelNamePlayer1 = new JLabel("Daniel Diaz");
        labelNamePlayer1.setFont(new Font(QuoridorGUI.FONT_SUBTITLE, Font.BOLD, 20));

        labelWallsPlayer1 = createLabel("Remaining walls: 2");
        labelNormalWallsPlayer1 = createLabel("Normal: 2");
        labelTemporaryWallsPlayer1 = createLabel("Temporary: 2");
        labelLongWallsPlayer1 = createLabel("Long: 2");
        labelAlliedWallsPlayer1 = createLabel("Allied: 2");

        panelPlayer1.add(imagePlayer1);
        panelPlayer1.add(Box.createHorizontalStrut(30));
        panelPlayer1.add(prepareElemetsNamePlayer(labelNamePlayer1, labelWallsPlayer1));
        panelPlayer1.add(Box.createHorizontalStrut(30));
        panelPlayer1.add(prepareElementsWallsPlayer(labelNormalWallsPlayer1, labelTemporaryWallsPlayer1, labelLongWallsPlayer1, labelAlliedWallsPlayer1));
        container.add(panelPlayer1);

        return container;
    }

    private JPanel prepareElementsPlayer2() {
        JPanel container = new JPanel(new GridBagLayout());
        container.setBackground(Color.WHITE);

        panelPlayer2 = new JPanel();
        panelPlayer2.setBackground(Color.WHITE);
        panelPlayer2.setLayout(new BoxLayout(panelPlayer2, BoxLayout.X_AXIS));
        panelPlayer2.setBorder(new EmptyBorder(10, 10, 10, 10));

        imagePlayer2 = new JLabel();
        imagePlayer2.setSize(50, 50);
        createImage(imagePlayer2, "assets/stopWatch.png");

        labelNamePlayer2 = new JLabel("Miguel Motta");
        labelNamePlayer2.setFont(new Font(QuoridorGUI.FONT_SUBTITLE, Font.BOLD, 20));

        labelWallsPlayer2 = createLabel("Remaining walls: 2");
        labelNormalWallsPlayer2 = createLabel("Normal: 2");
        labelTemporaryWallsPlayer2 = createLabel("Temporary: 2");
        labelLongWallsPlayer2 = createLabel("Long: 2");
        labelAlliedWallsPlayer2 = createLabel("Allied: 2");

        panelPlayer2.add(imagePlayer2);
        panelPlayer2.add(Box.createHorizontalStrut(30));
        panelPlayer2.add(prepareElemetsNamePlayer(labelNamePlayer2, labelWallsPlayer2));
        panelPlayer2.add(Box.createHorizontalStrut(30));
        panelPlayer2.add(prepareElementsWallsPlayer(labelNormalWallsPlayer2, labelTemporaryWallsPlayer2, labelLongWallsPlayer2, labelAlliedWallsPlayer2));
        container.add(panelPlayer2);

        return container;
    }

    private JPanel prepareElemetsNamePlayer(JLabel playerName, JLabel playerWalls) {
        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.add(playerName);
        content.add(playerWalls);

        return content;
    }

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

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(QuoridorGUI.FONT_TEXT, Font.PLAIN, 10));

        return label;
    }

    private void prepareActions() {
        prepareActionButtonsMove();
    }

    private void prepareActionButtonsMove() {
        buttonUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    quoridorGUI.moveToken("UP");
                    refresh();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    quoridorGUI.moveToken("LEFT");
                    refresh();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    quoridorGUI.moveToken("DOWN");
                    refresh();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    quoridorGUI.moveToken("RIGHT");
                    refresh();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

     private void createImage(JLabel label, String path) {
		URL url = getClass().getResource(path);
		if (url != null) {
			ImageIcon img = new ImageIcon(url);
			label.setIcon(new ImageIcon(img.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH)));
			label.setHorizontalAlignment(SwingConstants.CENTER);
        	label.setVerticalAlignment(SwingConstants.CENTER);
		}
	}

    private void refresh() {
		updateBoard();
	}
}
