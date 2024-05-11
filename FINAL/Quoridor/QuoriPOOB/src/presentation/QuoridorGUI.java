package presentation;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

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

    // Menu
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem newItem;
	private JMenuItem openItem;
	private JMenuItem saveItem;
	private JMenuItem exitItem;

    // Windows
    private StartGUI startGUI;
    private GameModeGUI gameModeGUI;
    private GameDifficultyGUI gameDifficultyGUI;
    private PlayerInfoGUI playerInfoGUI;
    private SetUpGameGUI setUpGameGUI;
    private BoardGUI boardGUI;

    private QuoridorGUI() {
        prepareElements();
        prepareActions();
        cardLayout.show(cardPanel, "boardGUI");
    }

    private void prepareElements() {
        setTitle("Quoridor");
        setSize(PREFERRED_DIMENSION);
        setLocationRelativeTo(null);
        prepareElementsMenu();

        JPanel container = new JPanel();
        createCardPanel();

        SpringLayout calculator = new SpringLayout();
        calculator.putConstraint(SpringLayout.HORIZONTAL_CENTER, cardPanel, 0, SpringLayout.HORIZONTAL_CENTER, container);
        calculator.putConstraint(SpringLayout.VERTICAL_CENTER, cardPanel, 0, SpringLayout.VERTICAL_CENTER, container);

        container.add(cardPanel);
        container.setLayout(calculator);
        add(container, BorderLayout.CENTER);
    }

    private void prepareElementsMenu(){
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		newItem = new JMenuItem("New");
		openItem = new JMenuItem("Open");
		saveItem = new JMenuItem("Save");
		exitItem = new JMenuItem("Exit");

		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
	}

    private void createCardPanel() {
        startGUI = new StartGUI(this);
        gameModeGUI = new GameModeGUI(this);
        gameDifficultyGUI = new GameDifficultyGUI(this);
        playerInfoGUI = new PlayerInfoGUI(this);
        setUpGameGUI = new SetUpGameGUI(this);
        boardGUI = new BoardGUI(this);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(startGUI, "startGUI");
        cardPanel.add(gameModeGUI, "gameModeGUI");
        cardPanel.add(gameDifficultyGUI, "gameDifficultyGUI");
        cardPanel.add(playerInfoGUI, "playerInfoGUI");
        cardPanel.add(setUpGameGUI, "setUpGameGUI");
        cardPanel.add(boardGUI, "boardGUI");
    }

    private void prepareActions() { 
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        addWindowListener(new WindowAdapter() { 
            public void windowClosing(WindowEvent windowEvent) { 
                confirmClose(); 
            }
        }); 

        prepareActionsMenu();
    } 

    private void prepareActionsMenu(){
		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(QuoridorGUI.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File fileSelected = chooser.getSelectedFile();
					JOptionPane.showMessageDialog(QuoridorGUI.this, "You chose open this file: " + fileSelected.getName(), "Action under construction", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showSaveDialog(QuoridorGUI.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File fileSelected = chooser.getSelectedFile();
					JOptionPane.showMessageDialog(QuoridorGUI.this, "You chose save this file: " + fileSelected.getName(), "Action under construction", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
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

    public void showSetUpGameGUI() {
        cardLayout.show(cardPanel, "setUpGameGUI");
    }

    public void showBoardGUI() {
        cardLayout.show(cardPanel, "boardGUI");
    }

    public static void main(String args[]) {
        QuoridorGUI gui = new QuoridorGUI();
        gui.setVisible(true);
    }
}