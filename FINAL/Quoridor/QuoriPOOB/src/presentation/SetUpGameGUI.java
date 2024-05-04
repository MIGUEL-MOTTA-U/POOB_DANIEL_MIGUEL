package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class SetUpGameGUI extends JPanel{
    public static final Dimension PREFERRED_DIMENSION = new Dimension(250, 30);

    QuoridorGUI quoridorGUI;

    // West
    private JPanel panelWest;
    private JLabel imageGame;
    private JLabel labelTitle;
    private JLabel labelDescription;
    private JPanel panelSquares;
    private JLabel labelSquareTitle;
    private JTextField textNormalSquares;
    private JTextField textTeleporterSquares;
    private JTextField textReturnSquares;
    private JTextField textDoubleTurnSquares;

    // East
    private JPanel panelEast;
    private JTextField textBoardSize;
    private JButton textTime;
    private JPanel panelWalls;
    private JTextField textNormalWalls;
    private JTextField textTemporaryWalls;
    private JTextField textLongWalls;
    private JTextField textAlliedWalls;
    private JButton buttonNext;

    public SetUpGameGUI(QuoridorGUI quoridorGUI) {
        this.quoridorGUI = quoridorGUI;
        prepareElements();
        prepareActions();
        setVisible(true);
    }

    private void prepareElements() {
        JPanel content = new JPanel(new BorderLayout());
        content.setBorder(new RoundBorder(Color.WHITE, Color.WHITE, 20));

        prepareElementsWest(content);

        add(content);
    }

    private void prepareElementsWest(JPanel content) {
        panelWest = new JPanel();
        panelWest.setBackground(Color.WHITE);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new EmptyBorder(0, 0, 0, 50));

        imageGame = new JLabel();
        imageGame.setSize(50, 50);
        createImage(imageGame, "assets/Logo.png");

        labelTitle = new JLabel("Player info");
        labelTitle.setFont(new Font(QuoridorGUI.FONT_TITLE, Font.BOLD, 25));

        labelDescription = new JLabel("Enter you data");
        labelDescription.setFont(new Font(QuoridorGUI.FONT_TEXT, Font.PLAIN, 15));

        container.add(imageGame);
        container.add(Box.createVerticalStrut(10));
        container.add(labelTitle);
        container.add(Box.createVerticalStrut(10));
        container.add(labelDescription);
        container.add(Box.createVerticalStrut(10));
        container.add(prepareElementsSquares());
        panelWest.add(container);

        content.add(panelWest, BorderLayout.WEST);
    }

    private JPanel prepareElementsSquares() {
        panelSquares = new JPanel();
        panelSquares.setLayout(new BoxLayout(panelSquares, BoxLayout.Y_AXIS));

        labelSquareTitle = new JLabel("Squares");
        labelSquareTitle.setFont(new Font(QuoridorGUI.FONT_SUBTITLE, Font.BOLD, 15));

        textNormalSquares = new JTextField("Number of normal squares");
        textNormalSquares.setPreferredSize(PREFERRED_DIMENSION);
        textTeleporterSquares = new JTextField("Number of teleporter squares");
        textTeleporterSquares.setPreferredSize(PREFERRED_DIMENSION);
        textReturnSquares = new JTextField("Number of return squares");
        textReturnSquares.setPreferredSize(PREFERRED_DIMENSION);
        textDoubleTurnSquares = new JTextField("Number of double turn squares");
        textDoubleTurnSquares.setPreferredSize(PREFERRED_DIMENSION);

        panelSquares.add(labelSquareTitle);
        panelSquares.add(Box.createVerticalStrut(10));
        panelSquares.add(textNormalSquares);
        panelSquares.add(Box.createVerticalStrut(10));
        panelSquares.add(textTeleporterSquares);
        panelSquares.add(Box.createVerticalStrut(10));
        panelSquares.add(textReturnSquares);
        panelSquares.add(Box.createVerticalStrut(10));
        panelSquares.add(textDoubleTurnSquares);

        return panelSquares;
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
