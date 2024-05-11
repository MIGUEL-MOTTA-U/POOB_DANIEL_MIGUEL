package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;


public class BoardGUI extends JPanel{
    private QuoridorGUI quoridorGUI;

    // Header
    private JPanel panelNorth;
    private JLabel imageGame;
	private JLabel labelTitle;
    
    // Board
    private JPanel panelWest;
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
    private JPanel panelNamePlayer1;
    private JPanel panelWallsPlayer1;
    private JLabel imagePlayer1;
    private JLabel labelNamePlayer1;
    private JLabel labelWallsPlayer1;
    private JLabel labelNormalWallsPlayer1;
    private JLabel labelTemporaryWallsPlayer1;
    private JLabel labelLongWallsPlayer1;
    private JLabel labelAlliedWallsPlayer1;

    // Player 2
    private JPanel panelPlayer2;
    private JPanel panelNamePlayer2;
    private JPanel panelWallsPlayer2;
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
        JPanel content = new JPanel(new BorderLayout());
        content.setPreferredSize(QuoridorGUI.PREFERRED_DIMENSION);

        prepareElementsHeader(content);
        prepareElementsEast(content);

        add(content);
    }

    private void prepareElementsHeader(JPanel content) {
        panelNorth = new JPanel();
        panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.Y_AXIS));
        panelNorth.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

		JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.X_AXIS));
        panelHeader.setBorder(new EmptyBorder(50, 20, 20, 20));

		imageGame = new JLabel();
		imageGame.setSize(50, 50);
		createImage(imageGame, "assets/Logo.png");

		labelTitle = new JLabel("Quoridor");
		labelTitle.setFont(new Font(QuoridorGUI.FONT_TITLE, Font.BOLD, 35));

		panelHeader.add(imageGame);
        panelHeader.add(Box.createHorizontalStrut(70));
		panelHeader.add(labelTitle);
        panelNorth.add(panelHeader);

		content.add(panelNorth, BorderLayout.NORTH);
	}

    private void prepareElementsEast(JPanel content) {
        panelEast = new JPanel(new GridBagLayout());

        JPanel container = new JPanel();
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

    private void prepareActions() {

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
}
