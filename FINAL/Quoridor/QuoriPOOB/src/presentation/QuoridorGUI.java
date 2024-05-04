package presentation;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class QuoridorGUI extends JFrame {
    public static final int PREFERRED_WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.6);
    public static final int PREFERRED_HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.7);
    public static final Dimension PREFERRED_DIMENSION = new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT);
    public static final Color DEFAULT_BACKGROUND = new Color(238, 238, 238);
    public static final Color BUTTONS_COLOR = new Color(80, 180, 255);
    public static final Color BUTTONS_COLOR_HOVER = new Color(70, 170, 255);
    public static final Color COLOR_BORDER_PANEL = new Color(153, 153, 153);
    public static final String FONT_TITLE = "Tahoma";
    public static final String FONT_SUBTITLE = "Bahnschrift";
    public static final String FONT_TEXT = "Candara";

    // CardLayout
    private CardLayout cardLayout;
    private JPanel cardPanel;

    // Windows
    StartGUI startGUI;
    GameModeGUI gameModeGUI;
    GameDifficultyGUI gameDifficultyGUI;
    PlayerInfoGUI playerInfoGUI;
    SetUpGameGUI setUpGameGUI;

    private QuoridorGUI() {
        prepareElements();
        prepareActions();
        cardLayout.show(cardPanel, "setUpGameGUI");
    }

    private void prepareElements() {
        setTitle("Quoridor");
        setSize(PREFERRED_DIMENSION);
        setLocationRelativeTo(null);

        JPanel container = new JPanel();
        createCardPanel();

        SpringLayout calculator = new SpringLayout();
        calculator.putConstraint(SpringLayout.HORIZONTAL_CENTER, cardPanel, 0, SpringLayout.HORIZONTAL_CENTER, container);
        calculator.putConstraint(SpringLayout.VERTICAL_CENTER, cardPanel, 0, SpringLayout.VERTICAL_CENTER, container);

        container.add(cardPanel);
        container.setLayout(calculator);
        add(container, BorderLayout.CENTER);
    }

    private void createCardPanel() {
        startGUI = new StartGUI(this);
        gameModeGUI = new GameModeGUI(this);
        gameDifficultyGUI = new GameDifficultyGUI(this);
        playerInfoGUI = new PlayerInfoGUI(this);
        setUpGameGUI = new SetUpGameGUI(this);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(startGUI, "startGUI");
        cardPanel.add(gameModeGUI, "gameModeGUI");
        cardPanel.add(gameDifficultyGUI, "gameDifficultyGUI");
        cardPanel.add(playerInfoGUI, "playerInfoGUI");
        cardPanel.add(setUpGameGUI, "setUpGameGUI");
    }

    private void prepareActions() { 
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        addWindowListener(new WindowAdapter() { 
            public void windowClosing(WindowEvent windowEvent) { 
                confirmClose(); 
            }
        }); 
    } 

    private void confirmClose() { 
        int option = JOptionPane.showConfirmDialog(this, "Are you sure do you want to get out of the game?", 
        "Close Window", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); 
    
        if (option == JOptionPane.YES_OPTION) { 
            System.exit(0);
        } 
    } 

    public void showGameModeGUI() {
        cardLayout.show(cardPanel, "gameModeGUI");
    }

    public void showGameDifficultyGUI() {
        cardLayout.show(cardPanel, "gameDifficultyGUI");
    }

    public void showPlayerInfoGUI() {
        cardLayout.show(cardPanel, "playerInfoGUI");
    }

    public static void main(String args[]) {
        QuoridorGUI gui = new QuoridorGUI();
        gui.setVisible(true);
    }
}