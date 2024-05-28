package presentation;

import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.HashMap;

/**
 * This class communicates with the controller and contains the game's JFrame.
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2024
 */
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

    /*
     * Constructor of QuoridorGUI
     */
    private QuoridorGUI() {
        this.quoriPOOB = QuoriPOOB.getQuoriPOOB();
        this.playerTwo = false;
        prepareElements();
        prepareActions();
    }

    /*
     * Create all the elements
     */
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

    /*
     * Create the menu elements
     */
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

    /*
     * Create the actions
     */
    private void prepareActions() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                confirmClose();
            }
        });

        prepareActionsMenu();
    }

    /*
     * Create the menu actions
     */
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

    /**
     * Show the start view
     */
    public void showStartGUI() {
        if (startGUI == null) {
            startGUI = new StartGUI(this);
            cardPanel.add(startGUI, "startGUI");
        }
        cardLayout.show(cardPanel, "startGUI");
    }

    /**
     * Show the game mode view
     */
    public void showGameModeGUI() {
        if (gameModeGUI == null) {
            gameModeGUI = new GameModeGUI(this);
            cardPanel.add(gameModeGUI, "gameModeGUI");
        }
        cardLayout.show(cardPanel, "gameModeGUI");
    }

    /**
     * Show the game difficulty view
     */
    public void showGameDifficultyGUI() {
        if (gameDifficultyGUI == null) {
            gameDifficultyGUI = new GameDifficultyGUI(this);
            cardPanel.add(gameDifficultyGUI, "gameDifficultyGUI");
        }
        cardLayout.show(cardPanel, "gameDifficultyGUI");
    }

    /**
     * Show the player information view
     */
    public void showPlayerInfoGUI() {
        if (playerInfoGUI == null) {
            playerInfoGUI = new PlayerInfoGUI(this);
            cardPanel.add(playerInfoGUI, "playerInfoGUI");
        }
        cardLayout.show(cardPanel, "playerInfoGUI");
    }

    /**
     * Show the set up game view
     */
    public void showSetUpGameGUI() {
        if (setUpGameGUI == null) {
            setUpGameGUI = new SetUpGameGUI(this);
            cardPanel.add(setUpGameGUI, "setUpGameGUI");
        }
        cardLayout.show(cardPanel, "setUpGameGUI");
    }

    /**
     * Show the board view
     */
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

    /**
     * Close the game
     */
    public void confirmClose() {
        int option = JOptionPane.showConfirmDialog(this, "Are you sure do you want to get out of the game?",
                "Close Window", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Restart the game 
     */
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

    /**
     * Restat the player information view
     */
    public void restartPlayerInfoGUI() {
        playerInfoGUI = null;
    }

    /**
     * Set the second player entering his data
     */
    public void setPlayerTwo() {
        this.playerTwo = true;
    }

    /**
     * Return if the second player is entering his data
     * 
     * @return TRUE, if the second player is entering his data. FALSE, otherwise
     */
    public boolean getPlayerTwo() {
        return this.playerTwo;
    }

    // Controlador
    /**
	 * Return if the game have two human players
	 * 
	 * @return True, if two human player are playing. FALSE, otherwise
	 */
    public boolean twoPlayers() {
        return quoriPOOB.getTwoPlayers();
    }

    /**
	 * Set the game mode to one player
	 */
    public void setOnePlayer() {
        quoriPOOB.setOnePlayer();
    }

    /**
	 * Set the game mode to two players
	 */
    public void setTwoPlayers() {
        quoriPOOB.setTwoPlayers();
    }

    /**
	 * Set the normal mode of the game
	 * 
	 * @throws QuoriPOOBException
	 */
    public void setNormalMode() throws QuoriPOOBException {
        quoriPOOB.setMode("domain.NormalMode");
    }

    /**
	 * Set the time trial mode of the game
	 * 
	 * @throws QuoriPOOBException
	 */
    public void setTimeTrialMode() throws QuoriPOOBException {
        quoriPOOB.setMode("domain.TimeTrial");
    }

    /**
	 * Set the timed mode of the game
	 * 
	 * @throws QuoriPOOBException
	 */
    public void setTimedMode() throws QuoriPOOBException {
        quoriPOOB.setMode("domain.Timed");
    }

    /**
	 * Set the time of the game mode
	 * 
	 * @param time the time that the players have to play
	 */
    public void setTime(int time) {
        quoriPOOB.setTime(time);
    }

    /**
	 * Creates a begginer machine Player for QuoriPOOB and integrate it to the Game
	 * 
	 * @throws QuoriPOOBException Throws an Exception in case there is other Machine
	 *                            created, or in case the given parameters are
	 *                            wrong, or
	 *                            there are two players already.
	 */
    public void createBeginnerMachine() throws QuoriPOOBException {
        quoriPOOB.createPlayerMachine(Color.RED.toString(), "domain.Beginner");
    }

    /**
	 * Creates an intermediate machine Player for QuoriPOOB and integrate it to the Game
	 * 
	 * @throws QuoriPOOBException Throws an Exception in case there is other Machine
	 *                            created, or in case the given parameters are
	 *                            wrong, or
	 *                            there are two players already.
	 */
    public void createIntermediateMachine() throws QuoriPOOBException {
        quoriPOOB.createPlayerMachine(Color.RED.toString(), "domain.Intermediate");
    }

    /**
	 * Creates a advanced machine Player for QuoriPOOB and integrate it to the Game
	 * 
	 * @throws QuoriPOOBException Throws an Exception in case there is other Machine
	 *                            created, or in case the given parameters are
	 *                            wrong, or
	 *                            there are two players already.
	 */
    public void createAdvancedMachine() throws QuoriPOOBException {
        quoriPOOB.createPlayerMachine(Color.RED.toString(), "domain.Advanced");
    }

    /**
	 * Creates a Human Player for QuoriPOOB and integrate it to the Game
	 * 
	 * @param name  The name of the Player
	 * @param color The respective color of the Player
	 * @throws QuoriPOOBException Throws an Exception in case the attempt to more
	 *                            than two players, or create a player with wrong
	 *                            parameters.
	 */
    public void createPlayerHuman(String name, String color) throws QuoriPOOBException {
        quoriPOOB.createPlayerHuman(name, color);
    }

    /**
	 * Creates a Board for QuioriPOOB it its possible.
	 * 
	 * @param size           The size of the Board
	 * @param specialSquares The respective specialSquare
	 * @throws QuoriPOOBException The exception in case the Board is created
	 *                            with wrong parameters, or before create two
	 *                            players.
	 */
    public void createBoard(int size, HashMap<String, int[][]> specialSquares) throws QuoriPOOBException {
        quoriPOOB.createBoard(size, specialSquares);
    }

    /**
	 * Add the given number of walls to each player.
	 * 
	 * @param normal    The given number of normal Walls.
	 * @param temporary The given number of temporary Walls.
	 * @param longWall  The given number of long Walls.
	 * @param allied    The given number of allied Walls.
	 * @throws QuoriPOOBException Throws an Exception in case there is an attempt to
	 *                            add the Walls and there are not players created or
	 *                            a Board created, also if the parameters are wrong.
	 */
    public void addWalls(int normal, int temporary, int longW, int allied) throws QuoriPOOBException {
        quoriPOOB.addWalls(normal, temporary, longW, allied);
    }

    /**
	 * Moves the token to the given position.
	 * 
	 * @param direction The respective direction where the token is moving.
	 * @throws QuoriPOOBException Throws an exception in case the parameters are
	 *                            wrong or the action is not possible.
	 */
    public void moveToken(String direction) throws QuoriPOOBException {
        quoriPOOB.moveToken(direction);
    }

    /**
	 * Add a wall to the board by the given parameters.
	 * 
	 * @param type          The type of board
	 * @param initialRow    The initial row where is located the board (reference to
	 *                      the Square).
	 * @param initialColumn The initial column where is located the board (reference
	 *                      to the Square).
	 * @param squareSide    The side of the referenced Square where the wall is
	 *                      located.
	 * @throws QuoriPOOBException Throws an Exception in case the given parameters
	 *                            are wrong or the action is not possible.
	 */
    public void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide)
            throws QuoriPOOBException {
        quoriPOOB.addWallToBoard(type, initialRow, initialColumn, squareSide);
    }

    /**
	 * Returns the player that is actually playing.
	 * 
	 * @return The player that has the turn to play.
	 */
    public Player getPlayerPlaying() {
        return quoriPOOB.getCurrentPlayer();
    }

    /**
	 * Return the matrix board
	 * 
	 * @return the matrix taht respresents the board
	 */
    public Square[][] getBoardMatrix() {
        return quoriPOOB.getMatrixBoard();
    }

    /**
	 * Return the size of the board
	 * 
	 * @return the board size
	 */
    public int getBoardSize() {
        return quoriPOOB.getSize();
    }

    /**
	 * Returns the names of the players
	 * 
	 * @return Array of String, from the respective players.
	 */
    public String[] getNames() {
        return this.quoriPOOB.getNames();
    }

    /**
	 * Return the number of each type of wall the players have.
	 * 
	 * @return the number of walls with their respective quantity
	 */
    public HashMap<String, HashMap<String, Integer>> getReaminingWalls() {
        return this.quoriPOOB.numberWalls();
    }

    /**
	 * Returns the respective color by the given name.
	 * 
	 * @param name The name of the player.
	 * @return The color of the player
	 * @throws QuoriPOOBException Throws an exception in case the parameters are
	 *                            wrong or the action is not possible.
	 */
    public String getColorPlayer(String name) throws QuoriPOOBException {
        return this.quoriPOOB.getColor(name);
    }

    /**
	 * Return if the mode is time mode
	 * 
	 * @return TRUE, if the game mode is a time mode. FALSE, otherwise
	 */
    public boolean timeMode() {
        return this.quoriPOOB.timeMode();
    }

    /**
	 * Return if the game is over
	 * 
	 * @return TRUE, if the game is over. FALSE, otherwise
	 */
    public boolean getGameOver() {
        return this.quoriPOOB.getGameOver();
    }

    /**
	 * The winner player
	 * 
	 * @return the winner player
	 */
    public Player getWinner() {
        return this.quoriPOOB.getWinner();
    }

    /*
     * Open a saved game
     */
    private void optionOpen() {
        String currentDirectory = System.getProperty("user.dir");
        JFileChooser chooser = new JFileChooser(currentDirectory);
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

    /*
     * Save the game
     */
    private void optionSave() {
        String currentDirectory = System.getProperty("user.dir");
        JFileChooser chooser = new JFileChooser(currentDirectory);      
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