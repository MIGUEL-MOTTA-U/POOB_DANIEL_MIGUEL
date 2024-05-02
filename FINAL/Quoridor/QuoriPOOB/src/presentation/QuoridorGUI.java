package presentation;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class QuoridorGUI extends JFrame {
    private static final int PREFERRED_WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.6);
    private static final int PREFERRED_HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.7);
    private static final Dimension PREFERRED_DIMENSION = new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT);

    // CardLayout
    private CardLayout cardLayout;
    private JPanel cardPanel;

    // Windows
    StartGUI startGUI;
    GameModeGUI gameModeGUI;
    GameDifficultyGUI gameDifficultyGUI;

    private QuoridorGUI() {
        prepareElements();
        prepareActions();
        cardLayout.show(cardPanel, "startGUI");
    }

    private void prepareElements() {
        setTitle("Quoridor");
        setSize(PREFERRED_DIMENSION);
        setLocationRelativeTo(null);
        
        createCardPanel();

        JPanel container = new JPanel(new GridBagLayout());
        
        container.add(cardPanel);
        getContentPane().add(container);
    }

    private void createCardPanel() {
        startGUI = new StartGUI(this);
        gameModeGUI = new GameModeGUI(this);
        gameDifficultyGUI = new GameDifficultyGUI(this);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(startGUI, "startGUI");
        cardPanel.add(gameModeGUI, "gameModeGUI");
        cardPanel.add(gameDifficultyGUI, "gameDifficultyGUI");
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

    public static void main(String args[]) {
        QuoridorGUI gui = new QuoridorGUI();
        gui.setVisible(true);
    }
}
