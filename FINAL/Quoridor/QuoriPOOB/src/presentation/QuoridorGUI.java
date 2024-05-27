package presentation;

import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.HashMap;

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

    private QuoriPOOB quoriPOOB;
    private boolean playerTwo;

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
        this.quoriPOOB = QuoriPOOB.getQuoriPOOB();
        this.quoriPOOB.setPersistence();
        this.playerTwo = false;
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
        setTitle("Quoridor");
        setSize(PREFERRED_DIMENSION);
        setLocationRelativeTo(null);
        prepareElementsMenu();

        JPanel container = new JPanel(new BorderLayout());
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        showStartGUI();

        container.add(cardPanel);

        add(container, BorderLayout.CENTER);
    }

    private void prepareElementsMenu() {
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

    private void prepareActions() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                confirmClose();
            }
        });

        prepareActionsMenu();
    }

    private void prepareActionsMenu() {
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                optionOpen();
            }
        });

        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                optionSave();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                confirmClose();
            }
        });

        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                restart();
                showStartGUI();
            }
        });
    }

    public void showStartGUI() {
        if (startGUI == null) {
            startGUI = new StartGUI(this);
            cardPanel.add(startGUI, "startGUI");
        }
        cardLayout.show(cardPanel, "startGUI");
    }

    public void showGameModeGUI() {
        if (gameModeGUI == null) {
            gameModeGUI = new GameModeGUI(this);
            cardPanel.add(gameModeGUI, "gameModeGUI");
        }
        cardLayout.show(cardPanel, "gameModeGUI");
    }

    public void showGameDifficultyGUI() {
        if (gameDifficultyGUI == null) {
            gameDifficultyGUI = new GameDifficultyGUI(this);
            cardPanel.add(gameDifficultyGUI, "gameDifficultyGUI");
        }
        cardLayout.show(cardPanel, "gameDifficultyGUI");
    }

    public void showPlayerInfoGUI() {
        if (playerInfoGUI == null) {
            playerInfoGUI = new PlayerInfoGUI(this);
            cardPanel.add(playerInfoGUI, "playerInfoGUI");
        }
        cardLayout.show(cardPanel, "playerInfoGUI");
    }

    public void showSetUpGameGUI() {
        if (setUpGameGUI == null) {
            setUpGameGUI = new SetUpGameGUI(this);
            cardPanel.add(setUpGameGUI, "setUpGameGUI");
        }
        cardLayout.show(cardPanel, "setUpGameGUI");
    }

    public void showBoardGUI() {
        if (boardGUI == null) {
            boardGUI = new BoardGUI(this);
            this.quoriPOOB.addObserverToMode(boardGUI);
            this.quoriPOOB.addObserverToQuoridor(boardGUI);
            cardPanel.add(boardGUI, "boardGUI");
        }
        this.quoriPOOB.startTurn();
        cardLayout.show(cardPanel, "boardGUI");
    }

    public void confirmClose() {
        int option = JOptionPane.showConfirmDialog(this, "Are you sure do you want to get out of the game?",
                "Close Window", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void restart() {
        quoriPOOB.resetSingleton();
        quoriPOOB = QuoriPOOB.getQuoriPOOB();
        playerTwo = false;
        startGUI = null;
        gameModeGUI = null;
        gameDifficultyGUI = null;
        playerInfoGUI = null;
        setUpGameGUI = null;
        boardGUI = null;
    }

    public void restartPlayerInfoGUI() {
        playerInfoGUI = null;
    }

    public void setPlayerTwo() {
        this.playerTwo = true;
    }

    public boolean getPlayerTwo() {
        return this.playerTwo;
    }

    // Controlador
    public boolean twoPlayers() {
        return quoriPOOB.getTwoPlayers();
    }

    public void setOnePlayer() {
        quoriPOOB.setOnePlayer();
    }

    public void setTwoPlayers() {
        quoriPOOB.setTwoPlayers();
    }

    public void setNormalMode() throws QuoriPOOBException {
        quoriPOOB.setMode("domain.NormalMode");
    }

    public void setTimeTrialMode() throws QuoriPOOBException {
        quoriPOOB.setMode("domain.TimeTrial");
    }

    public void setTimedMode() throws QuoriPOOBException {
        quoriPOOB.setMode("domain.Timed");
    }
    public void setTime(int time) {
        quoriPOOB.setTime(time);
    }

    public void createBeginnerMachine() throws QuoriPOOBException {
        quoriPOOB.createPlayerMachine(Color.RED, "domain.Beginner");
    }

    public void createIntermediateMachine() throws QuoriPOOBException {
        quoriPOOB.createPlayerMachine(Color.RED, "domain.Intermediate");
    }

    public void createAdvancedMachine() throws QuoriPOOBException {
        quoriPOOB.createPlayerMachine(Color.RED, "domain.Advanced");
    }

    public void createPlayerHuman(String name, Color color) throws QuoriPOOBException {
        quoriPOOB.createPlayerHuman(name, color);
    }

    public void createBoard(int size, HashMap<String, int[][]> specialSquares) throws QuoriPOOBException {
        quoriPOOB.createBoard(size, specialSquares);
    }

    public void addWalls(int normal, int temporary, int longW, int allied) throws QuoriPOOBException {
        quoriPOOB.addWalls(normal, temporary, longW, allied);
    }

    public void moveToken(String direction) throws QuoriPOOBException {
        quoriPOOB.moveToken(direction);
    }

    public void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide)
            throws QuoriPOOBException {
        quoriPOOB.addWallToBoard(type, initialRow, initialColumn, squareSide);
    }

    public Player getPlayerPlaying() {
        return quoriPOOB.getCurrentPlayer();
    }

    public Square[][] getBoardMatrix() {
        return quoriPOOB.getMatrixBoard();
    }

    public int getBoardSize() {
        return quoriPOOB.getSize();
    }

    public String[] getNames() {
        return this.quoriPOOB.getNames();
    }

    public HashMap<Color, HashMap<String, Integer>> getReaminingWalls() {
        return this.quoriPOOB.numberWalls();
    }

    public Color getColorPlayer(String name) throws QuoriPOOBException {
        return this.quoriPOOB.getColor(name);
    }

    public boolean timeMode() {
        return this.quoriPOOB.timeMode();
    }

    public boolean getGameOver() {
        return this.quoriPOOB.getGameOver();
    }

    public Player getWinner() {
        return this.quoriPOOB.getWinner();
    }

    private void optionOpen() {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                Quoridor quoridor = QuoriPOOB.openFile(file);
                quoriPOOB.setQuoridor(quoridor);
                boardGUI = null;
                showBoardGUI();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error loading the game", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void optionSave() {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                quoriPOOB.saveFile(file);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error saving the game", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String args[]) {
        QuoridorGUI gui = new QuoridorGUI();
        gui.setVisible(true);
    }
}