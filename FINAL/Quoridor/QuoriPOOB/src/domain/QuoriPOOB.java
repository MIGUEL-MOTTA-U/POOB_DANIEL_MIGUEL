package domain;

import java.util.*;
import java.io.*;

/**
 * This class is the controller between the domain and the presentation.
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2024
 */
public class QuoriPOOB {
	private static QuoriPOOB quoriPOOBSingleton;
	private Quoridor quoridor;

	/**
	 * Constructor of QuoriPOOB
	 */
	private QuoriPOOB() {
		this.quoridor = new Quoridor();
	}

	/**
	 * Get the singleton of the class
	 * 
	 * @return
	 */
	public static QuoriPOOB getQuoriPOOB() {
		if (quoriPOOBSingleton == null) {
			quoriPOOBSingleton = new QuoriPOOB();
		}
		
		return quoriPOOBSingleton;
	}

	/**
	 * Set the game mode to one player
	 */
	public void setOnePlayer() {
		this.quoridor.setOnePlayer();
	}

	/**
	 * Set the game mode to two players
	 */
	public void setTwoPlayers() {
		this.quoridor.setTwoPlayers();
	}

	/**
	 * Set the mode of the game
	 * 
	 * @param mode the mode type of the game
	 * @throws QuoriPOOBException
	 */
	public void setMode(String mode) throws QuoriPOOBException {
		this.quoridor.setMode(mode);
	}

	/**
	 * Set the time of the game mode
	 * 
	 * @param time the time that the players have to play
	 */
	public void setTime(int time) {
		this.quoridor.setTime(time);
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
		this.quoridor.createBoard(size, specialSquares);
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
		this.quoridor.createPlayerHuman(name, color);
	}

	/**
	 * Creates a Machine Player for QuoriPOOB and integrate it to the Game according
	 * to the respective difficulty.
	 * 
	 * @param color The color of the Machine Player.
	 * @param type  The Type (difficulty) of the Machine.
	 * @throws QuoriPOOBException Throws an Exception in case there is other Machine
	 *                            created, or in case the given parameters are
	 *                            wrong, or
	 *                            there are two players already.
	 */
	public void createPlayerMachine(String color, String type) throws QuoriPOOBException {
		this.quoridor.createPlayerMachine(color, type);
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
	public void addWalls(int normal, int temporary, int longWall, int allied) throws QuoriPOOBException {
		this.quoridor.addWalls(normal, temporary, longWall, allied);
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
		this.quoridor.addWallToBoard(type, initialRow, initialColumn, squareSide);
	}

	/**
	 * Moves the token to the given position.
	 * 
	 * @param direction The respective direction where the token is moving.
	 * @throws QuoriPOOBException Throws an exception in case the parameters are
	 *                            wrong or the action is not possible.
	 */
	public void moveToken(String direction) throws QuoriPOOBException {
		this.quoridor.moveToken(direction);
	}

	/**
	 * Start player's turn to play
	 */
	public void startTurn() {
		this.quoridor.startTurn();
	}

	/**
	 * Add an observer to mode
	 * 
	 * @param observer the observer to add
	 */
	public void addObserverToMode(TimeObserver observer) {
		this.quoridor.addObserverToMode(observer);
	}

	/**
	 * Add an observer to quoridor
	 * 
	 * @param observer the observer to add
	 */
	public void addObserverToQuoridor(GameModeObserver observer) {
		this.quoridor.addObserver(observer);
	}

	/**
	 * Return if the mode is time mode
	 * 
	 * @return TRUE, if the game mode is a time mode. FALSE, otherwise
	 */
	public boolean timeMode() {
		return this.quoridor.timeMode();
	}

	/**
	 * Reset the singleton
	 */
	public void resetSingleton() {
		if (quoriPOOBSingleton != null) {
			this.quoridor.finishGame();
			this.quoridor = null;
		}

		quoriPOOBSingleton = null;
	}

	/**
	 * Returns the names of the players
	 * 
	 * @return Array of String, from the respective players.
	 */
	public String[] getNames() {
		return this.quoridor.getNames();
	}

	/**
	 * Return the number of each type of wall the players have.
	 * 
	 * @return the number of walls with their respective quantity
	 */
	public HashMap<String, HashMap<String, Integer>> numberWalls() {
		return this.quoridor.numberWalls();
	}

	/**
	 * Returns the respective color by the given name.
	 * 
	 * @param name The name of the player.
	 * @return The color of the player
	 * @throws QuoriPOOBException Throws an exception in case the parameters are
	 *                            wrong or the action is not possible.
	 */
	public String getColor(String name) throws QuoriPOOBException {
		return this.quoridor.getColor(name);
	}

	/**
	 * Return the matrix board
	 * 
	 * @return the matrix taht respresents the board
	 */
	public Square[][] getMatrixBoard() {
		return this.quoridor.getMatrixBoard();
	}

	/**
	 * Return the size of the board
	 * 
	 * @return the board size
	 */
	public int getSize() {
		return this.quoridor.getSize();
	}

	/**
	 * Returns the board
	 * 
	 * @return the board of QuoriPOOB
	 */
	public Board getBoard() {
		return this.quoridor.getBoard();
	}

	/**
	 * The winner player
	 * 
	 * @return the winner player
	 */
	public Player getWinner() {
		return this.quoridor.getWinner();
	}

	/**
	 * Returns the player that is actually playing.
	 * 
	 * @return The player that has the turn to play.
	 */
	public Player getCurrentPlayer() {
		return this.quoridor.getCurrentPlayer();
	}

	/**
	 * Return if the game is over
	 * 
	 * @return TRUE, if the game is over. FALSE, otherwise
	 */
	public boolean getGameOver() {
		return this.quoridor.getGameOver();
	}

	/**
	 * Return if the game have two human players
	 * 
	 * @return True, if two human player are playing. FALSE, otherwise
	 */
	public boolean getTwoPlayers() {
		return this.quoridor.getTwoPlayers();
	}

	/**
	 * Return the quoridor
	 * 
	 * @return the quoridor
	 */
	public Quoridor getQuoridor() {
		return this.quoridor;
	}

	/**
	 * Set the new quoridor
	 * 
	 * @param quoridor the new quoridor
	 */
	public void setQuoridor(Quoridor quoridor) {
		this.quoridor = quoridor;
	}

	/**
	 * Save the game in a file
	 * 
	 * @param file the file where the game is saved
	 */
	public void saveFile(File file) {
		Persistence.saveFile(file, this.quoridor);
	}

	/**
	 * Load a saved game
	 * 
	 * @param file the file where the game is saved
	 * @return the saved game
	 * @throws QuoriPOOBException
	 */
	public static Quoridor openFile(File file) throws QuoriPOOBException {
		return Persistence.openFile(file);
	}
}
